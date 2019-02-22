package com.code.mallservice.mall.controller;

import com.code.mallservice.mall.service.IDomainService;
import com.code.mallservice.mall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
            return Result.ok(domainService.findByUser(-1));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }
}
