package com.code.mallservice.mall.service;

import com.code.mallservice.mall.entity.OrderEntity;

/**
 * 订单
 */
public interface IOrderService {
    /**
     * 添加订单
     * @param entity
     * @return
     */
    int add(OrderEntity entity);
}
