package com.hurui.sell.biz;

import com.hurui.sell.common.enums.ResultStatusEnum;
import com.hurui.sell.dataobject.ProductCategory;
import com.hurui.sell.dataobject.ProductInfo;
import com.hurui.sell.service.CategoryService;
import com.hurui.sell.service.ProductService;
import com.hurui.sell.vo.ProductInfoVo;
import com.hurui.sell.vo.ProductVo;
import com.hurui.sell.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @title 商品业务处理
 */
@Service
public class ProductBiz {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    /**
     * @title 批量查询所有商家商品
     * @return
     */
    public ResultVo<ProductVo> queryProductInfoInUp(){
        ResultVo result = new ResultVo();
        List<ProductVo> resultList = new ArrayList<>();
        ProductVo resultVo = null;


        //1、查询所有商家商品
        List<ProductInfo> productInfoList = productService.findAll();

        //2、查询类目 传统写法
        /*List<Integer> categoryTypeList = new ArrayList<>();
        categoryTypeList.add(1);*/
        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        if (productCategoryList != null && productCategoryList.size() > 0) {
            for (ProductCategory category : productCategoryList) {
                resultVo = new ProductVo();
                resultVo.setCategoryName(category.getCategoryName());
                resultVo.setCategoryType(category.getCategoryType());
                List<ProductInfoVo> infoVoList = new ArrayList<>();
                for (ProductInfo info : productInfoList) {
                    if (info.getCategoryType() != null && info.getCategoryType().equals(category.getCategoryType())) {
                        ProductInfoVo infoVo = new ProductInfoVo();
                        //结果拷贝
                        BeanUtils.copyProperties(info, infoVo);
                        infoVoList.add(infoVo);
                    }
                }
                resultVo.setProductInfoVoList(infoVoList);
                resultList.add(resultVo);
            }
        }

        return ResultVo.OK(resultList);

    }


}
