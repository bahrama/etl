package com.tehcf.etl.model.list;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductLst implements Serializable {
    public int code;
    public boolean result;
    public String message;
    public DataP data;
    public String requestId;
    public boolean success;

    public ProductLst(){}
}
