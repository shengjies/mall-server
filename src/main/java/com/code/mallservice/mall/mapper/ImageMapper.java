package com.code.mallservice.mall.mapper;

import com.code.mallservice.mall.entity.ImageEntity;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ImageMapper {
    /**
     * 添加图片
     * @param entity
     * @return
     */
    @Insert("INSERT INTO `mall`.`tab_imags`(`url`,`min_url`,`c_date`)\n" +
            "VALUES(#{url},#{min_url},now());")
    @Options(useGeneratedKeys = true,keyProperty = "uid")
    int add(ImageEntity entity);

    /**
     * 修改信息
     * @param id
     * @param product_id
     * @return
     */
    @Update("update tab_imags set product_id=#{product_id} where uid=#{id}")
    int edit(@Param("id")int id,@Param("product_id")int product_id);
}
