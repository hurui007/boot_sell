package com.hurui.sell.service.impl;

import com.hurui.sell.common.enums.OrderStatusEnum;
import com.hurui.sell.common.enums.PayStatusEnum;
import com.hurui.sell.common.enums.ErrorEnum;
import com.hurui.sell.common.util.KeyUtil;
import com.hurui.sell.converter.OrderMster2OrderDtoConverter;
import com.hurui.sell.dataobject.OrderDetail;
import com.hurui.sell.dataobject.OrderMaster;
import com.hurui.sell.dataobject.ProductInfo;
import com.hurui.sell.dto.CarDto;
import com.hurui.sell.dto.OrderDto;
import com.hurui.sell.exception.SellException;
import com.hurui.sell.respository.OrderDetailRepository;
import com.hurui.sell.respository.OrderMasterRepository;
import com.hurui.sell.service.OrderService;
import com.hurui.sell.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @title 订单接口实现类
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductService productService;

    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {
        String orderId = KeyUtil.genUniqueKey();
        //设置一个订单总价
        BigDecimal orderAmount = BigDecimal.ZERO;

//        List<CarDto> carDtoList = new ArrayList<>();
        if(null == orderDto || null == orderDto.getOrderDetailList() || orderDto.getOrderDetailList().size() <= 0){
            throw new SellException(ErrorEnum.CAR_EMPTY);
        }
        //1、 查询商品数量，价格
        for(OrderDetail orderDetail: orderDto.getOrderDetailList()){
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if(null == productInfo){
                //商品不存在
                throw new SellException(ErrorEnum.PRODUCT_NOT_EXIST);
            }
            //2、计算总价，单价乘以总价
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);

//            CarDto carDto = new CarDto(orderDetail.getProductId(), orderDetail.getProductQuantity());
//            carDtoList.add(carDto);
        }

        //3、写入订单数据（orderMaster 和orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        // 4、 减库存


        List<CarDto> carDtoList = new ArrayList<>();
        carDtoList = orderDto.getOrderDetailList().stream().map(e ->
                new CarDto(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());

        productService.decreaseStock(carDtoList);
        orderDto.setOrderId(orderId);
        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {
        OrderDto result = new OrderDto();
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if(null == orderMaster ){
            throw new SellException(ErrorEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ErrorEnum.ORDERDETAIL_NOT_EXIST);
        }
        BeanUtils.copyProperties(orderMaster, result);
        result.setOrderDetailList(orderDetailList);
        return result;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenId, Pageable pageable) {
        Page<OrderMaster> orderMasterList = orderMasterRepository.findByBuyerOpenid(buyerOpenId, pageable);
        List<OrderDto> orderMasterList2 = OrderMster2OrderDtoConverter.convert(orderMasterList.getContent());
        Page<OrderDto> orderDtoPage = new PageImpl<>(orderMasterList2, pageable, orderMasterList.getTotalElements());
        return orderDtoPage;
    }

    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {
        //判断订单状态
        OrderMaster orderMaster = orderMasterRepository.findOne(orderDto.getOrderId());
        if(null == orderMaster){
            throw new SellException(ErrorEnum.ORDER_NOT_EXIST);
        }
        if(orderMaster.getOrderStatus() == OrderStatusEnum.FINISHED.getCode()){
            throw new SellException(ErrorEnum.ORDER_STATUS_ERROR, "订单已完成，取消失败");
        }
        else if(orderMaster.getOrderStatus() == OrderStatusEnum.CANCEL.getCode()){
            throw new SellException(ErrorEnum.ORDER_STATUS_ERROR, "当前订单已经是取消状态，不能重复取消");
        }
        //修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        //修改订单状态
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            //如果返回结果为空的话更新失败
            throw new SellException(ErrorEnum.ORDER_UPDATE_FAIL);
        }
        //退换库存
        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            log.error("【取消订单】,订单中午商品详情,orderDto={}",orderDto);
            throw new SellException(ErrorEnum.ORDER_DETAIL_EMPTY);
        }
        List<CarDto> carDtoList = orderDto.getOrderDetailList().stream().map(e -> new CarDto(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(carDtoList);


        // 如果支付了，需要退款
        if(orderMaster.getPayStatus() == PayStatusEnum.SUCCESS.getCode()){
            //TODO
        }
        return null;
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderDto.getOrderId());
        if(null == orderMaster ){
             throw new SellException(ErrorEnum.ORDER_NOT_EXIST);
        }
        if(orderMaster.getOrderStatus() != OrderStatusEnum.NEW.getCode()){
            throw new SellException(ErrorEnum.ORDER_STATUS_ERROR, "不是待完成的订单");
        }
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster updateOrderMaster = orderMasterRepository.save(orderMaster);
        if(null == updateOrderMaster){
            throw new SellException(ErrorEnum.ORDER_UPDATE_FAIL);
        }

        return orderDto;
    }

    @Override
    public OrderDto paid(OrderDto orderDto) {
        //判断订单状态,新订单才能支付
        OrderMaster orderMaster = orderMasterRepository.findOne(orderDto.getOrderId());
        if(null == orderMaster ){
            throw new SellException(ErrorEnum.ORDER_NOT_EXIST);
        }
        if(orderMaster.getOrderStatus() != OrderStatusEnum.NEW.getCode() || orderMaster.getPayStatus() != PayStatusEnum.WAIT.getCode()){
            throw new SellException(ErrorEnum.ORDER_STATUS_ERROR, "不是待支付订单");
        }

        //修改支付状态
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster updateOrderMaster = orderMasterRepository.save(orderMaster);
        if(null == updateOrderMaster){
            throw new SellException(ErrorEnum.ORDER_UPDATE_FAIL);
        }

        return orderDto;
    }
}
