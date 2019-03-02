package com.code.mallservice.mall.service;

import com.code.mallservice.mall.entity.TemplateEntity;
import com.code.mallservice.mall.utils.Page;

import java.util.List;

/**
 * 模板操作
 */
public interface ITemplateService {
    /**
     * 添加模板
     * @param entity
     * @return
     */
    int add(TemplateEntity entity);

    /**
     * 编辑模板
     * @param entity
     * @return
     */
    int edit(TemplateEntity entity);

    /**
     * 分页查询
     * @param t_name 模板名称
     * @param page
     * @param size
     * @return
     */
    Page<TemplateEntity> findPage(String t_name,int page,int size);

    List<TemplateEntity> listAll();

}
