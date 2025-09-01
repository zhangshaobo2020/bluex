package com.zsb.bluex.core.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 执行任务表
 * </p>
 *
 * @author ${author}
 * @since 2025-09-01
 */
@TableName("BLUEX_JOB")
public class BluexJob extends Model<BluexJob> {

    /**
    * 所有字段名
    */
    public static class Columns {
        /**
        * 任务编号
        */
        public static final String jobNo = "JOB_NO";
        /**
        * 状态
        */
        public static final String rowState = "ROW_STATE";
        /**
        * 任务名称
        */
        public static final String jobName = "JOB_NAME";
        /**
        * 任务描述
        */
        public static final String jobDesc = "JOB_DESC";
        /**
        * 创建时间
        */
        public static final String createTime = "CREATE_TIME";
        /**
        * 更新时间
        */
        public static final String updateTime = "UPDATE_TIME";
        /**
        * 任务类型
        */
        public static final String jobType = "JOB_TYPE";
        /**
        * 绑定的程序编号
        */
        public static final String programNo = "PROGRAM_NO";
        /**
        * CRON表达式
        */
        public static final String cronExpression = "CRON_EXPRESSION";
        /**
        * 文件系统监听路径
        */
        public static final String filePath = "FILE_PATH";
        /**
        * HTTP请求方式
        */
        public static final String httpMethod = "HTTP_METHOD";
        /**
        * HTTP的URL映射
        */
        public static final String httpUrlMapping = "HTTP_URL_MAPPING";
        /**
        * WebSocket的Endpoint
        */
        public static final String wsEndpoint = "WS_ENDPOINT";
        /**
        * MQ驱动名称
        */
        public static final String mqDriverName = "MQ_DRIVER_NAME";
        /**
        * MQ地址
        */
        public static final String mqUri = "MQ_URI";
        /**
        * MQ登录用户名
        */
        public static final String mqUsername = "MQ_USERNAME";
        /**
        * MQ登录密码
        */
        public static final String mqPassword = "MQ_PASSWORD";
        /**
        * MQ队列或Topic名称
        */
        public static final String mqDestinationName = "MQ_DESTINATION_NAME";
        /**
        * MQ模式：发布/订阅、点对点(Y/N)
        */
        public static final String mqPubSubDomain = "MQ_PUB_SUB_DOMAIN";
        /**
        * MQ队列管理器名称
        */
        public static final String mqQueueManager = "MQ_QUEUE_MANAGER";
        /**
        * MQ通信通道名称
        */
        public static final String mqChannel = "MQ_CHANNEL";
        /**
        * MQ主机和端口列表
        */
        public static final String mqConnectionNameList = "MQ_CONNECTION_NAME_LIST";
        /**
        * MQ消息编码类型
        */
        public static final String mqCcsId = "MQ_CCS_ID";
        /**
        * 数据库驱动名称
        */
        public static final String dbDriverName = "DB_DRIVER_NAME";
        /**
        * 数据库连接地址
        */
        public static final String dbUrl = "DB_URL";
        /**
        * 数据库用户名
        */
        public static final String dbUsername = "DB_USERNAME";
        /**
        * 数据库密码
        */
        public static final String dbPassword = "DB_PASSWORD";
        /**
        * 数据库实体类
        */
        public static final String dbEntity = "DB_ENTITY";
        /**
        * 数据表是否监听INSERT(Y/N)
        */
        public static final String dbListenInsert = "DB_LISTEN_INSERT";
        /**
        * 数据表是否监听UPDATE(Y/N)
        */
        public static final String dbListenUpdate = "DB_LISTEN_UPDATE";
        /**
        * 数据表是否监听DELETE(Y/N)
        */
        public static final String dbListenDelete = "DB_LISTEN_DELETE";
    }

    /**
     * 任务编号
     */
    @TableId("JOB_NO")
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
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    /**
     * 任务类型
     */
    @TableField("JOB_TYPE")
    private String jobType;

