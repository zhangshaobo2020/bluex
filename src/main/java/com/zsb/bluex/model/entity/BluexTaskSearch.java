package com.zsb.bluex.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 任务信息（总览）, 查询对象
 * </p>
 *
 * @author dev3
 * @since 2025-08-12
 */
@TableName("BLUEX_TASK")
public class BluexTaskSearch implements Serializable {

    /**
     * 任务编号
     */
    @TableField("TASK_NO")
    private String taskNo;

    /**
     * 任务名称
     */
    @TableField("TASK_NAME")
    private String taskName;

    /**
     * 任务描述
     */
    @TableField("TASK_DESC")
    private String taskDesc;

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
     * JSON内容
     */
    @TableField("JSON_CONTENT")
    private String jsonContent;


    /**
     * 获取: 任务编号
     */
    public String getTaskNo() {
        return taskNo;
    }

    /**
     * 设置: 任务编号
     */
    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * 获取: 任务名称
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * 设置: 任务名称
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * 获取: 任务描述
     */
    public String getTaskDesc() {
        return taskDesc;
    }

    /**
     * 设置: 任务描述
     */
    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
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
     * 获取: JSON内容
     */
    public String getJsonContent() {
        return jsonContent;
    }

    /**
     * 设置: JSON内容
     */
    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent;
    }

}