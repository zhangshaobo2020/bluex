package com.zsb.bluex.model.entity;

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
 * @since 2025-08-28
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
    }

    /**
     * 任务编号
     */
    @TableId("JOB_NO")
    private String jobNo;

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


    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
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

    @Override
    public Serializable pkVal() {
        return this.jobNo;
    }

    @Override
    public String toString() {
        return "BluexJob{" +
        "jobNo=" + jobNo +
        ", jobName=" + jobName +
        ", jobDesc=" + jobDesc +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", jobType=" + jobType +
        ", cronExpression=" + cronExpression +
        ", filePath=" + filePath +
        ", httpMethod=" + httpMethod +
        ", httpUrlMapping=" + httpUrlMapping +
        "}";
    }
}
