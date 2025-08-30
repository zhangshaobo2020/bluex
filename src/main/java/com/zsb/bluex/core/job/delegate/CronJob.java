package com.zsb.bluex.core.job.delegate;

import com.zsb.bluex.core.def.ControlDef;
import com.zsb.bluex.core.def.ParamDef;
import com.zsb.bluex.core.graph.GraphView;
import com.zsb.bluex.core.launch.MetaHolder;
import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.delegate.DelegateNode;
import com.zsb.bluex.core.job.EventDelegate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;
import java.util.concurrent.ScheduledFuture;

@Slf4j
public class CronJob extends EventDelegate {

    public CronJob() {
    }

    public CronJob(GraphView graphView, String cronExpression) {
        super(graphView);
        this.cronExpression = cronExpression;
    }

    private String cronExpression;

    private ThreadPoolTaskScheduler taskScheduler;
    private ScheduledFuture<?> future;

    @Override
    public void start() throws Exception {
        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setThreadNamePrefix("CronJob-Thread-");
        taskScheduler.setPoolSize(1);
        taskScheduler.initialize();

        future = taskScheduler.schedule(() -> {
            try {
                /* 执行开始 */
                ExecutionContext newCtx = graphView.buildExecCtx();
                DelegateNode delegateNode = (DelegateNode) newCtx.findStartupNode();
                delegateNode.setOutput("Now", new OUTPUT<>(LocalDateTime.now()));
                newCtx.run();
                /* 执行结束 */
            } catch (Exception e) {
                log.error("CronJob 执行异常", e);
            }
        }, new CronTrigger(cronExpression));
    }

    @Override
    public void end() {
        try {
            if (future != null) {
                future.cancel(true);
            }
            if (taskScheduler != null) {
                taskScheduler.shutdown();
            }
        } catch (Exception e) {
            log.error("关闭 CronJob 出错", e);
        }
    }

    @Override
    public ControlDef provideDefinition() {
        ControlDef def = new ControlDef();
        def.setName("CronJob");
        def.setDisplayName("定时任务");
        def.setCategory("事件委托|CronJob");
        def.setQualifiedName("DELEGATE:CronJob");
        def.setSignature("DELEGATE:CronJob");
        def.setDelegate(true);

        // 输出：Exec 节点
        def.getOutputExecDefs().add(new ParamDef("Exec"));

        // 输出：触发时间
        def.getOutputParamDefs().add(
                new ParamDef(
                        "Now",
                        MetaHolder.PRIMITIVE_DEFINITION.get("java.time.LocalDateTime")
                )
        );
        return def;
    }
}
