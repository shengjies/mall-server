package com.code.mallservice.mall.mapper;

import com.code.mallservice.mall.entity.WLEntity;
import com.code.mallservice.mall.entity.WlInfoEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * 物流
 */
@Mapper
public interface WLMapper {
    /**
     *
     * 查询对应物流数据表中是否存在
     * @param order_id
     * @return
     */
    @Select("SELECT * FROM mall.tab_wl where order_id = #{order_id} limit 1")
    WLEntity findByOrderId(@Param("order_id")int order_id);

    /**
     * 添加物流数据表信息
     * @param wlEntity
     * @return
     */
    @Insert("INSERT INTO `mall`.`tab_wl`(`order_id`,`wl_status`,`sign`,`wl`,`c_date`)\n" +
            "VALUES(#{order_id},-1,0,-1,now());")
    int add(WLEntity wlEntity);

    /**
     * 查询物流信息
     * @param order_id
     * @return
     */
    @Select("SELECT * FROM mall.tab_wl_info where order_id= #{order_id} order by `date` asc")
    List<WlInfoEntity> findAllWlInfo(@Param("order_id")int order_id);

    /**
     * 修改物流
     * @param order_id
     * @param trackNum
     * @param wl
     * @return
     */
    @Update("update tab_wl set wl=#{wl},sign =1,tracking_number=#{trackNum} where order_id =#{order_id} limit 1")
    int editWl(@Param("order_id")int order_id,@Param("trackNum")String trackNum,@Param("wl")int wl);
}
