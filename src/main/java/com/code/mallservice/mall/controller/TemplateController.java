package com.code.mallservice.mall.controller;

import com.code.mallservice.mall.entity.TemplateEntity;
import com.code.mallservice.mall.service.ITemplateService;
import com.code.mallservice.mall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tem")
public class TemplateController {

    @Autowired
    private ITemplateService templateService;

    /**
     * 添加
     * @param entity
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TemplateEntity entity){
        try {
            templateService.add(entity);
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
    public Result edit(@RequestBody TemplateEntity entity){
        try {
            templateService.edit(entity);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 分页查询
     * @param t_name
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/find")
    public Result findPage(String t_name,
                           @RequestParam(name = "page",defaultValue = "0")Integer page,
                           @RequestParam(name = "size",defaultValue = "50")Integer size){
        try {
            return Result.ok(templateService.findPage(t_name,page,size));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 查询所有模板
     * @return
     */
    @RequestMapping("/list/all")
    public Result listAll(){
        try {
            return Result.ok(templateService.listAll());
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }
}
