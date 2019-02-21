package com.code.mallservice.mall.controller;

import com.code.mallservice.mall.entity.FBEtity;
import com.code.mallservice.mall.service.IFBService;
import com.code.mallservice.mall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fb")
public class FBController {
    @Autowired
    private IFBService ifbService;
    /**
     * 添加
     * @param fbEtity
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody  FBEtity fbEtity){
        try {
            ifbService.add(fbEtity);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 编辑
     * @param etity
     * @return
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody FBEtity etity){
        try {
            ifbService.edit(etity);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 分页查询
     * @param fbname
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/find")
    public Result findPage(String fbname, @RequestParam(name = "page",defaultValue = "0")Integer page,
                           @RequestParam(name = "size",defaultValue = "50")Integer size){
        try {

            return Result.ok(ifbService.findPage(fbname,page,size));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 查询所有FB
     * @return
     */
    @RequestMapping("/findAll")
    public Result findAll(){
        try {

            return Result.ok(ifbService.findAll());
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }
}
