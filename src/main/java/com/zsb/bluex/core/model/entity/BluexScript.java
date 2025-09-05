package com.zsb.bluex.core.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * Groovy脚本表
 * </p>
 *
 * @author ${author}
 * @since 2025-09-05
 */
@TableName("BLUEX_SCRIPT")
public class BluexScript extends Model<BluexScript> {

    /**
    * 所有字段名
    */
    public static class Columns {
        /**
        * 脚本编号
        */
        public static final String scriptNo = "SCRIPT_NO";
        /**
        * 状态
        */
        public static final String rowState = "ROW_STATE";
        /**
        * 脚本名称
        */
        public static final String scriptName = "SCRIPT_NAME";
        /**
        * 脚本描述
        */
        public static final String scriptDesc = "SCRIPT_DESC";
        /**
        * 创建时间
        */
        public static final String createTime = "CREATE_TIME";
        /**
        * 更新时间
        */
        public static final String updateTime = "UPDATE_TIME";
        /**
        * 脚本内容
        */
        public static final String scriptContent = "SCRIPT_CONTENT";
    }

    /**
     * 脚本编号
     */
    @TableId("SCRIPT_NO")
    private String scriptNo;

    /**
     * 状态
     */
    @TableField("ROW_STATE")
    private String rowState;

    /**
     * 脚本名称
     */
    @TableField("SCRIPT_NAME")
    private String scriptName;

    /**
     * 脚本描述
     */
    @TableField("SCRIPT_DESC")
    private String scriptDesc;

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
     * 脚本内容
     */
    @TableField("SCRIPT_CONTENT")
    private String scriptContent;


    public String getScriptNo() {
        return scriptNo;
    }

    public void setScriptNo(String scriptNo) {
        this.scriptNo = scriptNo;
    }

    public String getRowState() {
        return rowState;
    }

    public void setRowState(String rowState) {
        this.rowState = rowState;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getScriptDesc() {
        return scriptDesc;
    }

    public void setScriptDesc(String scriptDesc) {
        this.scriptDesc = scriptDesc;
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

    public String getScriptContent() {
        return scriptContent;
    }

    public void setScriptContent(String scriptContent) {
        this.scriptContent = scriptContent;
    }

    @Override
    public Serializable pkVal() {
        return this.scriptNo;
    }

    @Override
    public String toString() {
        return "BluexScript{" +
        "scriptNo=" + scriptNo +
        ", rowState=" + rowState +
        ", scriptName=" + scriptName +
        ", scriptDesc=" + scriptDesc +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", scriptContent=" + scriptContent +
        "}";
    }
}
