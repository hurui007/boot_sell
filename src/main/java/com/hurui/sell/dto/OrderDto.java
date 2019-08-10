package com.hurui.sell.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hurui.sell.common.util.Date2LongSerializer;
import com.hurui.sell.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) //为空的字段就不返回了
public class OrderDto {
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 买家名字
     */
    private String buyerName;
    /**
     * 买家手机号
     */
    private String buyerPhone;
    /**
     * 买家地址
     */
    private String buyerAddress;
    /**
     * 买家微信Openid
     */
    private String buyerOpenid;

    /**
     * 订单金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态 0:新下单
     */
    private Integer orderStatus;

    /**
     * 支付状态，0:等待支付
     */
    private Integer payStatus;
    /**
     * 创建时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    /**
     * 更新时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    /**
     * 订单详情列表
     */
    private List<OrderDetail> orderDetailList;


}
