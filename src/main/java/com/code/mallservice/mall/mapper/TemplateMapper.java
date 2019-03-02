package com.code.mallservice.mall.mapper;

import com.code.mallservice.mall.entity.TemplateEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 模板操作
 */
@Mapper
public interface TemplateMapper {
    /**
     * 添加模板
     * @param entity
     * @return
     */
    int add(TemplateEntity entity);

    /**
     * 修改模板
     * @param entity
     * @return
     */
    int edit(TemplateEntity entity);

    /**
     * 按编号查询
     * @param id
     * @return
     */
    TemplateEntity findById(@Param("id")int id);

    /**
     * 分页统计
     * @param t_name
     * @return
     */
    Long countFind(@Param("t_name")String t_name);

    /**
     * 分页查询
     * @param t_name
     * @param page
     * @param size
     * @return
     */
    List<TemplateEntity> findPage(@Param("t_name")String t_name,
                                  @Param("page")int page,@Param("size")int size);

    @Select("SELECT * FROM mall.tab_template order by id desc;")
    List<TemplateEntity> listAll();
}
