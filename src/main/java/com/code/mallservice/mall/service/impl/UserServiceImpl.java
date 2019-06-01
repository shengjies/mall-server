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
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String,Object> findPage(String username,String role_code, int page, int size) {
        Map<String,Object> map = new HashMap<>();
        long count = userMapper.findCount(username,role_code);
        List<UserEntity> list = userMapper.findList(username, role_code,page * size, size);
        if(list != null){
            for (UserEntity entity : list) {
                if(entity.getRole_code().equals("role_group")){
                    //查询对应的组员
                    entity.setGroupId(userMapper.groupId(entity.getId()));
                }
            }
        }
        map.put("page",new Page<>(list, count));
        //查询所以业务员
        map.put("m",userMapper.findAllUser());
        return map;
    }

    @Override
    public UserEntity findByUserNameAndPwd(String username, String password) {
        return userMapper.findLogin(username, password);
    }

    @Override
    public void groupSalesman(int id, String sales) throws Exception {
        //修改对应组长的组员
        userMapper.editGroup(id);
        String[] ids = sales.split(",");
        for (String s : ids) {
            try{
                if(StringUtils.isEmpty(s.trim()))continue;
                userMapper.editGroupById(Integer.parseInt(s.trim()),id);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<UserEntity> findAll(UserEntity entity) {
        if(entity.getRole_code().equals("role_admin")){
            return userMapper.findAll();
        }else if(entity.getRole_code().equals("role_group")){
            List<UserEntity> list = userMapper.findAllByGroupId(entity.getId());
            list.add(entity);
            return list;
        }
        return Collections.emptyList();
    }

    @Override
    public List<String> groupId(int id) {
        return userMapper.groupId(id);
    }
}
