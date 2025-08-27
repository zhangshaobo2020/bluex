package com.zsb.bluex.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 服务信息, 查询对象
 * </p>
 *
 * @author ${author}
 * @since 2025-08-27
 */
@TableName("BLUEX_SERVICE")
public class BluexServiceSearch implements Serializable {

    /**
     * 服务编号
     */
    @TableField("SERVICE_NO")
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


    /**
     * 获取: 服务编号
     */
    public String getServiceNo() {
        return serviceNo;
    }

    /**
     * 设置: 服务编号
     */
    public void setServiceNo(String serviceNo) {
        this.serviceNo = serviceNo;
    }

    /**
     * 获取: 服务名称
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * 设置: 服务名称
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * 获取: 服务描述
     */
    public String getServiceDesc() {
        return serviceDesc;
    }

    /**
     * 设置: 服务描述
     */
    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
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
     * 获取: 服务类型
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * 设置: 服务类型
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * 获取: 参数1
     */
    public String getServiceParam1() {
        return serviceParam1;
    }

    /**
     * 设置: 参数1
     */
    public void setServiceParam1(String serviceParam1) {
        this.serviceParam1 = serviceParam1;
    }

    /**
     * 获取: 参数2
     */
    public String getServiceParam2() {
        return serviceParam2;
    }

    /**
     * 设置: 参数2
     */
    public void setServiceParam2(String serviceParam2) {
        this.serviceParam2 = serviceParam2;
    }

    /**
     * 获取: 参数3
     */
    public String getServiceParam3() {
        return serviceParam3;
    }

    /**
     * 设置: 参数3
     */
    public void setServiceParam3(String serviceParam3) {
        this.serviceParam3 = serviceParam3;
    }

    /**
     * 获取: 参数4
     */
    public String getServiceParam4() {
        return serviceParam4;
    }

    /**
     * 设置: 参数4
     */
    public void setServiceParam4(String serviceParam4) {
        this.serviceParam4 = serviceParam4;
    }

    /**
     * 获取: 参数5
     */
    public String getServiceParam5() {
        return serviceParam5;
    }

    /**
     * 设置: 参数5
     */
    public void setServiceParam5(String serviceParam5) {
        this.serviceParam5 = serviceParam5;
    }

    /**
     * 获取: 参数6
     */
    public String getServiceParam6() {
        return serviceParam6;
    }

    /**
     * 设置: 参数6
     */
    public void setServiceParam6(String serviceParam6) {
        this.serviceParam6 = serviceParam6;
    }

    /**
     * 获取: 参数7
     */
    public String getServiceParam7() {
        return serviceParam7;
    }

    /**
     * 设置: 参数7
     */
    public void setServiceParam7(String serviceParam7) {
        this.serviceParam7 = serviceParam7;
    }

    /**
     * 获取: 参数8
     */
    public String getServiceParam8() {
        return serviceParam8;
    }

    /**
     * 设置: 参数8
     */
    public void setServiceParam8(String serviceParam8) {
        this.serviceParam8 = serviceParam8;
    }

    /**
     * 获取: 参数9
     */
    public String getServiceParam9() {
        return serviceParam9;
    }

    /**
     * 设置: 参数9
     */
    public void setServiceParam9(String serviceParam9) {
        this.serviceParam9 = serviceParam9;
    }

    /**
     * 获取: 参数10
     */
    public String getServiceParam10() {
        return serviceParam10;
    }

    /**
     * 设置: 参数10
     */
    public void setServiceParam10(String serviceParam10) {
        this.serviceParam10 = serviceParam10;
    }

}