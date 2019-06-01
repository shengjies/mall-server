package com.code.mallservice.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.code.mallservice.mall.entity.OrderEntity;
import com.code.mallservice.mall.entity.ProductEntity;
import com.code.mallservice.mall.entity.TrackingMoreModel;
import com.code.mallservice.mall.entity.WlInfoEntity;
import com.code.mallservice.mall.mapper.OrderMapper;
import com.code.mallservice.mall.mapper.ProductMapper;
import com.code.mallservice.mall.mapper.WLMapper;
import com.code.mallservice.mall.service.IWlService;
import com.code.mallservice.mall.utils.WL;
import okhttp3.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class WlServiceImpl implements IWlService {

    @Autowired
    private WLMapper wlMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductMapper productMapper;



    @Override
    public List<WlInfoEntity> findAllWlInfo(int order_id) {
        return wlMapper.findAllWlInfo(order_id);
    }

    @Override
    public void upload(InputStream inputStream, String fileName,int wl, int sheetindex,
                       int orderindex, int trackindex) {
        final String excel2003L = ".xls"; // 2003- 版本的excel
        final String excel2007U = ".xlsx";// 2007+ 版本的excel
        Workbook wb = null;
        try {
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            if (excel2003L.equals(fileType)) {
                wb = new HSSFWorkbook(inputStream);
            } else if (excel2007U.equals(fileType)) {
                wb = new XSSFWorkbook(inputStream);
            } else {
                throw new Exception("解析文件格式有误");
            }
            if (wb == null) {
                throw new Exception("解析失败");
            }
            String carrier_code ="";
            switch (wl){
                case WL.WL_CS:
                    carrier_code = "qi-eleven";
                    break;
                case WL.WL_HM:
                    carrier_code ="t-cat";
                    break;
                case WL.WL_QJ:
                    carrier_code ="hct";
                    break;
            }
            if(StringUtils.isEmpty(carrier_code))return;
            orderindex = orderindex -1;
            trackindex = trackindex -1;
            Sheet sheet = wb.getSheetAt(sheetindex -1);
            int rowNum = sheet.getLastRowNum()+1;
            Map<String, String> orderMap = new HashMap<>();
            Map<String, String> trackMap = new HashMap<>();
            for(int i = 1;i<rowNum;i++){
                Row row = sheet.getRow(i);
                if(row == null)continue;
                if(row.getCell(orderindex) == null || row.getCell(trackindex) ==null)continue;
                row.getCell(orderindex).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(trackindex).setCellType(Cell.CELL_TYPE_STRING);
                String trackingNum = row.getCell(trackindex).getStringCellValue().trim();
                String orderId = row.getCell(orderindex).getStringCellValue().trim();
                if(orderMap.containsKey(orderId) || trackMap.containsKey(trackingNum)){
                    continue;
                }else{
                    orderMap.put(orderId,trackingNum);
                    trackMap.put(trackingNum,orderId);
                }
                int order_id =0;
                try {
                     order_id = Integer.parseInt(orderId);
                }catch (Exception e){
                    continue;
                }
                if(order_id == 0)continue;
                OrderEntity order = orderMapper.findOrderById(order_id);
                if(order == null) continue;
                wlMapper.editWl(order_id,trackingNum,wl);
                addToTrackingMore(trackingNum,order,carrier_code,"TW");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(wb != null){
                try{
                    wb.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void addToTrackingMore(String tracking_number, OrderEntity order,
                                   String Carrier_code, String Country) {

        //创建单个运单号
        String addApiUrl = "https://api.trackingmore.com/v2/trackings/post";
        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                connectTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        ProductEntity product = productMapper.findById(order.getProduct_id());
        if(product ==null)return;
        TrackingMoreModel trackingMoreModel = new TrackingMoreModel();
        trackingMoreModel.setTracking_number(tracking_number);
        //台湾711： qi-eleven  台湾黑猫：t-cat
        trackingMoreModel.setCarrier_code(Carrier_code);//时丰马来默认走 gdex
        trackingMoreModel.setDestination_code(Country);//这里默认导入MY
        trackingMoreModel.setTitle(product.getName());
        trackingMoreModel.setCustomer_name(order.getUsername());
        trackingMoreModel.setOrder_id(order.getId() + "");
        trackingMoreModel.setOrder_create_time(order.getC_date());

        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , JSON.toJSONString(trackingMoreModel));

        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Trackingmore-Api-Key", "7076d560-f771-4a9d-a872-b501e9679342")
                .url(addApiUrl).post(requestBody)
                .build();

        Response response;
        try {
            response = okHttpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();
            String result = "";
            if (responseBody != null) {
                result = responseBody.string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
