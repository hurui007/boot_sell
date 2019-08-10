package com.hurui.sell.service;

import com.hurui.sell.dataobject.ProductInfo;
import com.hurui.sell.dto.CarDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    /**
     * @title 根据商品id查询
     * @param productId
     * @author hurui
     * @return
     */
    ProductInfo findOne(String productId);

    /**
     * @title 查询所有的商品列表
     * @return
     */
    List<ProductInfo> findAll();

    /**
     * @title 分页查询
     * @param pageable
     * @author hurui
     * @return
     */
    Page<ProductInfo> findAlll(Pageable pageable);

    /**
     * @title 新增商品
     * @param productInfo
     * @author hurui
     * @return
     */
    ProductInfo save(ProductInfo productInfo);

    /**
     * @title 商品加库存
     * @param carDtoList
     */
    void increaseStock(List<CarDto> carDtoList);

    /**
     * @title 商品减库存
     * @param carDtoList
     */
    void decreaseStock(List<CarDto> carDtoList);
}
