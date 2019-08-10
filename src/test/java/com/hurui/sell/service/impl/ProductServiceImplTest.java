package com.hurui.sell.service.impl;

import com.hurui.sell.dataobject.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() {
        ProductInfo record = productService.findOne("123456");
        System.out.println(record);
    }

    @Test
    public void findAll() {
        List<ProductInfo> recordList = productService.findAll();
        for(ProductInfo info:recordList){
            System.out.println(info);
        }
    }

    @Test
    public void findAlll() {
        PageRequest request = new PageRequest(0,10);
        Page<ProductInfo> result = productService.findAlll(request);
        Iterator<ProductInfo> iterator = result.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("-----");
        System.out.println(result);
    }

    @Test
    public void save() {
        ProductInfo record = new ProductInfo();
        record.setProductId("123457");
        record.setProductName("皮蛋粥");
        record.setProductPrice(new BigDecimal(3.2));
        record.setProductStock(100);
        record.setProductDescription("很好喝的粥");
        record.setProductIcon("http://xx.jpg");
        record.setProductStatus(0);
        record.setCategoryType(2);
        record = productService.save(record);
        System.out.println(record);
    }
}
