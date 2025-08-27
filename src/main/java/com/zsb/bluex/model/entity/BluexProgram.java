package com.zsb.bluex.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 可视化程序表
 * </p>
 *
 * @author ${author}
 * @since 2025-08-27
 */
@TableName("BLUEX_PROGRAM")
public class BluexProgram extends Model<BluexProgram> {

    /**
    * 所有字段名
    */
    public static class Columns {
        /**
        * 程序编号
        */
        public static final String programNo = "PROGRAM_NO";
        /**
        * 程序名称
        */
        public static final String programName = "PROGRAM_NAME";
        /**
        * 程序描述
        */
        public static final String programDesc = "PROGRAM_DESC";
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
     * 程序编号
     */
    @TableId("PROGRAM_NO")
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
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    /**
     * JSON内容
     */
    @TableField("JSON_CONTENT")
    private String jsonContent;


    public String getProgramNo() {
        return programNo;
    }

    public void setProgramNo(String programNo) {
        this.programNo = programNo;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramDesc() {
        return programDesc;
    }

    public void setProgramDesc(String programDesc) {
        this.programDesc = programDesc;
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
        return this.programNo;
    }

    @Override
    public String toString() {
        return "BluexProgram{" +
        "programNo=" + programNo +
        ", programName=" + programName +
        ", programDesc=" + programDesc +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", jsonContent=" + jsonContent +
        "}";
    }
}
