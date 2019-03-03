package com.code.mallservice.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.code.mallservice.mall.entity.OrderAttrEntity;
import com.code.mallservice.mall.entity.OrderEntity;
import com.code.mallservice.mall.entity.UrlEntity;
import com.code.mallservice.mall.mapper.OrderAttrMapper;
import com.code.mallservice.mall.mapper.OrderMapper;
import com.code.mallservice.mall.mapper.UrlMapper;
import com.code.mallservice.mall.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 订单模块
 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderAttrMapper orderAttrMapper;
    @Autowired
    private UrlMapper urlMapper;

    @Override
    public int add(OrderEntity entity) {
        entity.setMobile(entity.getMobile().trim());
        //查询对应的电话是否存在
        if(orderMapper.findByMobile(entity.getMobile()) != null){
            entity.setDh_status(1);//重复
        }
        //查询对应的IP是否存在
        if(orderMapper.findByIp(entity.getIp()) != null){
            entity.setIp_status(1);//重复
        }
        //查询对应的链接
        UrlEntity urlEntity = urlMapper.findById(entity.getUrl_id());
        if(urlEntity != null){
            entity.setPriview_url(urlEntity.getPreview_url());
        }
        orderMapper.add(entity);//保存订单信息
        if(!StringUtils.isEmpty(entity.getDetal())){
            //保存订单属性
            List<OrderAttrEntity> attrEntities = JSON.parseArray(entity.getDetal(),OrderAttrEntity.class);
            if(attrEntities != null){
                for (OrderAttrEntity attrEntity : attrEntities) {
                    attrEntity.setOrder_id(entity.getId());
                    orderAttrMapper.add(attrEntity);
                }
            }
        }
        return 0;
    }
}
