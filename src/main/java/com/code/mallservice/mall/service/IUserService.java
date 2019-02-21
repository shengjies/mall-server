package com.code.mallservice.mall.service;

import com.code.mallservice.mall.entity.UserEntity;
import com.code.mallservice.mall.utils.Page;

/**
 * 用户信息
 */
public interface IUserService {
    /**
     * 添加用户
     * @param entity
     * @return
     */
    int add(UserEntity entity);

    /**
     * 修改用户信息
     * @param entity
     * @return
     */
    int edit(UserEntity entity);

    /**
     * 分页查询
     * @param username 用户名称
     * @param page 页面大小
     * @param size 页数大小
     * @return
     */
    Page<UserEntity> findPage(String username,int page,int size);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    UserEntity findByUserNameAndPwd(String username,String password);
}
