package com.code.mallservice.mall.service;

import com.code.mallservice.mall.entity.DomailEntity;
import com.code.mallservice.mall.utils.Page;

import java.util.List;

/**
 * 域名管理
 */
public interface IDomainService {
    /**
     * 查询所有
     * @return
     */
    List<DomailEntity> findAll();

    /**
     * 查询对应用户下的域名
     * @param user_id
     * @return
     */
    List<DomailEntity> findByUser(int user_id);

    /**
     * 添加
     * @param entity
     * @return
     */
    int add(DomailEntity entity);

    /**
     * 修改
     * @param entity
     * @return
     */
    int edit(DomailEntity entity);

    /**
     * 分页查询
     * @param domain_name
     * @param page
     * @param size
     * @return
     */
    Page<DomailEntity> findPage(String domain_name,int page,int size);
}
