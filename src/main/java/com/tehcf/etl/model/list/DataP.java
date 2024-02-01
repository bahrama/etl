package com.tehcf.etl.model.list;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonRootName("Data")
public class DataP implements Serializable {
    public int pageNum;
    public int pageSize;
    public int total;
    public java.util.List<ListP> list;

    public DataP() {
    }
}
