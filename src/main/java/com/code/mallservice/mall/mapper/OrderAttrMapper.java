package com.code.mallservice.mall.mapper;

import com.code.mallservice.mall.entity.OrderAttrEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单属性
 */
@Mapper
public interface OrderAttrMapper {
    /**
     * 添加订单属性
     * @param entity
     * @return
     */
    int add(OrderAttrEntity entity);
}
