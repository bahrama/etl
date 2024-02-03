package com.tehcf.etl.entity;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity(name="product_cj")
@Table(name="product_cj")
@Getter
@Setter
@EqualsAndHashCode
@Cacheable(value = false)
@NoArgsConstructor
public class ProductCj extends BaseEntity{
    private String pid;
    @Column(length = 1000 , name = "product_name")
    private String productName;
    @Column(length = 1000 , name = "product_Sku")
    private String productSku;
    @Column(length = 1000 , name = "product_Image")
    private String productImage;
    @Column(length = 1000 , name = "product_Weight")
    private String productWeight;
    @Column(length = 1000 , name = "product_Type")
    private String productType;
    @Column(length = 1000 , name = "category_Name")
    private String categoryName;
    @Column(name = "sell_Price")
    private String sellPrice;
    @Column(name = "create_Time")
    private Date createTime;
    @Column(length = 2000)
    private String remark;
    @Column(name = "category_Id")
    private String categoryId;


}
