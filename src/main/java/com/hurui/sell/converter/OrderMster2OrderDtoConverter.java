package com.hurui.sell.converter;

import com.hurui.sell.dataobject.OrderMaster;
import com.hurui.sell.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @title 类型转换器
 */
public class OrderMster2OrderDtoConverter {

    public static OrderDto convert(OrderMaster orderMaster){
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        return orderDto;
    }

    public static List<OrderDto> convert(List<OrderMaster> orderMasterList){
       return orderMasterList.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
