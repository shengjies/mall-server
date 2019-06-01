package com.code.mallservice.mall.mapper;

import com.code.mallservice.mall.entity.SizesEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 尺码属性
 */
@Mapper
public interface SizeMapper {
    /**
     * 添加尺码属性
     * @param entity
     * @return
     */
    @Insert("INSERT INTO `mall`.`tab_size_info`(`size_label`,`size_value`,`product_id`,`c_date`)\n" +
            "VALUES(#{size_label},#{size_value},#{product_id},now());")
    int add(SizesEntity entity);

    /**
     * 删除尺码属性
     * @param product_id
     * @return
     */
    @Delete("delete from tab_size_info where product_id=#{product_id}")
    int del(@Param("product_id")int product_id);

    /**
     * 根据产品编号查询对应的产品尺码属性
     * @param product_id
     * @return
     */
    @Select("SELECT * FROM mall.tab_size_info where product_id = #{product_id} order by id desc;")
    List<SizesEntity> findSizeByProductId(@Param("product_id")int product_id);
}
