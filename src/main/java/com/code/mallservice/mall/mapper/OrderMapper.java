package com.code.mallservice.mall.mapper;

import com.code.mallservice.mall.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 订单映射
 */
@Mapper
public interface OrderMapper {
    /**
     * 添加订单信息
     * @param entity
     * @return
     */
    int add(OrderEntity entity);

    /**
     * 按照电话查询
     * @param mobile
     * @return
     */
    @Select("SELECT * FROM mall.tab_order where mobile =#{mobile} limit 1")
    OrderEntity findByMobile(@Param("mobile")String mobile);

    /**
     * 按照ip查询
     * @param ip
     * @return
     */
    @Select("SELECT * FROM mall.tab_order where ip =#{ip} limit 1")
    OrderEntity findByIp(@Param("ip")String ip);
}
