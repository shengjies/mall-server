package com.code.mallservice.mall.service.impl;

import com.code.mallservice.mall.entity.UrlEntity;
import com.code.mallservice.mall.mapper.UrlMapper;
import com.code.mallservice.mall.service.IUrlService;
import com.code.mallservice.mall.utils.Page;
import com.code.mallservice.mall.uuid.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 链接管理
 */
@Service
public class UrlServiceImpl implements IUrlService {
    @Autowired
    private UrlMapper urlMapper;

    @Override
    public int add(UrlEntity entity) {
        entity.setId(UuidUtils.compressedUuid());
        return urlMapper.add(entity);
    }

    @Override
    public int edit(UrlEntity entity) {
        return urlMapper.edit(entity);
    }

    @Override
    public Page<UrlEntity> findPage(String code, int product_id, int user_id, int page, int size) {
        long count = urlMapper.findCount(code,product_id,user_id);
        List<UrlEntity> list = urlMapper.findPage(code,product_id,user_id,page *size,size);
        return new Page<>(list,count);
    }
}
