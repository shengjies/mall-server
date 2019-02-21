package com.code.mallservice.mall.service.impl;

import com.code.mallservice.mall.entity.ImageEntity;
import com.code.mallservice.mall.mapper.ImageMapper;
import com.code.mallservice.mall.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements IImageService {
    @Autowired
    private ImageMapper imageMapper;

    @Override
    public int add(ImageEntity entity) {
        return imageMapper.add(entity);
    }
}
