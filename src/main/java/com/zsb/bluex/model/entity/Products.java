package com.zsb.bluex.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zsb.bluex.core.anno.BluexClass;

import java.io.Serializable;

/**
 * <p>
 * 商品信息表，用于存储商品的基本数据
 * </p>
 *
 * @author ${author}
 * @since 2025-08-29
 */
@BluexClass()
@TableName("PRODUCTS")
public class Products extends Model<Products> {

    /**
    * 所有字段名
    */
    public static class Columns {
        /**
        * 商品编号，主键
        */
        public static final String productId = "PRODUCT_ID";
        /**
        * 商品名称
        */
        public static final String productName = "PRODUCT_NAME";
        /**
        * 商品价格（单位：元）
        */
        public static final String price = "PRICE";
        /**
        * 库存数量
        */
        public static final String stock = "STOCK";
        /**
        * 创建时间，默认当前时间
        */
        public static final String createDate = "CREATE_DATE";
    }

    /**
     * 商品编号，主键
     */
    @TableId("PRODUCT_ID")
    private Long productId;

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
     * 库存数量
     */
    @TableField("STOCK")
    private Long stock;

    /**
     * 创建时间，默认当前时间
     */
    @TableField("CREATE_DATE")
    private LocalDateTime createDate;


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Override
    public Serializable pkVal() {
        return this.productId;
    }

    @Override
    public String toString() {
        return "Products{" +
        "productId=" + productId +
        ", productName=" + productName +
        ", price=" + price +
        ", stock=" + stock +
        ", createDate=" + createDate +
        "}";
    }
}
