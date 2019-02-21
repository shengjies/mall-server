package com.code.mallservice.mall.service;

import com.code.mallservice.mall.entity.DomailEntity;

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
}
