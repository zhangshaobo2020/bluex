package com.zsb.bluex.core.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * Groovy脚本表, 查询对象
 * </p>
 *
 * @author ${author}
 * @since 2025-09-05
 */
@TableName("BLUEX_SCRIPT")
public class BluexScriptSearch implements Serializable {

    /**
     * 脚本编号
     */
    @TableField("SCRIPT_NO")
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
     * 脚本内容
     */
    @TableField("SCRIPT_CONTENT")
    private String scriptContent;


    /**
     * 获取: 脚本编号
     */
    public String getScriptNo() {
        return scriptNo;
    }

    /**
     * 设置: 脚本编号
     */
    public void setScriptNo(String scriptNo) {
        this.scriptNo = scriptNo;
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
     * 获取: 脚本名称
     */
    public String getScriptName() {
        return scriptName;
    }

    /**
     * 设置: 脚本名称
     */
    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    /**
     * 获取: 脚本描述
     */
    public String getScriptDesc() {
        return scriptDesc;
    }

    /**
     * 设置: 脚本描述
     */
    public void setScriptDesc(String scriptDesc) {
        this.scriptDesc = scriptDesc;
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
     * 获取: 脚本内容
     */
    public String getScriptContent() {
        return scriptContent;
    }

    /**
     * 设置: 脚本内容
     */
    public void setScriptContent(String scriptContent) {
        this.scriptContent = scriptContent;
    }

}