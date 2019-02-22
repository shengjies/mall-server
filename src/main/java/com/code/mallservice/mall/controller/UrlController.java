package com.code.mallservice.mall.controller;

import com.code.mallservice.mall.entity.UrlEntity;
import com.code.mallservice.mall.service.IUrlService;
import com.code.mallservice.mall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/urls")
public class UrlController {

    @Autowired
    private IUrlService urlService;

    /**
     * 添加
     * @param urlEntity
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody UrlEntity urlEntity){
        try {
            urlService.add(urlEntity);
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
    public Result edit(@RequestBody UrlEntity entity){
        try {
            urlService.edit(entity);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 分页查询
     * @param code
     * @param product_id
     * @param user_id
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/find")
    public Result findPage(String code, @RequestParam(name = "product_id" ,defaultValue = "-1")Integer product_id,
                           @RequestParam(name = "user_id",defaultValue = "-1")Integer user_id,
                           @RequestParam(name = "page",defaultValue = "0")Integer page,
                           @RequestParam(name = "size",defaultValue = "50")Integer size){
        try {
            return Result.ok(urlService.findPage(code,product_id,user_id,page,size));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }
}
