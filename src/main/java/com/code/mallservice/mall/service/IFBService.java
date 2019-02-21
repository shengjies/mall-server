package com.code.mallservice.mall.service;

import com.code.mallservice.mall.entity.FBEtity;
import com.code.mallservice.mall.utils.Page;

import java.util.List;

/**
 * FB管理
 */
public interface IFBService {
    /**
     * 添加
     * @param etity
     * @return
     */
    int add(FBEtity etity);

    /**
     * 修改
     * @param etity
     * @return
     */
    int edit(FBEtity etity);

    /**
     * 分页查询
     * @param fbname
     * @param page
     * @param size
     * @return
     */
    Page<FBEtity> findPage(String fbname,int page,int size);

    /**
     * 查询所有FB
     * @return
     */
    List<FBEtity> findAll();
}
