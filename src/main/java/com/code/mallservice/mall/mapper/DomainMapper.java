package com.code.mallservice.mall.mapper;

import com.code.mallservice.mall.entity.DomailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 域名管理
 */
@Mapper
public interface DomainMapper {
    /**
     * 查询所有
     * @return
     */
    List<DomailEntity> findAll();

    /**
     * 按照编号查询
     * @param id
     * @return
     */
    DomailEntity findById(@Param("id")int id);
}
