package com.code.mallservice.mall.mapper;

import com.code.mallservice.mall.entity.OrderEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    /**
     * 按编号查询所以数据
     * @param id
     * @return
     */
    OrderEntity findById(@Param("id")int id);

    /**
     * 按编号查询订单基本数据
     * @param id
     * @return
     */
    @Select("SELECT * FROM mall.tab_order where id = #{id} limit 1;")
    OrderEntity findOrderById(@Param("id")int id);
    /**
     * 分页统计
     * @param id 订单编号
     * @param product_id 产品编号
     * @param username 用户名称
     * @param order_status 订单状态
     * @param bTime 开始时间
     * @param eTime 结束时间
     * @param cg_status 采购涨停
     * @param wl_status 物流状态
     * @param mobile 手机号码
     * @param country 国家
     * @return
     */
    Long countFind(@Param("id")int id,@Param("product_id")int product_id,
                   @Param("username")String username,@Param("order_status")int order_status,
                   @Param("bTime")String bTime,@Param("eTime")String eTime,
                   @Param("cg_status")int cg_status,@Param("wl_status")int wl_status,
                   @Param("mobile")String mobile,@Param("country")String country,
                   @Param("user_id")String user_id,@Param("ck_status")int ck_status);

    /**
     *分页查询
     * @param id
     * @param product_id
     * @param username
     * @param order_status
     * @param bTime
     * @param eTime
     * @param cg_status
     * @param wl_status
     * @param mobile
     * @param country
     * @param page
     * @param size
     * @return
     */
    List<OrderEntity> findPage(@Param("id")int id,@Param("product_id")int product_id,
                               @Param("username")String username,@Param("order_status")int order_status,
                               @Param("bTime")String bTime,@Param("eTime")String eTime,
                               @Param("cg_status")int cg_status,@Param("wl_status")int wl_status,
                               @Param("mobile")String mobile,@Param("country")String country,
                               @Param("page")int page,@Param("size")int size,
                               @Param("user_id")String user_id,@Param("ck_status")int ck_status);

    /**
     * 修改订单基本信息
     * @param id 编号
     * @param postzip 确认邮编
     * @param email 邮箱
     * @param remark 备注信息
     * @return
     */
    @Update("update tab_order set cpostzip=#{postzip},emaill=#{email},remark=#{remark},order_status=#{order_status},up_date=now() where id = #{id}")
    int editOrderInfo(@Param("id")int id,@Param("postzip")String postzip,@Param("order_status")int order_status,
                      @Param("email")String email,@Param("remark")String remark);

    /**
     * 修改采购状态
     * @param order_id
     * @param cg_status
     * @return
     */
    @Update("update tab_order set cg_status=#{cg_status} where id = #{order_id}")
    int editCgStatus(@Param("order_id")int order_id,@Param("cg_status")int cg_status);

    /**
     * 收款状态
     * @param order_id
     * @param ck_status
     * @return
     */
    @Update("update tab_order set ck_status=#{ck_status} where id = #{order_id}")
    int editSkStatus(@Param("order_id")int order_id,@Param("ck_status")int ck_status);


    List<OrderEntity> findExcel(@Param("id")int id,@Param("product_id")int product_id,
                               @Param("username")String username,@Param("order_status")int order_status,
                               @Param("bTime")String bTime,@Param("eTime")String eTime,
                               @Param("cg_status")int cg_status,@Param("wl_status")int wl_status,
                               @Param("mobile")String mobile,@Param("country")String country,
                               @Param("user_id")String user_id,@Param("ck_status")int ck_status);

    /**
     * 删除订单
     * @param id
     * @return
     */
    @Delete("delete from tab_order where id = #{id}")
    int del(@Param("id")int id);

    /**
     * 电话或者IP重复
     * @param mobile
     * @return
     */
    List<OrderEntity> findMobileOrIpList(@Param("mobile")String mobile,
                                         @Param("ip")String ip);



}
