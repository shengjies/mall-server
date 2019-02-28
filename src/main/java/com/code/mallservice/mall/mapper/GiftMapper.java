package com.code.mallservice.mall.mapper;

import com.code.mallservice.mall.entity.GiftEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 赠品
 */
@Mapper
public interface GiftMapper {
    /**
     * 添加赠品
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
     * 根据编号查询赠品信息
     * @param id
     * @return
     */
    GiftEntity findById(@Param("id")int id);

    /**
     * 分页统计
     * @param name
     * @return
     */
    Long countFind(@Param("name") String name);

    /**
     * 分页查询
     * @param name
     * @param page
     * @param size
     * @return
     */
    List<GiftEntity> findPage(@Param("name")String name,@Param("page")int page,@Param("size")int size);

    /**
     * 查询所有赠品
     * @return
     */
    List<GiftEntity> listAll();
}
