package com.code.mallservice.mall.service;

import com.code.mallservice.mall.entity.WlInfoEntity;

import java.io.InputStream;
import java.util.List;

public interface IWlService {
    /**
     * 查询物流信息
     * @param order_id
     * @return
     */
    List<WlInfoEntity> findAllWlInfo(int order_id);

    /**
     * 物流上传
     * @param inputStream 物流信息
     * @param wl 物流
     * @param sheetindex sheet 下标
     * @param orderindex 订单下标
     * @param trackindex 运单下标
     */
    void upload(InputStream inputStream, String fileName, int wl, int sheetindex,
                int orderindex, int trackindex);
}
