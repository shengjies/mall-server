package com.code.mallservice.mall.service.impl;

import com.code.mallservice.mall.entity.FBEtity;
import com.code.mallservice.mall.mapper.FBMapper;
import com.code.mallservice.mall.service.IFBService;
import com.code.mallservice.mall.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * FB管理
 */
@Service
public class FBServiceImpl implements IFBService {
    @Autowired
    private FBMapper fbMapper;

    @Override
    public int add(FBEtity etity) {
        return fbMapper.add(etity);
    }

    @Override
    public int edit(FBEtity etity) {
        return fbMapper.edit(etity);
    }

    @Override
    public Page<FBEtity> findPage(String fbname, int page, int size) {
        long count = fbMapper.findCount(fbname);
        List<FBEtity> list = fbMapper.findPage(fbname, page * size, size);
        return new Page<>(list, count);
    }

    @Override
    public List<FBEtity> findAll() {
        return fbMapper.findAll();
    }

    @Override
    public List<FBEtity> findByUser(int user_id) {
        return fbMapper.findByUser(user_id);
    }
}
