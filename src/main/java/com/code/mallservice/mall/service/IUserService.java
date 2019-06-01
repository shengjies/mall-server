package com.code.mallservice.mall.service;

import com.code.mallservice.mall.entity.UserEntity;
import com.code.mallservice.mall.utils.Page;

import java.util.List;
import java.util.Map;

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
    Map<String,Object> findPage(String username,String role_code, int page, int size);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    UserEntity findByUserNameAndPwd(String username,String password);

    /**
     * 分配组员
     * @param id
     * @param sales
     * @throws Exception
     */
    void  groupSalesman(int id,String sales) throws Exception;

    /**
     * 查询用户下拉列表
     * @param entity
     * @return
     */
    List<UserEntity> findAll(UserEntity entity);

    /**
     * 根据组长查询对应的信息
     * @param id
     * @return
     */
    List<String> groupId(int id);
}
