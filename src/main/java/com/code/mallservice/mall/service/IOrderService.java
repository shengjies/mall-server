package com.code.mallservice.mall.service;

import com.code.mallservice.mall.entity.OrderEntity;
import com.code.mallservice.mall.entity.SizesEntity;
import com.code.mallservice.mall.entity.TypeEntity;
import com.code.mallservice.mall.utils.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

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

    /**
     * 按照编号查询
     * @param id
     * @return
     */
    OrderEntity findById(int id);

    /**
     * 分页查询
     * @param id 订单编号
     * @param product_id 产品编号
     * @param username 客户名称
     * @param order_status 订单状态
     * @param bTime 开始时间
     * @param eTime 结束时间
     * @param cg_status 采购状态
     * @param wl_status 物流状态
     * @param mobile 手机号码
     * @param country 国家
     * @param page 页数大小
     * @param size 页面大小
     * @return
     */
    Page<OrderEntity> findPage(int id,int product_id,String username,
                               int order_status,String bTime,String eTime,
                               int cg_status,int wl_status,String mobile,String country,
                               int page,int size,String user_id,int ck_status);

    /**
     * 修改订单信息
     * @param id
     * @param is_edit
     * @param postzip
     * @param email
     * @param detal
     * @param remark
     * @throws Exception
     */
    void  editOrder(int id, boolean is_edit, String postzip, String email,
                    String detal, String remark,int order_status) throws Exception;

    /**
     * 批量修改状态
     * @param edit_status 需要修改的状态 1、采购状态 2、收款状态
     * @param estatus 各个状态的值
     * @param edit_order 订单编号
     * @return
     */
    Map<String,String> actionsOrderStatus(int edit_status,int estatus,String edit_order);

    /**
     * 条件导出
     * @param response
     * @param id 订单编号
     * @param product_id 产品编号
     * @param username 客户名称
     * @param order_status 订单状态
     * @param bTime 开始时间
     * @param eTime 结束时间
     * @param cg_status 采购状态
     * @param wl_status 物流状态
     * @param ck_status 收款状态
     * @param user_id 业务员
     * @param mobile 手机
     * @param sign 编辑是否需要导出详情
     */
    void exportBatchExcel(HttpServletResponse response,int id, int product_id, String username,
                          int order_status, String bTime, String eTime, int cg_status,
                          int wl_status, int ck_status, String user_id, String mobile, Integer sign);

    /**
     * 勾选导出
     * @param response
     * @param sign
     * @param checkedTest
     * @throws Exception
     */
    void checkReport(HttpServletResponse response, int sign, String checkedTest)throws Exception;

    /**
     * 删除订单
     * @param id
     * @return
     */
    int del(int id);

    /**
     * 查询电话或者ip是否重复
     * @param mobile
     * @param ip
     * @return
     */
    List<OrderEntity> findMobileOrIpList(String mobile, String ip);
}
