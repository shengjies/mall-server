package com.code.mallservice.mall.service.impl;

import com.code.mallservice.mall.entity.GiftEntity;
import com.code.mallservice.mall.entity.GiftSizeEntity;
import com.code.mallservice.mall.entity.GiftTypeEntity;
import com.code.mallservice.mall.mapper.GiftMapper;
import com.code.mallservice.mall.mapper.GiftSizeMapper;
import com.code.mallservice.mall.mapper.GiftTypeMapper;
import com.code.mallservice.mall.service.IGiftService;
import com.code.mallservice.mall.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 赠品信息
 */
@Service
public class GiftServiceImpl implements IGiftService {
    @Autowired
    private GiftMapper giftMapper;

    @Autowired
    private GiftTypeMapper giftTypeMapper;

    @Autowired
    private GiftSizeMapper giftSizeMapper;

    @Override
    public int add(GiftEntity entity) {
        giftMapper.add(entity);
        //添加赠品类型信息
        if(entity.getZptypes() != null){
            for (GiftTypeEntity typeEntity : entity.getZptypes()) {
                typeEntity.setGift_id(entity.getId());
                giftTypeMapper.add(typeEntity);
            }
        }
        //添加赠品尺码信息
        if(entity.getZpsizes()  != null){
            for (GiftSizeEntity sizeEntity : entity.getZpsizes()) {
                sizeEntity.setGift_id(entity.getId());
                giftSizeMapper.add(sizeEntity);
            }
        }
        return 0;
    }

    @Override
    public int edit(GiftEntity entity) {
        giftMapper.edit(entity);
        //清空赠品类型
        giftTypeMapper.del(entity.getId());
        //添加赠品类型信息
        if(entity.getZptypes() != null){
            for (GiftTypeEntity typeEntity : entity.getZptypes()) {
                typeEntity.setGift_id(entity.getId());
                giftTypeMapper.add(typeEntity);
            }
        }
        //清空赠品尺码
        giftSizeMapper.del(entity.getId());
        //添加赠品尺码信息
        if(entity.getZpsizes()  != null){
            for (GiftSizeEntity sizeEntity : entity.getZpsizes()) {
                sizeEntity.setGift_id(entity.getId());
                giftSizeMapper.add(sizeEntity);
            }
        }
        return 0;
    }

    @Override
    public GiftEntity findById(int id) {
        GiftEntity entity = giftMapper.findById(id);
        if(entity != null){
            entity.setZptypes(giftTypeMapper.findByGiftId(entity.getId()));
        }
        return entity;
    }

    @Override
    public List<GiftEntity> findAll() {
        return giftMapper.listAll();
    }

    @Override
    public Page<GiftEntity> findPage(String name, int page, int size) {
        long count = giftMapper.countFind(name);
        List<GiftEntity> list = giftMapper.findPage(name,page*size,size);
        return new Page<>(list,count);
    }
}
