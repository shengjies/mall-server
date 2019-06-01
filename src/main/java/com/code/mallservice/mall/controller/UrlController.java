package com.code.mallservice.mall.controller;

import com.alibaba.fastjson.JSON;
import com.code.mallservice.mall.entity.UrlEntity;
import com.code.mallservice.mall.entity.UserEntity;
import com.code.mallservice.mall.service.IUrlService;
import com.code.mallservice.mall.service.IUserService;
import com.code.mallservice.mall.utils.AuthorUtils;
import com.code.mallservice.mall.utils.JwtUtils;
import com.code.mallservice.mall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/urls")
public class UrlController {

    @Autowired
    private IUrlService urlService;

    @Autowired
    private IUserService userService;

    /**
     * 添加
     * @param urlEntity
     * @return
     */
    @RequestMapping("/add")
    public Result add(HttpServletRequest request,@RequestBody UrlEntity urlEntity){
        try {
            UserEntity entity = JwtUtils.getUserByToken(request.getHeader("token"));
            if(entity != null){
                urlEntity.setUser_id(entity.getId());
            }
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
    public Result findPage(HttpServletRequest request,
                           String code, @RequestParam(name = "product_id" ,defaultValue = "-1")Integer product_id,
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
            return Result.ok(urlService.findPage(code,product_id, user_id,page,size));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/del")
    public Result del(String id){
        try {
            return Result.ok(urlService.del(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }
}
