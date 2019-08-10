package com.hurui.sell.respository;

import com.hurui.sell.dataobject.ProductCategory;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest(){
        ProductCategory record = repository.findOne(1);
        System.out.println(record);
    }

    @Test
    public void saveTest(){
        ProductCategory record = new ProductCategory();
        record.setCategoryName("女生最爱");
        record.setCategoryType(3);
        ProductCategory result = repository.save(record);
        System.out.println(result);
    }

    @Test
    public void updateTest(){
        ProductCategory record = new ProductCategory();
        record.setCategoryId(2);
        record.setCategoryName("男生最爱");
        record.setCategoryType(3);
        /**
         * 修改方法和保存方法用的用一个方法，只是需要需要一个主键
         */
        ProductCategory result = repository.saveAndFlush(record);
        System.out.println("update:" + result);
    }

    @Test
    public void findAndUpdate(){
        //实体添加了DynamicUpdate注解，可以动态更新updateTime
        ProductCategory result = repository.findOne(2);
        result.setCategoryType(10);
        repository.save(result);
    }

    @Test
    public void findByCategoryTypeIn(){
        List<Integer> list = Lists.newArrayList();
        list.add(2);
        list.add(10);
        List<ProductCategory> resultList = repository.findByCategoryTypeIn(list);
        if(null != resultList && resultList.size() > 0){
            for(ProductCategory record:resultList){
                System.out.println(record);
            }
        }
    }

    @Test
    public void findByCategoryNameIn(){
        List<String> list = Lists.newArrayList();
        list.add("男生最爱");
        list.add("女生最爱");
        List<ProductCategory> resultList = repository.findByCategoryNameIn(list);
        if(null != resultList && resultList.size() > 0){
            for(ProductCategory record:resultList){
                System.out.println(record);
            }
        }
    }

}
