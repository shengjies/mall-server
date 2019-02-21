package com.code.mallservice.mall.service.impl;

import com.code.mallservice.mall.entity.DomailEntity;
import com.code.mallservice.mall.mapper.DomainMapper;
import com.code.mallservice.mall.service.IDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 域名管理
 */
@Service
public class DomainServiceImpl implements IDomainService {
    @Autowired
    private DomainMapper domainMapper;

    /**
     * 查询所有域名
     * @return
     */
    @Override
    public List<DomailEntity> findAll() {
        return domainMapper.findAll();
    }
}