package com.code.mallservice.mall.mapper;

import com.code.mallservice.mall.entity.UrlEntity;
import org.apache.ibatis.annotations.*;

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
    Long findCount(@Param("code") String code,@Param("product_id") int product_id,
                   @Param("user_id") String user_id);

    /**
     * 分页查询
     * @param code
     * @param product_id
     * @param user_id
     * @param page
     * @param size
     * @return
     */
    List<UrlEntity> findPage(@Param("code") String code,@Param("product_id") int product_id,
                             @Param("user_id") String user_id,
                             @Param("page")int page,@Param("size")int size);

    /**
     * 按编号查询
     * @param id
     * @return
     */
    UrlEntity findById(@Param("id")String id);

    /**
     * 删除链接
     * @param id
     * @return
     */
    @Delete("delete from tab_url_info where id = #{id}")
    int del(@Param("id")String id);

    /**
     * 查询产品所以的链接
     * @param product_id
     * @return
     */
    @Select("SELECT * FROM mall.tab_url_info where product_id = #{product_id}")
    List<UrlEntity> findByProduct(@Param("product_id") int product_id);

    @Update("update tab_url_info set preview_url =#{preview_url} where id=#{id}")
    int editUrl(@Param("preview_url")String preview_url,@Param("id")String id);
}
