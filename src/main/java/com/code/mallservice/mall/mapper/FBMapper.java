package com.code.mallservice.mall.mapper;

import com.code.mallservice.mall.entity.FBEtity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * FB 管理
 */
@Mapper
public interface FBMapper {
    /**
     * 添加
     * @param etity
     * @return
     */
    int add(FBEtity etity);

    /**
     * 修改
     * @param etity
     * @return
     */
    int edit(FBEtity etity);

    /**
     * 分页统计
     * @param fbname
     * @return
     */
    Long findCount(@Param("fbname") String fbname);

    /**
     * 分页查询
     * @param fbname
     * @param page
     * @param size
     * @return
     */
    List<FBEtity> findPage(@Param("fbname")String fbname,@Param("page")int page,@Param("size")int size);

    /**
     *
     * @param id
     * @return
     */
    FBEtity findById(@Param("id")int id);

    /**
     * 查询所有FB账户
     * @return
     */
    List<FBEtity> findAll();
}
