package com.code.mallservice.mall.service;

import com.code.mallservice.mall.entity.GiftEntity;
import com.code.mallservice.mall.utils.Page;

import java.util.List;

/**
 * 赠品
 */
public interface IGiftService {
    /**
     * 添加赠品信息
     * @param entity
     * @return
     */
    int add(GiftEntity entity);

    /**
     * 修改赠品
     * @param entity
     * @return
     */
    int edit(GiftEntity entity);

    /**
     * 按编号查询
     * @param id
     * @return
     */
    GiftEntity findById(int id);

    /**
     * 查询所有
     * @return
     */
    List<GiftEntity> findAll();

    /**
     * 分页查询
     * @param name
     * @param page
     * @param size
     * @return
     */
    Page<GiftEntity> findPage(String name,int page,int size);
}
