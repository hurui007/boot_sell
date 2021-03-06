package com.hurui.sell.respository;

import com.hurui.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    //根据openid分页查询订单数据
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
