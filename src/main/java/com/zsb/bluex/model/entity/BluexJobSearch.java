package com.zsb.bluex.model.entity;

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
 * @since 2025-08-28
 */
@TableName("BLUEX_JOB")
public class BluexJobSearch implements Serializable {

    /**
     * 任务编号
     */
    @TableField("JOB_NO")
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
     * 获取: 任务类型
     */
    public String getJobType() {
        return jobType;
    }

    /**
     * 设置: 任务类型
     */
    public void setJobType(String jobType) {
        this.jobType = jobType;
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

}