package com.zsb.bluex.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 商品信息表，用于存储商品的基本数据, 查询对象
 * </p>
 *
 * @author ${author}
 * @since 2025-09-01
 */
@TableName("PRODUCTS")
public class ProductsSearch implements Serializable {

    /**
     * 商品编号，主键
     */
    @TableField("PRODUCT_ID")
    private String productId;

    /**
     * 商品名称
     */
    @TableField("PRODUCT_NAME")
    private String productName;

    /**
     * 商品价格（单位：元）
     */
    @TableField("PRICE")
    private Double price;

    /**
     * 商品价格（单位：元） - 开始
     */
    @TableField("PRICE")
    private Double priceStart;

    /**
     * 商品价格（单位：元） - 结束
     */
    @TableField("PRICE")
    private Double priceEnd;
    /**
     * 库存数量
     */
    @TableField("STOCK")
    private Long stock;

    /**
     * 库存数量 - 开始
     */
    @TableField("STOCK")
    private Long stockStart;

    /**
     * 库存数量 - 结束
     */
    @TableField("STOCK")
    private Long stockEnd;
    /**
     * 创建时间，默认当前时间
     */
    @TableField("CREATE_DATE")
    private LocalDateTime createDate;

    /**
     * 创建时间，默认当前时间 - 开始
     */
    @TableField("CREATE_DATE")
    private LocalDateTime createDateStart;

    /**
     * 创建时间，默认当前时间 - 结束
     */
    @TableField("CREATE_DATE")
    private LocalDateTime createDateEnd;

    /**
     * 获取: 商品编号，主键
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 设置: 商品编号，主键
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * 获取: 商品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置: 商品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取: 商品价格（单位：元）
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置: 商品价格（单位：元）
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取: 商品价格（单位：元） - 开始
     */
    public Double getPriceStart() {
        return priceStart;
    }

    /**
     * 设置: 商品价格（单位：元） - 开始
     */
    public void setPriceStart(Double price) {
        this.priceStart = price;
    }

    /**
     * 获取: 商品价格（单位：元） - 结束
     */
    public Double getPriceEnd() {
        return priceEnd;
    }

    /**
     * 设置: 商品价格（单位：元） - 结束
     */
    public void setPriceEnd(Double price) {
        this.priceEnd = price;
        }
    /**
     * 获取: 库存数量
     */
    public Long getStock() {
        return stock;
    }

    /**
     * 设置: 库存数量
     */
    public void setStock(Long stock) {
        this.stock = stock;
    }

    /**
     * 获取: 库存数量 - 开始
     */
    public Long getStockStart() {
        return stockStart;
    }

    /**
     * 设置: 库存数量 - 开始
     */
    public void setStockStart(Long stock) {
        this.stockStart = stock;
    }

    /**
     * 获取: 库存数量 - 结束
     */
    public Long getStockEnd() {
        return stockEnd;
    }

    /**
     * 设置: 库存数量 - 结束
     */
    public void setStockEnd(Long stock) {
        this.stockEnd = stock;
        }
    /**
     * 获取: 创建时间，默认当前时间
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * 设置: 创建时间，默认当前时间
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取: 创建时间，默认当前时间 - 开始
     */
    public LocalDateTime getCreateDateStart() {
        return createDateStart;
    }

    /**
     * 设置: 创建时间，默认当前时间 - 开始
     */
    public void setCreateDateStart(LocalDateTime createDate) {
        this.createDateStart = createDate;
    }

    /**
     * 获取: 创建时间，默认当前时间 - 结束
     */
    public LocalDateTime getCreateDateEnd() {
        return createDateEnd;
    }

    /**
     * 设置: 创建时间，默认当前时间 - 结束
     */
    public void setCreateDateEnd(LocalDateTime createDate) {
        this.createDateEnd = createDate;
        }
}