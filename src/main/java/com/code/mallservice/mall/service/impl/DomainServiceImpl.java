package com.code.mallservice.mall.service.impl;

import com.code.mallservice.mall.entity.DomailEntity;
import com.code.mallservice.mall.mapper.DomainMapper;
import com.code.mallservice.mall.service.IDomainService;
import com.code.mallservice.mall.utils.Page;
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
    /**
     * 查询对应用户下的域名
     * @param user_id
     * @return
     */
    @Override
    public List<DomailEntity> findByUser(int user_id) {
        return domainMapper.findByUser(user_id);
    }

    @Override
    public int add(DomailEntity entity) {
        return domainMapper.add(entity);
    }

    @Override
    public int edit(DomailEntity entity) {
        return domainMapper.edit(entity);
    }

    @Override
    public Page<DomailEntity> findPage(String domain_name, int page, int size) {
        long count = domainMapper.countFind(domain_name);
        List<DomailEntity> list = domainMapper.findPage(domain_name,page*size,size);
        return new Page<>(list,count);
    }
}
