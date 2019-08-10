package com.hurui.sell.controller;

import com.hurui.sell.biz.ProductBiz;
import com.hurui.sell.dataobject.ProductCategory;
import com.hurui.sell.dataobject.ProductInfo;
import com.hurui.sell.service.CategoryService;
import com.hurui.sell.service.ProductService;
import com.hurui.sell.vo.ProductVo;
import com.hurui.sell.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @title 买家商品
 * @date 2019-06-26
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductBiz productBiz;

    @GetMapping("/list")
    public ResultVo<ProductVo> list(){
    return productBiz.queryProductInfoInUp();
    }
}
