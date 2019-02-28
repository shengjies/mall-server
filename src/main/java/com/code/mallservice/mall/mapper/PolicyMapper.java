package com.code.mallservice.mall.mapper;

import com.code.mallservice.mall.entity.PolicyEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义策略
 */
@Mapper
public interface PolicyMapper {
    /**
     * 添加自定义策略
     * @param entity
     * @return
     */

    int add(PolicyEntity entity);

    /**
     * 按照产品编号查询对应的产品的自定义策略
     * @param product_id
     * @return
     */
    List<PolicyEntity> findByProductId(@Param("product_id")int product_id);


    @Delete("delete from tab_policy  where   product_id =#{product_id};")
    int delByProductId(@Param("product_id")int product_id);

}
