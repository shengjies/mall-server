package com.code.mallservice.mall.mapper;

import com.code.mallservice.mall.entity.GiftTypeEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 赠品类型
 */
@Mapper
public interface GiftTypeMapper {
    /**
     * 添加赠品类别
     * @param entity
     * @return
     */
    @Insert("INSERT INTO `mall`.`tab_gift_type`(`zptlable`,`zptvalue`,`zptimg`,`gift_id`,`c_date`)" +
            "VALUES(#{zptlable},#{zptvalue},#{zptimg},#{gift_id},now());")
    int add(GiftTypeEntity entity);

    /**
     * 删除赠品类型
     * @param gift
     * @return
     */
    @Delete("DELETE FROM `mall`.`tab_gift_type` WHERE gift_id = #{gift_id};")
    int del(@Param("gift_id")int gift);

    /**
     * 根据赠品编号查询对应的类型
     * @param gift_id
     * @return
     */
    List<GiftTypeEntity> findByGiftId(@Param("gift_id")int gift_id);
}
