package com.code.mallservice.mall.controller;

import com.code.mallservice.mall.entity.ProductEntity;
import com.code.mallservice.mall.entity.UrlEntity;
import com.code.mallservice.mall.service.IProductService;
import com.code.mallservice.mall.service.IUrlService;
import org.jcodec.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 点击页面跳转
 */
@Controller
public class ClickController {

    @Autowired
    private IUrlService urlService;

    @Autowired
    private IProductService productService;

    @RequestMapping("/{code}")
    public String click(@PathVariable("code")String code, String lang, Model model){
        if(StringUtils.isEmpty(code))return "404";
        UrlEntity urlEntity = urlService.findById(code);
        if(urlEntity == null)return "404";
        model.addAttribute("title",urlEntity.getTitle());
        ProductEntity productEntity = productService.findAllById(urlEntity.getProduct_id());
        if(productEntity == null) return "404";
        model.addAttribute("product",productEntity);
        return productEntity.getTeml_page();
    }
}
