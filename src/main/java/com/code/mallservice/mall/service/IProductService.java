package com.code.mallservice.mall.service;

import com.code.mallservice.mall.entity.ProductEntity;
import com.code.mallservice.mall.utils.Page;

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
    Page<ProductEntity> findPage(int id,String product_name,int user_id,int page,int size);

    /**
     * 按编号查询
     * @param id
     * @return
     */
    ProductEntity findById(int id);
}
