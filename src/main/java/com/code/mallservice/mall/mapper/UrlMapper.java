package com.code.mallservice.mall.mapper;

import com.code.mallservice.mall.entity.UrlEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 链接管理
 */
@Mapper
public interface UrlMapper {
    /**
     * 添加链接
     * @param entity
     * @return
     */
    int add(UrlEntity entity);

    /**
     * 修改链接
     * @param entity
     * @return
     */
    int edit(UrlEntity entity);

    /**
     * 分页统计
     * @param code
     * @param product_id
     * @param user_id
     * @return
     */
    Long findCount(@Param("code") String code,@Param("product_id") int product_id,@Param("user_id") int user_id);

    /**
     * 分页查询
     * @param code
     * @param product_id
     * @param user_id
     * @param page
     * @param size
     * @return
     */
    List<UrlEntity> findPage(@Param("code") String code,@Param("product_id") int product_id,@Param("user_id") int user_id,
                             @Param("page")int page,@Param("size")int size);
}
