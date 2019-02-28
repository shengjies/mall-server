package com.code.mallservice.mall.mapper;

import com.code.mallservice.mall.entity.PolicyGirEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 套餐赠品配置
 */
@Mapper
public interface PolicyGirMapper {
    /**
     * 添加套餐赠品配置
     * @param entity
     * @return
     */
    @Insert("INSERT INTO `mall`.`tab_policy_gir`(`zpnum`,`zpname`,`policy_id`,`c_date`)" +
            "VALUES(#{zpnum},#{zpname},#{policy_id},now())")
    int add(PolicyGirEntity entity);

    @Delete("delete from tab_policy_gir where policy_id in (SELECT id FROM mall.tab_policy where product_id =#{product_id} )")
    int delByProductId(@Param("product_id")int product_id);
}
