package com.hurui.sell.from;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @title 订单表单
 */
@Data
public class OrderForm {

    @NotEmpty(message = "姓名不能为空")
    private String name;

    @NotEmpty(message = "手机号必填")
    private String phone;

    @NotEmpty(message = "收货地址必填")
    private String adress;

    @NotEmpty(message = "openId必填")
    private String openId;

    @NotEmpty(message = "购物车信息必填")
    private String items;
}
