package com.code.mallservice.mall.controller;

import com.code.mallservice.mall.entity.ProductEntity;
import com.code.mallservice.mall.service.IProductService;
import com.code.mallservice.mall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 产品
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/add")
    public Result add(@RequestBody ProductEntity entity){
        try {
//            System.out.println(entity);
            productService.add(entity);
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
    public Result edit(@RequestBody ProductEntity entity){
        try {
            System.out.println(entity);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 分页查询
     * @param id
     * @param name
     * @param user_id
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/find")
    public Result findPage(@RequestParam(name = "id",defaultValue = "0")Integer id,
                           String name,@RequestParam(name = "user_id",defaultValue = "-1")Integer user_id,
                           @RequestParam(name = "page",defaultValue = "0")Integer page,
                           @RequestParam(name = "size",defaultValue = "50")Integer size){
        try {
            return Result.ok(productService.findPage(id,name,user_id,page,size));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 按照编号查询
     * @param id
     * @return
     */
    @RequestMapping("/find/{id}")
    public Result findById(@PathVariable("id")int id){
        try {
            return Result.ok(productService.findAllById(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 用于下拉列表
     * @return
     */
    @RequestMapping("/list/all")
    public Result listAll(){
        try {
            return Result.ok(productService.listAll(-1));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }
}
