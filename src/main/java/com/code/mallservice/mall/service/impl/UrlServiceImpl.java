package com.code.mallservice.mall.service.impl;

import com.code.mallservice.mall.entity.ProductEntity;
import com.code.mallservice.mall.entity.UrlEntity;
import com.code.mallservice.mall.mapper.ProductMapper;
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

    @Autowired
    private ProductMapper productMapper;

    @Override
    public int add(UrlEntity entity) {
        String lang = "en";
        entity.setId(UuidUtils.compressedUuid());
        ProductEntity productEntity = productMapper.findById(entity.getProduct_id());
        if(productEntity != null){
            lang = productEntity.getLang();
        }
        String url = "http://"+entity.getDomaim()+":89/"+entity.getId()+"?lang="+lang;
        entity.setPreview_url(url);
        return urlMapper.add(entity);
    }

    @Override
    public int edit(UrlEntity entity) {
        return urlMapper.edit(entity);
    }

    @Override
    public Page<UrlEntity> findPage(String code, int product_id, String user_id, int page, int size) {
        long count = urlMapper.findCount(code,product_id,user_id);
        List<UrlEntity> list = urlMapper.findPage(code,product_id,user_id,page *size,size);
        return new Page<>(list,count);
    }

    @Override
    public UrlEntity findById(String code) {
        return urlMapper.findById(code);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public int del(String id) {
        return urlMapper.del(id);
    }
}
