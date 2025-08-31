package com.zsb.bluex.core.job.delegate;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zsb.bluex.core.def.ControlDef;
import com.zsb.bluex.core.def.ParamDef;
import com.zsb.bluex.core.graph.GraphView;
import com.zsb.bluex.core.job.EventDelegate;
import com.zsb.bluex.core.launch.MetaHolder;
import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.delegate.DelegateNode;
import com.zsb.bluex.defaults.enums.RowOpType;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.Locale;

/**
 * Oracle 表行级监听 Job
 * 基于 DBMS_ALERT + 触发器
 */
@Slf4j
public class OracleTableListenerJob extends EventDelegate {

    private String jdbcUrl;
    private String username;
    private String password;
    private String driverClass;

    private Class<? extends Model<?>> entityClass;
    private String tableName;
    private String pkColumn;

    private boolean listenInsert;
    private boolean listenUpdate;
    private boolean listenDelete;

    private Thread listenerThread;
    private volatile boolean running;

    private final String prefix = "BLUEX_TABLEJOB_";

    public OracleTableListenerJob() {
    }

    public OracleTableListenerJob(GraphView graphView,
                                  String driverClass,
                                  String jdbcUrl,
                                  String username,
                                  String password,
                                  Class<? extends Model<?>> entityClass,
                                  boolean listenInsert,
                                  boolean listenUpdate,
                                  boolean listenDelete) {
        super(graphView);
        this.driverClass = driverClass;
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        this.entityClass = entityClass;
        this.listenInsert = listenInsert;
        this.listenUpdate = listenUpdate;
        this.listenDelete = listenDelete;

        resolveTableMeta();
    }

