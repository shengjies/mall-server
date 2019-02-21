package com.code.mallservice.mall.service;

import com.code.mallservice.mall.entity.ImageEntity;

/**
 * 图片素材管理
 */
public interface IImageService {
    /**
     * 添加图片
     * @param entity
     * @return
     */
    int add(ImageEntity entity);
}
