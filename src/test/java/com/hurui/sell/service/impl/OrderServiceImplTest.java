package com.hurui.sell.service.impl;

import com.hurui.sell.dataobject.OrderDetail;
import com.hurui.sell.dto.OrderDto;
import org.hibernate.criterion.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String buyerOpenId = "110110";

    @Test
    public void create() {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("大师兄");
        orderDto.setBuyerAddress("中国");
        orderDto.setBuyerOpenid(buyerOpenId);
        orderDto.setBuyerPhone("18665825970");
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail detail = new OrderDetail();
        detail.setProductId("123456");
        detail.setProductQuantity(1 );
        orderDetailList.add(detail);
        orderDto.setOrderDetailList(orderDetailList);
        orderService.create(orderDto);


    }

    @Test
    public void findOne() {
        OrderDto orderDto = orderService.findOne("1565441729010400503");
        System.out.println(orderDto);
    }

    @Test
    public void findList() {
        PageRequest request = new PageRequest(0,10);

        Page<OrderDto> result = orderService.findList(buyerOpenId, request);
        System.out.println(result);
    }

    @Test
    public void cancel() {
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}
