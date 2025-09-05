package com.zsb.bluex.core.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 执行任务表, 查询对象
 * </p>
 *
 * @author ${author}
 * @since 2025-09-05
 */
@TableName("BLUEX_JOB")
public class BluexJobSearch implements Serializable {

    /**
     * 任务编号
     */
    @TableField("JOB_NO")
    private String jobNo;

    /**
     * 状态
     */
    @TableField("ROW_STATE")
    private String rowState;

    /**
     * 任务名称
     */
    @TableField("JOB_NAME")
    private String jobName;

    /**
     * 任务描述
     */
    @TableField("JOB_DESC")
    private String jobDesc;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 创建时间 - 开始
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTimeStart;

    /**
     * 创建时间 - 结束
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTimeEnd;
    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    /**
     * 更新时间 - 开始
     */
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTimeStart;

    /**
     * 更新时间 - 结束
     */
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTimeEnd;
    /**
     * 程序类型
     */
    @TableField("PROGRAM_TYPE")
    private String programType;

    /**
     * 程序编号
     */
    @TableField("PROGRAM_NO")
    private String programNo;

    /**
     * CRON表达式
     */
    @TableField("CRON_EXPRESSION")
    private String cronExpression;

    /**
     * 文件系统监听路径
     */
    @TableField("FILE_PATH")
    private String filePath;

    /**
     * HTTP请求方式
     */
    @TableField("HTTP_METHOD")
    private String httpMethod;

    /**
     * HTTP的URL映射
     */
    @TableField("HTTP_URL_MAPPING")
    private String httpUrlMapping;

    /**
     * WebSocket的Endpoint
     */
    @TableField("WS_ENDPOINT")
    private String wsEndpoint;

    /**
     * MQ驱动名称
     */
    @TableField("MQ_DRIVER_NAME")
    private String mqDriverName;

    /**
     * MQ地址
     */
    @TableField("MQ_URI")
    private String mqUri;

    /**
     * MQ登录用户名
     */
    @TableField("MQ_USERNAME")
    private String mqUsername;

    /**
     * MQ登录密码
     */
    @TableField("MQ_PASSWORD")
    private String mqPassword;

    /**
     * MQ队列或Topic名称
     */
    @TableField("MQ_DESTINATION_NAME")
    private String mqDestinationName;

    /**
     * MQ模式：发布/订阅、点对点(Y/N)
     */
    @TableField("MQ_PUB_SUB_DOMAIN")
    private String mqPubSubDomain;

    /**
     * MQ队列管理器名称
     */
    @TableField("MQ_QUEUE_MANAGER")
    private String mqQueueManager;

    /**
     * MQ通信通道名称
     */
    @TableField("MQ_CHANNEL")
    private String mqChannel;

    /**
     * MQ主机和端口列表
     */
    @TableField("MQ_CONNECTION_NAME_LIST")
    private String mqConnectionNameList;

    /**
     * MQ消息编码类型
     */
    @TableField("MQ_CCS_ID")
    private String mqCcsId;

    /**
     * 数据库驱动名称
     */
    @TableField("DB_DRIVER_NAME")
    private String dbDriverName;

    /**
     * 数据库连接地址
     */
    @TableField("DB_URL")
    private String dbUrl;

    /**
     * 数据库用户名
     */
    @TableField("DB_USERNAME")
    private String dbUsername;

    /**
     * 数据库密码
     */
    @TableField("DB_PASSWORD")
    private String dbPassword;

    /**
     * 数据库实体类
     */
    @TableField("DB_ENTITY")
    private String dbEntity;

    /**
     * 数据表是否监听INSERT(Y/N)
     */
    @TableField("DB_LISTEN_INSERT")
    private String dbListenInsert;

    /**
     * 数据表是否监听UPDATE(Y/N)
     */
    @TableField("DB_LISTEN_UPDATE")
    private String dbListenUpdate;

    /**
     * 数据表是否监听DELETE(Y/N)
     */
    @TableField("DB_LISTEN_DELETE")
    private String dbListenDelete;


    /**
     * 获取: 任务编号
     */
    public String getJobNo() {
        return jobNo;
    }

    /**
     * 设置: 任务编号
     */
    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    /**
     * 获取: 状态
     */
    public String getRowState() {
        return rowState;
    }

