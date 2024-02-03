package com.tehcf.etl.service;

import com.tehcf.etl.dao.ProdoctCjDao;
import com.tehcf.etl.entity.ProductCj;
import com.tehcf.etl.model.list.ProductLst;
import com.tehcf.etl.util.CategoryList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EtlService {

    private final ProdoctCjDao prodoctCjDao;

    private String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjg2MyIsInR5cGUiOiJBQ0NFU1NfVE9LRU4iLCJzdWIiOiJicUxvYnFRMGxtTm55UXB4UFdMWnlsbnU3cHJPa0wvS2JGN2V0bk1RbGFXUzNZcDVUZEpkcTQwRjl1VDlJNjJHVG55VUhjN1VCWFk2Si9JZ0s4S2xPOVJnQndTMlV4cTFMQ0ZTa1FjcXlpeGMybHRVRGVQbW9PUytiWksvRVQ1WmV1WCs5WnNCL0JKbmkzY1g1ZjJTeUFCQ2RXRnY3bVJ2UTNkd0h0M1JwdjhwOW9LRWt4d1VDNFZ6ZUIzZGRYV0VRSWVWRU4wTWNXSmJUbTJWQXQ0cW5kbThPRDZ4MnNtVTBDcmRXbVIweGFpMWhaRkl1Wm95ZGpOQmorZ3RQY040dHVoTSszZTQzNnRuSlpMcGpkbVN5WUdpRzEvN01vWmg5bzI5eFBnWnFIbz0ifQ.IV_Vs4HLYGlKMWEPzqn4QlnL_VZx4VwH2UiHYRdfoMQ";

    private final RestTemplate restTemplate;

    @Scheduled(cron = "* * * * * *")
    @Transactional
    public void readFrom() throws InterruptedException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("CJ-Access-Token", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
            String item = "4D7853CE-9E1C-4103-A06F-46401827A535";
            ProductLst productRes = restTemplate
                    .exchange("https://developers.cjdropshipping.com/api2.0/v1/product/list?categoryId=" +item, HttpMethod.GET, entity, ProductLst.class).getBody();
            Thread.sleep(2000);
            long total = productRes.getData().getTotal();
            int m = Math.round(total/200)+1;
            for (int j=1;j<=m;j++){
                try {
                    ProductLst productResf = restTemplate
                            .exchange("https://developers.cjdropshipping.com/api2.0/v1/product/list?categoryId=" + item + "&pageSize=200&pageNum=" + j, HttpMethod.GET, entity, ProductLst.class).getBody();
                    Thread.sleep(2000);
                    productResf.getData().getList().forEach(p -> {
                        ProductCj productCj = new ProductCj();
                        productCj.setProductImage(p.getProductImage());
                        productCj.setProductName(p.getProductNameEn());
                        productCj.setPid(p.getPid());
                        productCj.setProductSku(p.getProductSku());
                        productCj.setProductType(p.getProductType());
                        productCj.setProductWeight(p.getProductWeight());
                        productCj.setRemark(p.getRemark());
                        productCj.setCategoryId(p.getCategoryId());
                        productCj.setCreateTime(p.getCreateTime());
                        productCj.setSellPrice(p.getSellPrice());
                        productCj.setCategoryName(item);
                        save(productCj);

                    });
                }catch (Exception eee){
                    System.out.println("error");
                }
            }
    }
    @Transactional
    public void save(ProductCj productCj){
        try {
            prodoctCjDao.save(productCj);
            System.out.println("inserted");
        } catch (Exception e) {
            System.out.println("error inserting");
            throw new RuntimeException(e);
        }
    }
}
