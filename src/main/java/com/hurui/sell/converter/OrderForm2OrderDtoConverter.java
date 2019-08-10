package com.hurui.sell.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hurui.sell.common.enums.ErrorEnum;
import com.hurui.sell.dataobject.OrderDetail;
import com.hurui.sell.dto.OrderDto;
import com.hurui.sell.exception.SellException;
import com.hurui.sell.from.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class OrderForm2OrderDtoConverter {

    public static OrderDto convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerAddress(orderForm.getAdress());
        orderDto.setBuyerOpenid(orderForm.getOpenId());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        //json转对象
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (JsonSyntaxException e) {
            log.error("【对象转换】string={}",orderForm.getItems());
            throw new SellException(ErrorEnum.PARAM_ERROR);
        }
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }
}