    /**
     * 设置: 状态
     */
    public void setRowState(String rowState) {
        this.rowState = rowState;
    }

    /**
     * 获取: 任务名称
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * 设置: 任务名称
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * 获取: 任务描述
     */
    public String getJobDesc() {
        return jobDesc;
    }

    /**
     * 设置: 任务描述
     */
    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    /**
     * 获取: 创建时间
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * 设置: 创建时间
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取: 创建时间 - 开始
     */
    public LocalDateTime getCreateTimeStart() {
        return createTimeStart;
    }

    /**
     * 设置: 创建时间 - 开始
     */
    public void setCreateTimeStart(LocalDateTime createTime) {
        this.createTimeStart = createTime;
    }

    /**
     * 获取: 创建时间 - 结束
     */
    public LocalDateTime getCreateTimeEnd() {
        return createTimeEnd;
    }

    /**
     * 设置: 创建时间 - 结束
     */
    public void setCreateTimeEnd(LocalDateTime createTime) {
        this.createTimeEnd = createTime;
        }
    /**
     * 获取: 更新时间
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置: 更新时间
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取: 更新时间 - 开始
     */
    public LocalDateTime getUpdateTimeStart() {
        return updateTimeStart;
    }

    /**
     * 设置: 更新时间 - 开始
     */
    public void setUpdateTimeStart(LocalDateTime updateTime) {
        this.updateTimeStart = updateTime;
    }

    /**
     * 获取: 更新时间 - 结束
     */
    public LocalDateTime getUpdateTimeEnd() {
        return updateTimeEnd;
    }

    /**
     * 设置: 更新时间 - 结束
     */
    public void setUpdateTimeEnd(LocalDateTime updateTime) {
        this.updateTimeEnd = updateTime;
        }
    /**
     * 获取: 程序类型
     */
    public String getProgramType() {
        return programType;
    }

    /**
     * 设置: 程序类型
     */
    public void setProgramType(String programType) {
        this.programType = programType;
    }

    /**
     * 获取: 程序编号
     */
    public String getProgramNo() {
        return programNo;
    }

    /**
     * 设置: 程序编号
     */
    public void setProgramNo(String programNo) {
        this.programNo = programNo;
    }

    /**
     * 获取: CRON表达式
     */
    public String getCronExpression() {
        return cronExpression;
    }

    /**
     * 设置: CRON表达式
     */
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    /**
     * 获取: 文件系统监听路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 设置: 文件系统监听路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 获取: HTTP请求方式
     */
    public String getHttpMethod() {
        return httpMethod;
    }

    /**
     * 设置: HTTP请求方式
     */
    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    /**
     * 获取: HTTP的URL映射
     */
    public String getHttpUrlMapping() {
        return httpUrlMapping;
    }

    /**
     * 设置: HTTP的URL映射
     */
    public void setHttpUrlMapping(String httpUrlMapping) {
        this.httpUrlMapping = httpUrlMapping;
    }

    /**
     * 获取: WebSocket的Endpoint
     */
    public String getWsEndpoint() {
        return wsEndpoint;
    }

    /**
     * 设置: WebSocket的Endpoint
     */
    public void setWsEndpoint(String wsEndpoint) {
        this.wsEndpoint = wsEndpoint;
    }

    /**
     * 获取: MQ驱动名称
     */
    public String getMqDriverName() {
        return mqDriverName;
    }

    /**
     * 设置: MQ驱动名称
     */
    public void setMqDriverName(String mqDriverName) {
        this.mqDriverName = mqDriverName;
    }

    /**
     * 获取: MQ地址
     */
    public String getMqUri() {
        return mqUri;
    }

    /**
     * 设置: MQ地址
     */
    public void setMqUri(String mqUri) {
        this.mqUri = mqUri;
    }

    /**
     * 获取: MQ登录用户名
     */
    public String getMqUsername() {
        return mqUsername;
    }

    /**
     * 设置: MQ登录用户名
     */
    public void setMqUsername(String mqUsername) {
        this.mqUsername = mqUsername;
    }

    /**
     * 获取: MQ登录密码
     */
    public String getMqPassword() {
        return mqPassword;
    }

