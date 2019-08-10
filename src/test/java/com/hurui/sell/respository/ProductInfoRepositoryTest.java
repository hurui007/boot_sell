package com.hurui.sell.respository;

import com.hurui.sell.dataobject.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void findByProductStatus() {
        List<ProductInfo> recordList = repository.findByProductStatus(0);
    }

    @Test
    public void save(){
        ProductInfo record = new ProductInfo();
        record.setProductId("123456");
        record.setProductName("皮蛋粥");
        record.setProductPrice(new BigDecimal(3.2));
        record.setProductStock(100);
        record.setProductDescription("很好喝的粥");
        record.setProductIcon("http://xx.jpg");
        record.setProductStatus(0);
        record.setCategoryType(2);
        record = repository.save(record);
        System.out.println(record);
    }
}
