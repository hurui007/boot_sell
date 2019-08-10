package com.hurui.sell.service;

import com.hurui.sell.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {

    /**
     * @title 根据类目id，查找单条记录
     * @param categoryId
     * @author hurui
     * @return
     */
    ProductCategory findOne(Integer categoryId);

    /**
     * @title 查询所有记录
     * @author hurui
     * @return
     */
    List<ProductCategory> findAll();

    /**
     * @title 根据类目类型批量查询
     * @param categoryTypeList
     * @author hurui
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * @title 添加一条记录
     * @param productCategory
     * @author hurui
     * @return
     */
    ProductCategory save(ProductCategory productCategory);
}
