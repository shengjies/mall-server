package com.code.mallservice.mall.controller;

import com.code.mallservice.mall.entity.CommentEntity;
import com.code.mallservice.mall.service.ICommentService;
import com.code.mallservice.mall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 产品评论
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    /**
     * 添加评论
     * @param entity
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody CommentEntity entity){
        try {
            commentService.add(entity);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 编辑评论
     * @param entity
     * @return
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody CommentEntity entity){
        try {
            commentService.edit(entity);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 分页查询
     * @param product_id
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/find")
    public Result findPage(@RequestParam(name = "product_id",defaultValue = "-1")Integer product_id,
                           @RequestParam(name = "page",defaultValue = "0")Integer page,
                           @RequestParam(name = "size",defaultValue = "50")Integer size){
        try {
            if(product_id == -1)return Result.error();
            return Result.ok(commentService.findPage(product_id,page,size));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }
}
