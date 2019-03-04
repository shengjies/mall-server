package com.code.mallservice.mall.controller;

import com.code.mallservice.mall.entity.OrderEntity;
import com.code.mallservice.mall.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 下单界面
 */
@Controller
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @ResponseBody
    @RequestMapping("/save")
    public String save(HttpServletRequest request, OrderEntity entity){
        try {
            entity.setMale(entity.getMale().equals("-undefined")?null:entity.getMale());
            entity.setIp(request.getRemoteAddr());
            orderService.add(entity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }
}
