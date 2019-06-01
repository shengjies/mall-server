package com.code.mallservice.mall.controller;

import com.code.mallservice.mall.service.IWlService;
import com.code.mallservice.mall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 物流
 */
@RestController
@RequestMapping("/wl")
public class WlController {

    @Autowired
    private IWlService wlService;
    /**
     * 查询物流信息
     * @param order_id
     * @return
     */
    @RequestMapping("/find/info")
    public Result findWlInfo(Integer order_id){
        try {
            if(order_id == null)return Result.error();
            return Result.ok(wlService.findAllWlInfo(order_id));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 物流上传
     * @return
     */
    @RequestMapping("/upload")
    public Result upload(MultipartFile file,Integer wl,Integer sheetindex,
                         Integer orderindex,Integer trackindex){
        try {
            if(wl == null || sheetindex == null ||
                    orderindex == null || trackindex == null){
                return Result.uploadError();
            }
            wlService.upload(file.getInputStream(),file.getOriginalFilename(),wl,
                    sheetindex,orderindex,trackindex);
            return Result.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }
}
