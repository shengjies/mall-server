package com.code.mallservice.mall.mapper;

import com.code.mallservice.mall.entity.TypeEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 产品类型
 */
@Mapper
public interface TypeMapper {
    /**
     * 添加类型
     * @param entity
     * @return
     */
    @Insert("INSERT INTO `mall`.`tab_type_info`(`type_lable`,`type_value`,`img_id`,`product_id`)\n" +
            "VALUES(#{type_lable},#{type_value},#{img_id},#{product_id});")
    int add(TypeEntity entity);

    /**
     * 根据产品删除
     * @param product_id
     * @return
     */
    @Delete("DELETE FROM `mall`.`tab_type_info`  where product_id=#{product_id}")
    int del(@Param("product_id")int product_id);

    /**
     * 按照产品编号查询
     * @param product_id
     * @return
     */
    List<TypeEntity> findByProductId(@Param("product_id")int product_id);
}
