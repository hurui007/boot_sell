package com.hurui.sell.respository;

import com.hurui.sell.dataobject.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 订单详情表的测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void saveTest(){
        OrderDetail detail = new OrderDetail();
        detail.setDetailId("12345");
        detail.setOrderId("1111");
        detail.setProductId("123");
        detail.setProductIcon("112");
        detail.setProductName("粥");
        detail.setProductPrice(new BigDecimal("20"));
        detail.setProductQuantity(2);
        orderDetailRepository.save(detail);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> detailList = orderDetailRepository.findByOrderId("1111");
        System.out.println(detailList);
    }
}
