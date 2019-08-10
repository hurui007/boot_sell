package com.hurui.sell.dataobject;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @title 类目
 * @author hurui
 * @date 2019-06-24
 * product_category,类名没有对应表名的话，需要加 @Table(name = "product_category")
 */
//@Table(name = "product_category")
@Entity
@DynamicUpdate
@Data
public class ProductCategory {
    /**
     * 类目id
     */
    @Id
    @GeneratedValue
    private Integer categoryId;
    /**
     * 类目名称
     */
    private String categoryName;
    /**
     * 类目类型
     */
    private Integer categoryType;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

}
