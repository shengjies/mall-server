package com.code.mallservice.mall.controller;

import com.code.mallservice.mall.entity.ProductEntity;
import com.code.mallservice.mall.service.IProductService;
import com.code.mallservice.mall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 产品
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/add")
    public Result add(ProductEntity entity){
        try {
            System.out.println(entity);
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
    public Result edit(ProductEntity entity){
        try {

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
}
