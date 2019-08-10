package com.hurui.sell.dto;

import lombok.Data;

import javax.persistence.GeneratedValue;

/**
 * @title 购物车
 * @author hurui
 */
@Data
public class CarDto {

    /**
     * 商品id
     */
    private String productId;
    /**
     * 商品数量
     */
    private Integer productQuantity;

    public CarDto(){}

    public CarDto(String productId, Integer productQuantity){
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
