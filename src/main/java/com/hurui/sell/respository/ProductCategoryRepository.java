package com.hurui.sell.respository;

import com.hurui.sell.dataobject.ProductCategory;
import lombok.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @title
 * @author hurui
 * @date 2019-06-24
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    /**
     * @title 自定义根据类目类型批量查询
     * @param catetoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> catetoryTypeList);

    /**
     * @title 自定义根据类目名称批量查询
     * @param catetoryNameList
     * @return
     */
    List<ProductCategory> findByCategoryNameIn(List<String> catetoryNameList);
}