    /**
     * 解析实体类上的 @TableName 和 @TableId
     */
    private void resolveTableMeta() {
        TableName tn = entityClass.getAnnotation(TableName.class);
        if (tn == null || tn.value().isEmpty()) {
            throw new IllegalArgumentException("实体类必须有 @TableName 注解");
        }
        this.tableName = tn.value().toUpperCase(Locale.ROOT);

        // 找主键
        String pk = null;
        for (Field field : entityClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(TableId.class)) {
                TableId idAnn = field.getAnnotation(TableId.class);
                pk = idAnn.value();
                break;
            }
        }
        if (pk == null || pk.isEmpty()) {
            throw new IllegalArgumentException("实体类必须有 @TableId 主键");
        }
        this.pkColumn = pk.toUpperCase();
    }

    @Override
    public void start() throws Exception {
        Class.forName(driverClass);

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             Statement stmt = conn.createStatement()) {

            String alertName = prefix + tableName;

            // 4. 先清理可能的残留
            try {
                stmt.execute("BEGIN DBMS_ALERT.REMOVE('" + alertName + "'); END;");
            } catch (SQLException ignored) {
                // 不存在时忽略
            }

            String registerSql = "BEGIN DBMS_ALERT.REGISTER('" + alertName + "'); END;";
            stmt.execute(registerSql);

            String triggerName = prefix + tableName + "_TRG";
            StringBuilder triggerSql = new StringBuilder();
            triggerSql.append("CREATE OR REPLACE TRIGGER ").append(triggerName)
                    .append("\nAFTER ");

            boolean first = true;
            if (listenInsert) {
                triggerSql.append("INSERT");
                first = false;
            }
            if (listenUpdate) {
                if (!first) triggerSql.append(" OR ");
                triggerSql.append("UPDATE");
                first = false;
            }
            if (listenDelete) {
                if (!first) triggerSql.append(" OR ");
                triggerSql.append("DELETE");
            }

            triggerSql.append(" ON ").append(tableName).append(" FOR EACH ROW\n");
            triggerSql.append("DECLARE v_op VARCHAR2(20);\n");
            triggerSql.append("BEGIN\n");
            triggerSql.append("  IF INSERTING THEN v_op := 'INSERT'; END IF;\n");
            triggerSql.append("  IF UPDATING THEN v_op := 'UPDATE'; END IF;\n");
            triggerSql.append("  IF DELETING THEN v_op := 'DELETE'; END IF;\n");

            // DELETE 用 :OLD，其他用 :NEW
            triggerSql.append("  IF DELETING THEN\n")
                    .append("    DBMS_ALERT.SIGNAL('").append(alertName).append("', v_op || ':' || :OLD.")
                    .append(pkColumn).append(");\n")
                    .append("  ELSE\n")
                    .append("    DBMS_ALERT.SIGNAL('").append(alertName).append("', v_op || ':' || :NEW.")
                    .append(pkColumn).append(");\n")
                    .append("  END IF;\n");

            triggerSql.append("END;");
            stmt.execute(triggerSql.toString());

            log.info("注册 OracleTableListenerJob 成功: {}", triggerName);

            running = true;
            listenerThread = new Thread(() -> listenLoop(alertName));
            listenerThread.start();
        }
    }

    @Override
    public void end() {
        running = false;
        if (listenerThread != null && listenerThread.isAlive()) {
            listenerThread.interrupt(); // 通知线程退出
        }
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             Statement stmt = conn.createStatement()) {
            String triggerName = prefix + tableName + "_TRG";
            stmt.execute("DROP TRIGGER " + triggerName);

            String alertName = prefix + tableName;
            stmt.execute("BEGIN DBMS_ALERT.REMOVE('" + alertName + "'); END;");

            log.info("注销 OracleTableListenerJob 成功: {}", triggerName);
        } catch (Exception e) {
            log.error("注销 OracleTableListenerJob 失败", e);
        }
    }

    @Override
    public ControlDef provideDefinition() {
        ControlDef def = new ControlDef();
        def.setName("OracleTableListenerJob");
        def.setDisplayName("Oracle表监听器");
        def.setCategory("事件委托|OracleTableListenerJob");
        def.setQualifiedName("DELEGATE:OracleTableListenerJob");
        def.setSignature("DELEGATE:OracleTableListenerJob");
        def.setDelegate(true);

        def.getOutputExecDefs().add(new ParamDef("Exec"));

        def.getOutputParamDefs().add(
                new ParamDef("OpType", MetaHolder.ENUM_DEFINITION.get(RowOpType.class.getName()))
        );

        def.getOutputParamDefs().add(
                new ParamDef("ID", MetaHolder.PRIMITIVE_DEFINITION.get("java.lang.String"))
        );

        return def;
    }

    private void listenLoop(String alertName) {
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {

            // 在同一连接上先注册 alert
            try (CallableStatement regStmt = conn.prepareCall("{call DBMS_ALERT.REGISTER(?)}")) {
                regStmt.setString(1, alertName);
                regStmt.execute();
            }

            while (running && !Thread.currentThread().isInterrupted()) {
                try (CallableStatement stmt = conn.prepareCall("{call DBMS_ALERT.WAITONE(?, ?, ?, ?)}")) {
                    stmt.setString(1, alertName);
                    stmt.registerOutParameter(2, Types.VARCHAR); // message
                    stmt.registerOutParameter(3, Types.INTEGER); // status
                    stmt.setInt(4, 5); // timeout 秒

                    stmt.execute();

                    int status = stmt.getInt(3);
                    if (status == 0) {
                        String msg = stmt.getString(2);
                        if (msg != null) {
                            handleMessage(msg);
                        }
                    } else {
                        log.info("WAITONE 超时或未收到消息，status={}", status);
                    }
                } catch (SQLException e) {
                    if (!running) break; // 正常结束
                    log.error("监听 WAITONE 异常", e);
                }
            }

        } catch (Exception e) {
            log.error("OracleTableListenerJob 监听异常", e);
        }
    }

    private void handleMessage(String msg) {
        try {
            String[] parts = msg.split(":", 2);
            String op = parts[0];
            String id = parts.length > 1 ? parts[1] : null;

            ExecutionContext newCtx = graphView.buildExecCtx();
            DelegateNode delegateNode = (DelegateNode) newCtx.findStartupNode();
            delegateNode.setOutput("ID", new OUTPUT<>(id));
            delegateNode.setOutput("OpType", new OUTPUT<>(RowOpType.valueOf(op)));

            newCtx.run();
        } catch (Exception e) {
            log.error("OracleTableListenerJob 执行异常", e);
        }
    }
}
