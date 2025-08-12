package com.zsb.bluex.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 任务信息（总览）
 * </p>
 *
 * @author dev3
 * @since 2025-08-12
 */
@TableName("BLUEX_TASK")
public class BluexTask extends Model<BluexTask> {

    /**
    * 所有字段名
    */
    public static class Columns {
        /**
        * 任务编号
        */
        public static final String taskNo = "TASK_NO";
        /**
        * 任务名称
        */
        public static final String taskName = "TASK_NAME";
        /**
        * 任务描述
        */
        public static final String taskDesc = "TASK_DESC";
        /**
        * 创建时间
        */
        public static final String createTime = "CREATE_TIME";
        /**
        * 更新时间
        */
        public static final String updateTime = "UPDATE_TIME";
        /**
        * JSON内容
        */
        public static final String jsonContent = "JSON_CONTENT";
    }

    /**
     * 任务编号
     */
    @TableId("TASK_NO")
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
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    /**
     * JSON内容
     */
    @TableField("JSON_CONTENT")
    private String jsonContent;


    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
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

    public String getJsonContent() {
        return jsonContent;
    }

    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent;
    }

    @Override
    public Serializable pkVal() {
        return this.taskNo;
    }

    @Override
    public String toString() {
        return "BluexTask{" +
        "taskNo=" + taskNo +
        ", taskName=" + taskName +
        ", taskDesc=" + taskDesc +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", jsonContent=" + jsonContent +
        "}";
    }
}
