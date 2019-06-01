package com.code.mallservice.mall.mapper;

import com.code.mallservice.mall.entity.OrderAttrEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    /**
     * 查询订单的详情
     * @param order_id
     * @return
     */
    @Select("SELECT * FROM mall.tab_order_attr where order_id = #{order_id} and attr_status=1;")
    List<OrderAttrEntity> findAttrByOrderId(@Param("order_id")int order_id);

    /**
     * 修改订单属性的状态
     * @param order_id
     * @return
     */
    @Update("update tab_order_attr set attr_status = 0 where order_id =#{order_id}")
    int editOrderAttrStatus(@Param("order_id")int order_id);

    /**
     * 修改订单属性
     * @param entity
     * @return
     */
    @Update("update tab_order_attr set cattr_value =#{cattr_value},cattr_size=#{cattr_size}," +
            "cnum=#{cnum},up_date = now(),attr_status = 1 where id = #{id}")
    int editOrderAttr(OrderAttrEntity entity);

    /**
     * 查询对应订单属性
     * @param attr_value
     * @param attr_size
     * @param order_id
     * @return
     */
    @Select("SELECT * FROM mall.tab_order_attr where order_id = #{order_id} and attr_value=#{attr_value} and attr_size=#{attr_size} limit 1;")
    OrderAttrEntity findByAttrTypeAndSize(@Param("attr_value")String attr_value,
                                          @Param("attr_size")String attr_size,
                                          @Param("order_id")int order_id);

    @Select("SELECT * FROM mall.tab_order_attr where order_id = #{order_id} and cattr_value=#{cattr_value} and cattr_size=#{cattr_size} limit 1;")
    OrderAttrEntity findByCAttrTypeAndSize(@Param("cattr_value")String cattr_value,
                                          @Param("cattr_size")String cattr_size,
                                          @Param("order_id")int order_id);

    /**
     * 删除订单属性
     * @param order_id
     * @return
     */
    @Delete("delete from tab_order_attr where order_id = #{order_id}")
    int delAttr(@Param("order_id")int order_id);
}
