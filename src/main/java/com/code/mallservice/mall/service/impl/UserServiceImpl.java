package com.code.mallservice.mall.service.impl;

import com.code.mallservice.mall.entity.DomailEntity;
import com.code.mallservice.mall.entity.FBEtity;
import com.code.mallservice.mall.entity.UserEntity;
import com.code.mallservice.mall.mapper.DomainMapper;
import com.code.mallservice.mall.mapper.FBMapper;
import com.code.mallservice.mall.mapper.UserMapper;
import com.code.mallservice.mall.service.IUserService;
import com.code.mallservice.mall.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户信息
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FBMapper fbMapper;

    @Autowired
    private DomainMapper domainMapper;

    @Override
    public int add(UserEntity entity) {
        entity.setRole_code("role_user");
        int result = userMapper.add(entity);
        for (Integer fb : entity.getFb_id()) {
            FBEtity fbEtity = fbMapper.findById(fb);
            if(fbEtity != null){
                userMapper.addUserFb(entity.getId(),fb);
            }
        }
        for (Integer d : entity.getDomain_id()) {
            DomailEntity  domailEntity = domainMapper.findById(d);
            if(domailEntity != null){
                userMapper.addUserDomain(entity.getId(),domailEntity.getId());
            }
        }
        return result;
    }

    @Override
    public int edit(UserEntity entity) {
        //删除FB
        userMapper.delUserFb(entity.getId());
        for (Integer item : entity.getFb_id()) {
            FBEtity fbEtity = fbMapper.findById(item);
            if(fbEtity != null){
                userMapper.addUserFb(entity.getId(),item);
            }
        }
        //删除域名
        userMapper.delUserDomain(entity.getId());
        for (Integer d : entity.getDomain_id()) {
            DomailEntity  domailEntity = domainMapper.findById(d);
            if(domailEntity != null){
                userMapper.addUserDomain(entity.getId(),domailEntity.getId());
            }
        }
        return userMapper.edit(entity);
    }

    @Override
    public Page<UserEntity> findPage(String username, int page, int size) {
        long count = userMapper.findCount(username);
        List<UserEntity> list = userMapper.findList(username, page * size, size);
        return new Page<>(list, count);
    }

    @Override
    public UserEntity findByUserNameAndPwd(String username, String password) {
        return userMapper.findLogin(username, password);
    }
}
