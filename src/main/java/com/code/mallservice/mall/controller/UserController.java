package com.code.mallservice.mall.controller;

import com.alibaba.fastjson.JSON;
import com.code.mallservice.mall.entity.UserEntity;
import com.code.mallservice.mall.service.IUserService;
import com.code.mallservice.mall.utils.JwtUtils;
import com.code.mallservice.mall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户信息
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * 添加用户信息
     * @param entity
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody UserEntity entity){
        try {
            userService.add(entity);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 修改用户信息
     * @param entity
     * @return
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody UserEntity entity){
        try {
            userService.edit(entity);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 分页查询
     * @param username
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/find")
    public Result findPage(String username, @RequestParam(name = "page",defaultValue = "0")Integer page,
                           @RequestParam(name = "size",defaultValue = "50")Integer size){
        try {
            return Result.ok(userService.findPage(username,page,size));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public Result login(String username,String password){
        try {
            Map<String,String> resultMap = new HashMap<>();
            UserEntity entity = userService.findByUserNameAndPwd(username,password);
            if(entity == null) return Result.userorpasserror();
            Map<String,Object>map = new HashMap<>();
            map.put(JwtUtils.CLAIM_KEY_USER,JSON.toJSONString(entity));
            String token = JwtUtils.getToken(map);
            resultMap.put("r",entity.getRole_code());
            resultMap.put("t",token);
            return Result.ok(resultMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }
}
