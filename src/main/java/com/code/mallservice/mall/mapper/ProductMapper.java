package com.code.mallservice.mall.mapper;

import com.code.mallservice.mall.entity.ProductEntity;
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
     * 分页查询
     * @param product_id
     * @param product_name
     * @param user_id
     * @param page
     * @param size
     * @return
     */
    List<ProductEntity> findPage(@Param("product_id")int product_id, @Param("product_name")String product_name,
                   @Param("user_id")int user_id, @Param("page")int page, @Param("size")int size);

    /**
     * 分页统计
     * @param product_id
     * @param product_name
     * @param user_id
     * @return
     */
    Long findCount(@Param("product_id")int product_id, @Param("product_name")String product_name,
                                 @Param("user_id")int user_id);
}
