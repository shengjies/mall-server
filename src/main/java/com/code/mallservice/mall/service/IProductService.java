package com.code.mallservice.mall.service;

import com.code.mallservice.mall.entity.ProductEntity;
import com.code.mallservice.mall.utils.Page;

import java.util.List;
import java.util.Map;

/**
 * 产品信息
 */
public interface IProductService {
    /**
     * 添加产品信息
     * @param entity
     * @return
     */
    int add(ProductEntity entity);

    /**
     * 修改
     * @param entity
     * @return
     */
    int edit(ProductEntity entity);

    /**
     * 分页查询
     * @param id
     * @param product_name
     * @param user_id
     * @param page
     * @param size
     * @return
     */
    Page<ProductEntity> findPage(int id,String product_name,String user_id,int page,int size);

    /**
     * 按编号查询
     * @param id
     * @return
     */
    ProductEntity findAllById(int id);

    /**
     * 用于下拉列表
     * @param user_id
     * @return
     */
    List<ProductEntity> listAll(int user_id);

    /**
     * 复制产品
     * @param id
     * @param country
     * @throws Exception
     */
    void copyInfo(int id,String country) throws Exception;

    /**
     * 根据产品编号查询对应的属性
     * @param product_id
     * @return
     */
    Map<String,Object> findAttrByProductId(int product_id);

    /**
     * 删除产品
     * @param id
     * @return
     */
    int del(int id);
}
