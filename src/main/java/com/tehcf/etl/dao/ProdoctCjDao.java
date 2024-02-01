package com.tehcf.etl.dao;

import com.tehcf.etl.entity.ProductCj;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class ProdoctCjDao {

    @PersistenceContext
    private EntityManager em;

    public void save(ProductCj p) throws Exception {
        String q = "insert into product_cj (create_date,pid,product_name,product_Sku,product_Image,product_Weight,product_Type,category_Name,sell_Price,create_Time,remark,category_Id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)\n";
        Query query = em.createNativeQuery(q, ProductCj.class);
        query.setParameter(1,new Date());
        query.setParameter(2,p.getPid());
        query.setParameter(3,p.getProductName());
        query.setParameter(4,p.getProductSku());
        query.setParameter(5,p.getProductImage());
        query.setParameter(6,p.getProductWeight());
        query.setParameter(7,p.getProductType());
        query.setParameter(8,p.getCategoryName());
        query.setParameter(9,p.getSellPrice());
        query.setParameter(10,p.getCreateTime());
        query.setParameter(11,p.getRemark());
        query.setParameter(12,p.getCategoryId());
        int aaa = query.executeUpdate();
    }
}
