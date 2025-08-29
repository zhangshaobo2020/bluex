package com.zsb.bluex.core.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 可视化程序表, 查询对象
 * </p>
 *
 * @author ${author}
 * @since 2025-08-28
 */
@TableName("BLUEX_PROGRAM")
public class BluexProgramSearch implements Serializable {

    /**
     * 程序编号
     */
    @TableField("PROGRAM_NO")
    private String programNo;

    /**
     * 程序名称
     */
    @TableField("PROGRAM_NAME")
    private String programName;

    /**
     * 程序描述
     */
    @TableField("PROGRAM_DESC")
    private String programDesc;

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
     * 获取: 程序名称
     */
    public String getProgramName() {
        return programName;
    }

    /**
     * 设置: 程序名称
     */
    public void setProgramName(String programName) {
        this.programName = programName;
    }

    /**
     * 获取: 程序描述
     */
    public String getProgramDesc() {
        return programDesc;
    }

    /**
     * 设置: 程序描述
     */
    public void setProgramDesc(String programDesc) {
        this.programDesc = programDesc;
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