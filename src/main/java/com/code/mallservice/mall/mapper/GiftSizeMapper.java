package com.code.mallservice.mall.mapper;

import com.code.mallservice.mall.entity.GiftSizeEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 赠品尺码
 */
@Mapper
public interface GiftSizeMapper {
    /**
     * 赠品尺码
     * @param entity
     * @return
     */
    @Insert("INSERT INTO `mall`.`tab_gift_size`(`zpzlable`,`zpzvalue`,`gift_id`,`c_date`)" +
            "VALUES(#{zpzlable},#{zpzvalue},#{gift_id},now());")
    int add(GiftSizeEntity entity);

    /**
     * 删除赠品尺码
     * @param gift_id
     * @return
     */
    @Delete("DELETE FROM `mall`.`tab_gift_size` WHERE gift_id =#{gift_id};")
    int del(@Param("gift_id")int gift_id);
}
