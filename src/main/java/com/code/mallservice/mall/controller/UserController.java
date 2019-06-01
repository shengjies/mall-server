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

import javax.servlet.http.HttpServletRequest;
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
     *
     * @param entity
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody UserEntity entity) {
        try {
            userService.add(entity);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 修改用户信息
     *
     * @param entity
     * @return
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody UserEntity entity) {
        try {
            userService.edit(entity);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 分页查询
     *
     * @param username
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/find")
    public Result findPage(String username, String role_code,
                           @RequestParam(name = "page", defaultValue = "0") Integer page,
                           @RequestParam(name = "size", defaultValue = "50") Integer size) {
        try {
            return Result.ok(userService.findPage(username, role_code, page, size));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public Result login(String username, String password) {
        try {
            Map<String, String> resultMap = new HashMap<>();
            UserEntity entity = userService.findByUserNameAndPwd(username, password);
            if (entity == null) return Result.userorpasserror();
            Map<String, Object> map = new HashMap<>();
            map.put(JwtUtils.CLAIM_KEY_USER, JSON.toJSONString(entity));
            String token = JwtUtils.getToken(map);
            resultMap.put("r", entity.getRole_code());
            resultMap.put("t", token);
            resultMap.put("n", entity.getUsername());
            return Result.ok(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 分配组员
     *
     * @param id
     * @param sales
     * @return
     */
    @RequestMapping("/salesman")
    public Result groupSalesman(Integer id, String sales) {
        try {
            if (id == null) return Result.error();
            userService.groupSalesman(id, sales.replace("[", "")
                    .replace("]", "").replaceAll("\"", ""));
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 查询用户用于下拉列表
     * @param request
     * @return
     */
    @RequestMapping("/list/all")
    public Result findAll(HttpServletRequest request) {
        try {
            UserEntity entity = JwtUtils.getUserByToken(request.getHeader("token"));
            if (entity == null) return Result.error();
            return Result.ok(userService.findAll(entity));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }
}
