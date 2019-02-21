package com.code.mallservice.mall.controller;

import com.code.mallservice.mall.service.IDomainService;
import com.code.mallservice.mall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