    /**
     * 设置: MQ登录密码
     */
    public void setMqPassword(String mqPassword) {
        this.mqPassword = mqPassword;
    }

    /**
     * 获取: MQ队列或Topic名称
     */
    public String getMqDestinationName() {
        return mqDestinationName;
    }

    /**
     * 设置: MQ队列或Topic名称
     */
    public void setMqDestinationName(String mqDestinationName) {
        this.mqDestinationName = mqDestinationName;
    }

    /**
     * 获取: MQ模式：发布/订阅、点对点(Y/N)
     */
    public String getMqPubSubDomain() {
        return mqPubSubDomain;
    }

    /**
     * 设置: MQ模式：发布/订阅、点对点(Y/N)
     */
    public void setMqPubSubDomain(String mqPubSubDomain) {
        this.mqPubSubDomain = mqPubSubDomain;
    }

    /**
     * 获取: MQ队列管理器名称
     */
    public String getMqQueueManager() {
        return mqQueueManager;
    }

    /**
     * 设置: MQ队列管理器名称
     */
    public void setMqQueueManager(String mqQueueManager) {
        this.mqQueueManager = mqQueueManager;
    }

    /**
     * 获取: MQ通信通道名称
     */
    public String getMqChannel() {
        return mqChannel;
    }

    /**
     * 设置: MQ通信通道名称
     */
    public void setMqChannel(String mqChannel) {
        this.mqChannel = mqChannel;
    }

    /**
     * 获取: MQ主机和端口列表
     */
    public String getMqConnectionNameList() {
        return mqConnectionNameList;
    }

    /**
     * 设置: MQ主机和端口列表
     */
    public void setMqConnectionNameList(String mqConnectionNameList) {
        this.mqConnectionNameList = mqConnectionNameList;
    }

    /**
     * 获取: MQ消息编码类型
     */
    public String getMqCcsId() {
        return mqCcsId;
    }

    /**
     * 设置: MQ消息编码类型
     */
    public void setMqCcsId(String mqCcsId) {
        this.mqCcsId = mqCcsId;
    }

    /**
     * 获取: 数据库驱动名称
     */
    public String getDbDriverName() {
        return dbDriverName;
    }

    /**
     * 设置: 数据库驱动名称
     */
    public void setDbDriverName(String dbDriverName) {
        this.dbDriverName = dbDriverName;
    }

    /**
     * 获取: 数据库连接地址
     */
    public String getDbUrl() {
        return dbUrl;
    }

    /**
     * 设置: 数据库连接地址
     */
    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    /**
     * 获取: 数据库用户名
     */
    public String getDbUsername() {
        return dbUsername;
    }

    /**
     * 设置: 数据库用户名
     */
    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    /**
     * 获取: 数据库密码
     */
    public String getDbPassword() {
        return dbPassword;
    }

    /**
     * 设置: 数据库密码
     */
    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    /**
     * 获取: 数据库实体类
     */
    public String getDbEntity() {
        return dbEntity;
    }

    /**
     * 设置: 数据库实体类
     */
    public void setDbEntity(String dbEntity) {
        this.dbEntity = dbEntity;
    }

    /**
     * 获取: 数据表是否监听INSERT(Y/N)
     */
    public String getDbListenInsert() {
        return dbListenInsert;
    }

    /**
     * 设置: 数据表是否监听INSERT(Y/N)
     */
    public void setDbListenInsert(String dbListenInsert) {
        this.dbListenInsert = dbListenInsert;
    }

    /**
     * 获取: 数据表是否监听UPDATE(Y/N)
     */
    public String getDbListenUpdate() {
        return dbListenUpdate;
    }

    /**
     * 设置: 数据表是否监听UPDATE(Y/N)
     */
    public void setDbListenUpdate(String dbListenUpdate) {
        this.dbListenUpdate = dbListenUpdate;
    }

    /**
     * 获取: 数据表是否监听DELETE(Y/N)
     */
    public String getDbListenDelete() {
        return dbListenDelete;
    }

    /**
     * 设置: 数据表是否监听DELETE(Y/N)
     */
    public void setDbListenDelete(String dbListenDelete) {
        this.dbListenDelete = dbListenDelete;
    }

}