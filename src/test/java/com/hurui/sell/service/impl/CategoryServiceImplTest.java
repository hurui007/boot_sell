package com.hurui.sell.service.impl;

import com.hurui.sell.dataobject.ProductCategory;
import com.hurui.sell.service.CategoryService;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOne() {
        ProductCategory record = categoryService.findOne(1);

        Assert.assertEquals((Integer) 1,record.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> recordList = categoryService.findAll();
        System.out.println(recordList);
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> typeList = Lists.newArrayList();
        typeList.add(1);
        typeList.add(2);
        List<ProductCategory> recordList = categoryService.findByCategoryTypeIn(typeList);
        System.out.println(recordList);
    }

    @Test
    public void save() {
        ProductCategory record = new ProductCategory();
        record.setCategoryType(4);
        record.setCategoryName("男生专享");
        categoryService.save(record);
        System.out.println(record);

    }
}
