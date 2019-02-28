package com.code.mallservice.mall.service.impl;

import com.code.mallservice.mall.entity.*;
import com.code.mallservice.mall.mapper.*;
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

    @Autowired
    private SizeMapper sizeMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private PolicyMapper policyMapper;

    @Autowired
    private PolicyGirMapper policyGirMapper;

    /**
     * 添加产品
     *
     * @param entity
     * @return
     */
    @Override
    public int add(ProductEntity entity) {
        entity.setUnit(getUnit(entity.getCountry()));
        entity.setFacebook(entity.getFacebook().replace("[", "").replace("]", "").replaceAll("\"", ""));
        productMapper.add(entity);
        /**
         * 添加轮播图
         */
        if (entity.getRoundImage() != null && entity.getRoundImage().size() > 0) {
            for (Integer img : entity.getRoundImage()) {
                imageMapper.edit(img, entity.getId());
            }
        }

        //营销策略
        if (entity.getCl_id() == 2 && entity.getPolicys() != null) {
            //自定义营销策略
            for (PolicyEntity policy : entity.getPolicys()) {
                policy.setProduct_id(entity.getId());
                policyMapper.add(policy);
                //添加赠品配置
                if(policy.getGir() != null){
                    for (PolicyGirEntity gir : policy.getGir()) {
                        gir.setPolicy_id(policy.getId());
                        policyGirMapper.add(gir);
                    }
                }
            }
        }
        //尺码属性
        if (entity.getSizes() != null && entity.getSizes().size() > 0) {
            for (SizesEntity size : entity.getSizes()) {
                size.setProduct_id(entity.getId());
                sizeMapper.add(size);
            }
        }
        //类型
        if (entity.getTypes() != null && entity.getTypes().size() > 0) {
            for (TypeEntity type : entity.getTypes()) {
                type.setProduct_id(entity.getId());
                typeMapper.add(type);
            }
        }
        return 0;
    }

    private String getUnit(String country) {
        String unit;
        switch (country) {
            case "TW":
                unit = "NT$";
                break;
            case "TH":
                unit = "฿";
                break;
            case "MY":
                unit = "RM";
                break;
            default:
                unit = "$";
                break;
        }
        return unit;
    }

    @Override
    public int edit(ProductEntity entity) {
        entity.setUnit(getUnit(entity.getCountry()));
        entity.setFacebook(entity.getFacebook().replace("[", "").replace("]", "").replaceAll("\"", ""));
        productMapper.edit(entity);
        /**
         * 添加轮播图
         */
        if (entity.getRoundImage() != null && entity.getRoundImage().size() > 0) {
            for (Integer img : entity.getRoundImage()) {
                imageMapper.edit(img, entity.getId());
            }
        }
        //营销策略
        if (entity.getCl_id() == 2) {
            //清空赠品配置
            policyGirMapper.delByProductId(entity.getId());
            //清空策略
            policyMapper.delByProductId(entity.getId());
            //自定义营销策略
            for (PolicyEntity policy : entity.getPolicys()) {
                policy.setProduct_id(entity.getId());
                policyMapper.add(policy);
                //添加赠品配置
                if(policy.getGir() != null){
                    for (PolicyGirEntity gir : policy.getGir()) {
                        gir.setPolicy_id(policy.getId());
                        policyGirMapper.add(gir);
                    }
                }
            }
        }

        //尺码属性
        sizeMapper.del(entity.getId());
        if (entity.getSizes() != null && entity.getSizes().size() > 0) {
            for (SizesEntity size : entity.getSizes()) {
                size.setProduct_id(entity.getId());
                sizeMapper.add(size);
            }
        }

        //类型
        typeMapper.del(entity.getId());
        if (entity.getTypes() != null && entity.getTypes().size() > 0) {
            for (TypeEntity type : entity.getTypes()) {
                type.setProduct_id(entity.getId());
                typeMapper.add(type);
            }
        }
        return 0;
    }

    @Override
    public Page<ProductEntity> findPage(int id, String product_name, int user_id, int page, int size) {
        long count = productMapper.findCount(id, product_name, user_id);
        List<ProductEntity> list = productMapper.findPage(id, product_name, user_id, page * size, size);
        return new Page<>(list, count);
    }

    @Override
    public ProductEntity findAllById(int id) {
        ProductEntity entity = productMapper.findAllById(id);
        if(entity == null) return null;
        //查询类型
        entity.setTypes(typeMapper.findByProductId(entity.getId()));
        //查询对应的策略
        if(entity.getCl_id() == 2){
            entity.setPolicys(policyMapper.findByProductId(entity.getId()));
        }
        return entity;
    }

    /**
     * 用于下拉列表
     * @param user_id
     * @return
     */
    @Override
    public List<ProductEntity> listAll(int user_id) {
        return productMapper.listAll(user_id);
    }
}
