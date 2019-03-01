package com.code.mallservice.mall.service;

import com.code.mallservice.mall.entity.CommentEntity;
import com.code.mallservice.mall.utils.Page;

/**
 * 产品评论
 */
public interface ICommentService {
    /**
     * 添加评论
     * @param entity
     * @return
     */
    int add(CommentEntity entity);

    /**
     * 编辑评论
     * @param entity
     * @return
     */
    int edit(CommentEntity entity);

    /**
     * 分页查询
     * @param product_id
     * @param page
     * @param size
     * @return
     */
    Page<CommentEntity> findPage(int product_id,int page,int size);
}
