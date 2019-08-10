package com.hurui.sell.controller;

import com.hurui.sell.common.enums.ErrorEnum;
import com.hurui.sell.converter.OrderForm2OrderDtoConverter;
import com.hurui.sell.dto.OrderDto;
import com.hurui.sell.exception.SellException;
import com.hurui.sell.from.OrderForm;
import com.hurui.sell.service.OrderService;
import com.hurui.sell.vo.ResultVo;
import com.sun.tools.javac.main.Main;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.channels.SelectableChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    //创建订单
    @PostMapping("/create")
    public ResultVo<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数错误,orderFrom={}",orderForm);
            throw new SellException(ErrorEnum.PARAM_ERROR, bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDto orderDto = OrderForm2OrderDtoConverter.convert(orderForm);
        if(orderDto == null || CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ErrorEnum.PARAM_ERROR, "购物车不能为空");
        }

        OrderDto createResult = orderService.create(orderDto);
        Map<String, String > map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());
        return ResultVo.OK(map);
    }

    //订单列表
    @PostMapping("/list")
    public ResultVo<List<OrderDto>> list(@RequestParam("openId") String openId,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){

        if(StringUtils.isEmpty(openId)){
            throw new SellException(ErrorEnum.PARAM_ERROR);
        }

        PageRequest request = new PageRequest(page, pageSize);
        Page<OrderDto> orderDtoPage = orderService.findList(openId, request);
        return ResultVo.OK(orderDtoPage.getContent());
    }

    //订单详情，查看单个订单

    @GetMapping("/detail")
    public ResultVo<OrderDto> detail(@RequestParam("openId") String openId,
                                     @RequestParam("orderId") String orderId){
        //TODO 安全问题，不能越权访问
        OrderDto orderDto = orderService.findOne(orderId);
        return ResultVo.OK(orderDto);

    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVo<Boolean> cancel(@RequestParam("openId") String openId,
                           @RequestParam("orderId") String orderId){
        //TODO 有安全问题
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(orderId);
        orderDto.setBuyerOpenid(openId);
        orderService.cancel(orderDto);
        return ResultVo.OK(true);
    }

}