    /**
     * 绑定的程序编号
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


    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public String getRowState() {
        return rowState;
    }

    public void setRowState(String rowState) {
        this.rowState = rowState;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getProgramNo() {
        return programNo;
    }

    public void setProgramNo(String programNo) {
        this.programNo = programNo;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getHttpUrlMapping() {
        return httpUrlMapping;
    }

    public void setHttpUrlMapping(String httpUrlMapping) {
        this.httpUrlMapping = httpUrlMapping;
    }

    public String getWsEndpoint() {
        return wsEndpoint;
    }

    public void setWsEndpoint(String wsEndpoint) {
        this.wsEndpoint = wsEndpoint;
    }

    public String getMqDriverName() {
        return mqDriverName;
    }

    public void setMqDriverName(String mqDriverName) {
        this.mqDriverName = mqDriverName;
    }

    public String getMqUri() {
        return mqUri;
    }

    public void setMqUri(String mqUri) {
        this.mqUri = mqUri;
    }

    public String getMqUsername() {
        return mqUsername;
    }

    public void setMqUsername(String mqUsername) {
        this.mqUsername = mqUsername;
    }

    public String getMqPassword() {
        return mqPassword;
    }

    public void setMqPassword(String mqPassword) {
        this.mqPassword = mqPassword;
    }

    public String getMqDestinationName() {
        return mqDestinationName;
    }

    public void setMqDestinationName(String mqDestinationName) {
        this.mqDestinationName = mqDestinationName;
    }

    public String getMqPubSubDomain() {
        return mqPubSubDomain;
    }

    public void setMqPubSubDomain(String mqPubSubDomain) {
        this.mqPubSubDomain = mqPubSubDomain;
    }

    public String getMqQueueManager() {
        return mqQueueManager;
    }

    public void setMqQueueManager(String mqQueueManager) {
        this.mqQueueManager = mqQueueManager;
    }

    public String getMqChannel() {
        return mqChannel;
    }

    public void setMqChannel(String mqChannel) {
        this.mqChannel = mqChannel;
    }

    public String getMqConnectionNameList() {
        return mqConnectionNameList;
    }

    public void setMqConnectionNameList(String mqConnectionNameList) {
        this.mqConnectionNameList = mqConnectionNameList;
    }

    public String getMqCcsId() {
        return mqCcsId;
    }

    public void setMqCcsId(String mqCcsId) {
        this.mqCcsId = mqCcsId;
    }

    public String getDbDriverName() {
        return dbDriverName;
    }

    public void setDbDriverName(String dbDriverName) {
        this.dbDriverName = dbDriverName;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbEntity() {
        return dbEntity;
    }

    public void setDbEntity(String dbEntity) {
        this.dbEntity = dbEntity;
    }

    public String getDbListenInsert() {
        return dbListenInsert;
    }

    public void setDbListenInsert(String dbListenInsert) {
        this.dbListenInsert = dbListenInsert;
    }

    public String getDbListenUpdate() {
        return dbListenUpdate;
    }

    public void setDbListenUpdate(String dbListenUpdate) {
        this.dbListenUpdate = dbListenUpdate;
    }

    public String getDbListenDelete() {
        return dbListenDelete;
    }

    public void setDbListenDelete(String dbListenDelete) {
        this.dbListenDelete = dbListenDelete;
    }

    @Override
    public Serializable pkVal() {
        return this.jobNo;
    }

    @Override
    public String toString() {
        return "BluexJob{" +
        "jobNo=" + jobNo +
        ", rowState=" + rowState +
        ", jobName=" + jobName +
        ", jobDesc=" + jobDesc +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", jobType=" + jobType +
        ", programNo=" + programNo +
        ", cronExpression=" + cronExpression +
        ", filePath=" + filePath +
        ", httpMethod=" + httpMethod +
        ", httpUrlMapping=" + httpUrlMapping +
        ", wsEndpoint=" + wsEndpoint +
        ", mqDriverName=" + mqDriverName +
        ", mqUri=" + mqUri +
        ", mqUsername=" + mqUsername +
        ", mqPassword=" + mqPassword +
        ", mqDestinationName=" + mqDestinationName +
        ", mqPubSubDomain=" + mqPubSubDomain +
        ", mqQueueManager=" + mqQueueManager +
        ", mqChannel=" + mqChannel +
        ", mqConnectionNameList=" + mqConnectionNameList +
        ", mqCcsId=" + mqCcsId +
        ", dbDriverName=" + dbDriverName +
        ", dbUrl=" + dbUrl +
        ", dbUsername=" + dbUsername +
        ", dbPassword=" + dbPassword +
        ", dbEntity=" + dbEntity +
        ", dbListenInsert=" + dbListenInsert +
        ", dbListenUpdate=" + dbListenUpdate +
        ", dbListenDelete=" + dbListenDelete +
        "}";
    }
}
