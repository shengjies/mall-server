package com.code.mallservice.mall.service.impl;

import com.code.mallservice.mall.entity.CommentEntity;
import com.code.mallservice.mall.mapper.CommentMapper;
import com.code.mallservice.mall.service.ICommentService;
import com.code.mallservice.mall.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int add(CommentEntity entity) {
        return commentMapper.add(entity);
    }

    @Override
    public int edit(CommentEntity entity) {
        return commentMapper.edit(entity);
    }

    @Override
    public Page<CommentEntity> findPage(int product_id, int page, int size) {
        long count = commentMapper.countFind(product_id);
        List<CommentEntity> list = commentMapper.findPage(product_id,page*size,size);
        return new Page<>(list,count);
    }
}
