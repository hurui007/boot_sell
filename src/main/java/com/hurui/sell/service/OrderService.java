package com.hurui.sell.service;

import com.hurui.sell.dataobject.OrderMaster;
import com.hurui.sell.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * @author hurui
 * @Date 2019-08-10
 * @title 订单
 */
public interface OrderService {
    /**
     * 创建订单
     */

    OrderDto create(OrderDto orderDto);

    /**
     * 查询单个订单
     */
    OrderDto findOne(String orderId);
    /**
     * 查询订单列表
     */
    Page<OrderDto> findList(String buyerOpenId, Pageable pageable);

    /**
     * 取消订单
     */
    OrderDto cancel(OrderDto orderDto);

    /**
     * 完结订单
     */
    OrderDto finish(OrderDto orderDto);

    /**
     * 支付订单
     */
    OrderDto paid(OrderDto orderDto);

}

