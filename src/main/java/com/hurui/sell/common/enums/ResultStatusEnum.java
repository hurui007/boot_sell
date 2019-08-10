package com.hurui.sell.common.enums;

/**
 * @title 返回结果状态
 */
public enum ResultStatusEnum {
    SUCCESS(200,"成功"),
    FAILED(500, "失败");


    private Integer code;

    private String name;

    ResultStatusEnum(Integer code, String name) {
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
