package com.hurui.sell.service.impl;

import com.hurui.sell.common.enums.ProductStatusEnum;
import com.hurui.sell.common.enums.ErrorEnum;
import com.hurui.sell.dataobject.ProductInfo;
import com.hurui.sell.dto.CarDto;
import com.hurui.sell.exception.SellException;
import com.hurui.sell.respository.ProductInfoRepository;
import com.hurui.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAlll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CarDto> carDtoList) {
        for(CarDto carDto:carDtoList){
            ProductInfo productInfo = repository.findOne(carDto.getProductId());
            if(null == productInfo){
                throw new SellException(ErrorEnum.PRODUCT_NOT_EXIST);
            }
            productInfo.setProductStock(productInfo.getProductStock() + carDto.getProductQuantity());
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CarDto> carDtoList) {
        for(CarDto carDto:carDtoList){
            ProductInfo productInfo = repository.findOne(carDto.getProductId());
            if(null == productInfo){
                throw new SellException(ErrorEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() - carDto.getProductQuantity();
            if(result < 0){
                throw new SellException(ErrorEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }
}
