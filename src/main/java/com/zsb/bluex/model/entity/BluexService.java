package com.zsb.bluex.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 服务信息
 * </p>
 *
 * @author ${author}
 * @since 2025-08-27
 */
@TableName("BLUEX_SERVICE")
public class BluexService extends Model<BluexService> {

    /**
    * 所有字段名
    */
    public static class Columns {
        /**
        * 服务编号
        */
        public static final String serviceNo = "SERVICE_NO";
        /**
        * 服务名称
        */
        public static final String serviceName = "SERVICE_NAME";
        /**
        * 服务描述
        */
        public static final String serviceDesc = "SERVICE_DESC";
        /**
        * 创建时间
        */
        public static final String createTime = "CREATE_TIME";
        /**
        * 更新时间
        */
        public static final String updateTime = "UPDATE_TIME";
        /**
        * 服务类型
        */
        public static final String serviceType = "SERVICE_TYPE";
        /**
        * 参数1
        */
        public static final String serviceParam1 = "SERVICE_PARAM1";
        /**
        * 参数2
        */
        public static final String serviceParam2 = "SERVICE_PARAM2";
        /**
        * 参数3
        */
        public static final String serviceParam3 = "SERVICE_PARAM3";
        /**
        * 参数4
        */
        public static final String serviceParam4 = "SERVICE_PARAM4";
        /**
        * 参数5
        */
        public static final String serviceParam5 = "SERVICE_PARAM5";
        /**
        * 参数6
        */
        public static final String serviceParam6 = "SERVICE_PARAM6";
        /**
        * 参数7
        */
        public static final String serviceParam7 = "SERVICE_PARAM7";
        /**
        * 参数8
        */
        public static final String serviceParam8 = "SERVICE_PARAM8";
        /**
        * 参数9
        */
        public static final String serviceParam9 = "SERVICE_PARAM9";
        /**
        * 参数10
        */
        public static final String serviceParam10 = "SERVICE_PARAM10";
    }

    /**
     * 服务编号
     */
    @TableId("SERVICE_NO")
    private String serviceNo;

    /**
     * 服务名称
     */
    @TableField("SERVICE_NAME")
    private String serviceName;

    /**
     * 服务描述
     */
    @TableField("SERVICE_DESC")
    private String serviceDesc;

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
     * 服务类型
     */
    @TableField("SERVICE_TYPE")
    private String serviceType;

    /**
     * 参数1
     */
    @TableField("SERVICE_PARAM1")
    private String serviceParam1;

    /**
     * 参数2
     */
    @TableField("SERVICE_PARAM2")
    private String serviceParam2;

    /**
     * 参数3
     */
    @TableField("SERVICE_PARAM3")
    private String serviceParam3;

    /**
     * 参数4
     */
    @TableField("SERVICE_PARAM4")
    private String serviceParam4;

    /**
     * 参数5
     */
    @TableField("SERVICE_PARAM5")
    private String serviceParam5;

    /**
     * 参数6
     */
    @TableField("SERVICE_PARAM6")
    private String serviceParam6;

    /**
     * 参数7
     */
    @TableField("SERVICE_PARAM7")
    private String serviceParam7;

    /**
     * 参数8
     */
    @TableField("SERVICE_PARAM8")
    private String serviceParam8;

    /**
     * 参数9
     */
    @TableField("SERVICE_PARAM9")
    private String serviceParam9;

    /**
     * 参数10
     */
    @TableField("SERVICE_PARAM10")
    private String serviceParam10;


    public String getServiceNo() {
        return serviceNo;
    }

    public void setServiceNo(String serviceNo) {
        this.serviceNo = serviceNo;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
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

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceParam1() {
        return serviceParam1;
    }

    public void setServiceParam1(String serviceParam1) {
        this.serviceParam1 = serviceParam1;
    }

    public String getServiceParam2() {
        return serviceParam2;
    }

    public void setServiceParam2(String serviceParam2) {
        this.serviceParam2 = serviceParam2;
    }

    public String getServiceParam3() {
        return serviceParam3;
    }

    public void setServiceParam3(String serviceParam3) {
        this.serviceParam3 = serviceParam3;
    }

    public String getServiceParam4() {
        return serviceParam4;
    }

    public void setServiceParam4(String serviceParam4) {
        this.serviceParam4 = serviceParam4;
    }

    public String getServiceParam5() {
        return serviceParam5;
    }

    public void setServiceParam5(String serviceParam5) {
        this.serviceParam5 = serviceParam5;
    }

    public String getServiceParam6() {
        return serviceParam6;
    }

    public void setServiceParam6(String serviceParam6) {
        this.serviceParam6 = serviceParam6;
    }

    public String getServiceParam7() {
        return serviceParam7;
    }

    public void setServiceParam7(String serviceParam7) {
        this.serviceParam7 = serviceParam7;
    }

    public String getServiceParam8() {
        return serviceParam8;
    }

    public void setServiceParam8(String serviceParam8) {
        this.serviceParam8 = serviceParam8;
    }

    public String getServiceParam9() {
        return serviceParam9;
    }

    public void setServiceParam9(String serviceParam9) {
        this.serviceParam9 = serviceParam9;
    }

    public String getServiceParam10() {
        return serviceParam10;
    }

    public void setServiceParam10(String serviceParam10) {
        this.serviceParam10 = serviceParam10;
    }

    @Override
    public Serializable pkVal() {
        return this.serviceNo;
    }

    @Override
    public String toString() {
        return "BluexService{" +
        "serviceNo=" + serviceNo +
        ", serviceName=" + serviceName +
        ", serviceDesc=" + serviceDesc +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", serviceType=" + serviceType +
        ", serviceParam1=" + serviceParam1 +
        ", serviceParam2=" + serviceParam2 +
        ", serviceParam3=" + serviceParam3 +
        ", serviceParam4=" + serviceParam4 +
        ", serviceParam5=" + serviceParam5 +
        ", serviceParam6=" + serviceParam6 +
        ", serviceParam7=" + serviceParam7 +
        ", serviceParam8=" + serviceParam8 +
        ", serviceParam9=" + serviceParam9 +
        ", serviceParam10=" + serviceParam10 +
        "}";
    }
}
