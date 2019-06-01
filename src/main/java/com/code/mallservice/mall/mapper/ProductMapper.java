package com.code.mallservice.mall.mapper;

import com.code.mallservice.mall.entity.ProductEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    /**
     * 添加产品
     * @param entity
     * @return
     */
    int add(ProductEntity entity);

    /**
     * 修改产品
     * @param entity
     * @return
     */
    int edit(ProductEntity entity);

    /**
     * 分页查询
     * @param product_id
     * @param product_name
     * @param user_id
     * @param page
     * @param size
     * @return
     */
    List<ProductEntity> findPage(@Param("product_id")int product_id, @Param("product_name")String product_name,
                   @Param("user_id")String user_id, @Param("page")int page, @Param("size")int size);

    /**
     * 分页统计
     * @param product_id
     * @param product_name
     * @param user_id
     * @return
     */
    Long findCount(@Param("product_id")int product_id, @Param("product_name")String product_name,
                                 @Param("user_id")String user_id);

    /**
     * 按照编号查询产品所有信息
     * @param product_id
     * @return
     */
    ProductEntity findAllById(@Param("product_id")int product_id);

    /**
     * 查询产品基本信息
     * @param product_id
     * @return
     */
    ProductEntity findById(@Param("product_id")int product_id);

    /**
     * 用于下拉列表
     * @return
     */
    List<ProductEntity> listAll(@Param("user_id")int user_id);

    /**
     * 删除产品
     * @param id
     * @return
     */
    @Delete("delete from tab_product where id = #{id}")
    int del(@Param("id")int id);
}
