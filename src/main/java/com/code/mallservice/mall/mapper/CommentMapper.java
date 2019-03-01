package com.code.mallservice.mall.mapper;

import com.code.mallservice.mall.entity.CommentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
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
     * 分页统计
     * @param product_id
     * @return
     */
    Long countFind(@Param("product_id")int product_id);

    /**
     * 分页查询
     * @param product_id
     * @param page
     * @param size
     * @return
     */
    List<CommentEntity> findPage(@Param("product_id")int product_id,@Param("page")int page,@Param("size")int size);

    /**
     * 查询对应产品所有评论
     * @param product_id
     * @return
     */
    List<CommentEntity> findByProduct(@Param("product_id")int product_id);
}
