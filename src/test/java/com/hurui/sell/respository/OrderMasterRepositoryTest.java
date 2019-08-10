package com.hurui.sell.respository;

import com.hurui.sell.common.enums.OrderStatusEnum;
import com.hurui.sell.common.enums.PayStatusEnum;
import com.hurui.sell.dataobject.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("师兄");
        orderMaster.setBuyerPhone("18665825977");
        orderMaster.setBuyerAddress("渝北");
        orderMaster.setBuyerOpenid("12029010");
        orderMaster.setOrderAmount(new BigDecimal("32"));
        orderMaster.setCreateTime(new Date());
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        repository.save(orderMaster);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest request = new PageRequest(0,1);
        Page<OrderMaster> result = repository.findByBuyerOpenid("12029010", request);
        System.out.println(result.getSize());
        List<OrderMaster> list = result.getContent();
        for(OrderMaster record:list){
            System.out.println(record);
        }

    }


}
