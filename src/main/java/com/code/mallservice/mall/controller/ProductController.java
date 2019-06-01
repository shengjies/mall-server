package com.code.mallservice.mall.controller;

import com.alibaba.fastjson.JSON;
import com.code.mallservice.mall.entity.ProductEntity;
import com.code.mallservice.mall.entity.UserEntity;
import com.code.mallservice.mall.service.IProductService;
import com.code.mallservice.mall.service.IUserService;
import com.code.mallservice.mall.utils.AuthorUtils;
import com.code.mallservice.mall.utils.JwtUtils;
import com.code.mallservice.mall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 产品
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IUserService userService;

    @RequestMapping("/add")
    public Result add(HttpServletRequest request,
                      @RequestBody ProductEntity entity){
        try {
            String token = request.getHeader("token");
            UserEntity user = JwtUtils.getUserByToken(token);
            if(user != null)entity.setUser_id(user.getId());
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
            productService.edit(entity);
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
    public Result findPage(HttpServletRequest request,
                           @RequestParam(name = "id",defaultValue = "0")Integer id,
                           String name,
                           @RequestParam(name = "user_id",defaultValue = "")String user_id,
                           @RequestParam(name = "page",defaultValue = "0")Integer page,
                           @RequestParam(name = "size",defaultValue = "50")Integer size){
        try {
            UserEntity entity = JwtUtils.getUserByToken(request.getHeader("token"));
            if(entity == null)return Result.error();
            //判断是否是组长
            if(StringUtils.isEmpty(user_id) && AuthorUtils.userRole(entity) == 1){
                List<String> ids = userService.groupId(entity.getId());
                String item = entity.getId()+"";
                if(ids.size()>0){
                    item+=","+JSON.toJSONString(ids);
                }
                user_id =item.replaceAll("\"","").replace("[","").replace("]","");
            }else if(StringUtils.isEmpty(user_id) && AuthorUtils.userRole(entity) == 2){
                user_id=entity.getId()+"";
            }
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
    public Result listAll(HttpServletRequest request){
        try {
            UserEntity entity = JwtUtils.getUserByToken(request.getHeader("token"));
            if(entity == null)return Result.error();
            return Result.ok(productService.listAll(AuthorUtils.getUserRole(entity)));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 复制产品信息
     * @param id
     * @param country
     * @return
     */
    @RequestMapping("/copy")
    public Result copyInfo(Integer id,String country){
        try {
            if(id == null || StringUtils.isEmpty(country)){
                return Result.error();
            }
            productService.copyInfo(id,country);
            return  Result.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 按产品编号查询对应的属性
     * @param product_id
     * @return
     */
    @RequestMapping("/attr")
    public Result findAttrByProductId(Integer product_id){
        try {
            if(product_id == null)return Result.error();
            return Result.ok(productService.findAttrByProductId(product_id));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 删除产品
     * @param id
     * @return
     */
    @RequestMapping("/del")
    public Result del(@RequestParam(name = "id",defaultValue = "-1")Integer id){
        try {
            if(id == -1)return Result.error();
            return Result.ok(productService.del(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }
}
