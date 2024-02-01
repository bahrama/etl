package com.tehcf.etl.model.list;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonRootName("List")
public class ListP implements Serializable {
    public String pid;
    public String productName;
    public String productNameEn;
    public String productSku;
    public String productImage;
    public String productWeight;
    public String productType;
    public Object productUnit;
    public String categoryName;
    public int listingCount;
    public String sellPrice;
    public String remark;
    public Object addMarkStatus;
    public Date createTime;
    public Object isVideo;
    public int saleStatus;
    public int listedNum;
    public Object supplierName;
    public Object supplierId;
    public String categoryId;
    public String sourceFrom;
    public List shippingCountryCodes;

    public ListP(){}
}
