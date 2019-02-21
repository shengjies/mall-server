package com.code.mallservice.mall.service.impl;

import com.code.mallservice.mall.entity.ProductEntity;
import com.code.mallservice.mall.mapper.ImageMapper;
import com.code.mallservice.mall.mapper.ProductMapper;
import com.code.mallservice.mall.service.IProductService;
import com.code.mallservice.mall.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品信息
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ImageMapper imageMapper;
    /**
     * 添加产品
     * @param entity
     * @return
     */
    @Override
    public int add(ProductEntity entity) {
        switch (entity.getCountry()) {
            case "TW":
                entity.setUnit("NT$");
                break;
            case "TH":
                entity.setUnit("฿");
                break;
            case "MY":
                entity.setUnit("RM");
                break;
            default:
                entity.setUnit("$");
                break;
        }
        entity.setFacebook(entity.getFacebook().replace("[","").replace("]","").replaceAll("\"",""));
        entity.setUser_id(1);
        productMapper.add(entity);
        /**
         * 添加轮播图
         */
        for (Integer img : entity.getRoundImage()) {
            imageMapper.edit(img,entity.getId());
        }
        //营销策略
        if(entity.getCl_id() == 2){
            //自定义营销策略
        }
        return 0;
    }

    @Override
    public int edit(ProductEntity entity) {
        return 0;
    }

    @Override
    public Page<ProductEntity> findPage(int id, String product_name, int user_id, int page, int size) {
        long count = productMapper.findCount(id,product_name,user_id);
        List<ProductEntity> list = productMapper.findPage(id,product_name,user_id,page*size,size);
        return new Page<>(list,count);
    }
}
