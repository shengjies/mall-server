package com.code.mallservice.mall.service.impl;

import com.code.mallservice.mall.entity.TemplateEntity;
import com.code.mallservice.mall.mapper.TemplateMapper;
import com.code.mallservice.mall.service.ITemplateService;
import com.code.mallservice.mall.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateServiceImpl implements ITemplateService {

    @Autowired
    private TemplateMapper templateMapper;

    @Override
    public int add(TemplateEntity entity) {
        return templateMapper.add(entity);
    }

    @Override
    public int edit(TemplateEntity entity) {
        return templateMapper.edit(entity);
    }

    @Override
    public Page<TemplateEntity> findPage(String t_name, int page, int size) {
        long count = templateMapper.countFind(t_name);
        List<TemplateEntity> list = templateMapper.findPage(t_name,page*size,size);
        return new Page<>(list,count);
    }
}
