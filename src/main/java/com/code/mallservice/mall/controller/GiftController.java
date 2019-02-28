package com.code.mallservice.mall.controller;

import com.code.mallservice.mall.entity.GiftEntity;
import com.code.mallservice.mall.entity.UserEntity;
import com.code.mallservice.mall.service.IGiftService;
import com.code.mallservice.mall.utils.JwtUtils;
import com.code.mallservice.mall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 赠品
 */
@RestController
@RequestMapping("/gift")
public class GiftController {
    @Autowired
    private IGiftService giftService;
    /**
     * 添加
     * @param entity
     * @return
     */
    @RequestMapping("/add")
    public Result add(HttpServletRequest request, @RequestBody GiftEntity entity){
        try {
            String token = request.getHeader("token");
            UserEntity user = JwtUtils.getUserByToken(token);
            if(user != null) entity.setUser_id(user.getId());
            giftService.add(entity);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 修改
     * @param entity
     * @return
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody GiftEntity entity){
        try {
            giftService.edit(entity);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 分页查询
     * @param name
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/find")
    public Result findPage(String name,
                           @RequestParam(name = "page",defaultValue = "0")Integer page,
                           @RequestParam(name = "size",defaultValue = "50")Integer size){
        try {
            return Result.ok(giftService.findPage(name,page,size));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 根据编号查询赠品所有信息
     * @param id
     * @return
     */
    @RequestMapping("/find/{id}")
    public Result findById(@PathVariable("id")Integer id){
        try {
            return Result.ok(giftService.findById(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 查询所有赠品信息用于下拉列表
     * @return
     */
    @RequestMapping("/list/all")
    public Result findAll(){
        try {
            return Result.ok(giftService.findAll());
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }
}
