package com.code.mallservice.mall.controller;

import com.code.mallservice.mall.entity.DomailEntity;
import com.code.mallservice.mall.entity.UserEntity;
import com.code.mallservice.mall.service.IDomainService;
import com.code.mallservice.mall.utils.AuthorUtils;
import com.code.mallservice.mall.utils.JwtUtils;
import com.code.mallservice.mall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 域名管理
 */
@RestController
@RequestMapping("/domain")
public class DomainController {
    @Autowired
    private IDomainService domainService;
    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/findAll")
    public Result findAll(){
        try {
            return Result.ok(domainService.findAll());
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 查询对应用户下的域名
     * @param request
     * @return
     */
    @RequestMapping("/list/all")
    public Result findAllByUser(HttpServletRequest request){
        try {
            UserEntity entity = JwtUtils.getUserByToken(request.getHeader("token"));
            if(entity == null) return Result.error();
            return Result.ok(domainService.findByUser(AuthorUtils.getUserRole(entity)));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 添加域名
     * @param entity
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody DomailEntity entity){
        try {
            domainService.add(entity);
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
    public Result edit(@RequestBody DomailEntity entity){
        try {
            domainService.edit(entity);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 分页查询
     * @param domain_name
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/find")
    public Result findPage(String domain_name,
                           @RequestParam(name = "page",defaultValue = "0")Integer page,
                           @RequestParam(name = "size",defaultValue = "50")Integer size){
        try {
            return Result.ok(domainService.findPage(domain_name,page,size));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }
}
