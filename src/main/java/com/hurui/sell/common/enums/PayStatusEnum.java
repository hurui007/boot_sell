package com.hurui.sell.common.enums;

/**
 * 支付状态
 */
public enum PayStatusEnum {
    WAIT(0, "待支付"),
    SUCCESS(1, "支付成功");

    private Integer code;

    private String name;

    PayStatusEnum(Integer code, String name) {
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
