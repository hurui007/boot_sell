package com.hurui.sell.common.enums;

/**
 * @title 商品状态
 */
public enum  ProductStatusEnum {
    UP(0,"上架"),
    DOWN(1,"下架");

    private Integer code;

    private String name;

    ProductStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
