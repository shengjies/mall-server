package com.code.mallservice.mall.service;

import com.code.mallservice.mall.entity.UrlEntity;
import com.code.mallservice.mall.utils.Page;

/**
 * 链接管理
 */
public interface IUrlService {
    /**
     * 添加链接
     * @param entity
     * @return
     */
    int add(UrlEntity entity);

    /**
     * 修改链接
     * @param entity
     * @return
     */
    int edit(UrlEntity entity);

    /**
     * 分页查询
     * @param code
     * @param product_id
     * @param user_id
     * @param page
     * @param size
     * @return
     */
    Page<UrlEntity> findPage(String code,int product_id,String user_id,int page,int size);

    /**
     * 按编号查询链接
     * @param code
     * @return
     */
    UrlEntity findById(String code);

    /**
     * 删除
     * @param id
     * @return
     */
    int del(String id);
}
