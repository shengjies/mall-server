package com.code.mallservice.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.code.mallservice.mall.entity.*;
import com.code.mallservice.mall.mapper.*;
import com.code.mallservice.mall.service.IOrderService;
import com.code.mallservice.mall.utils.ExcelUtil;
import com.code.mallservice.mall.utils.Page;
import com.code.mallservice.mall.utils.TimeUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 订单模块
 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderAttrMapper orderAttrMapper;
    @Autowired
    private UrlMapper urlMapper;

    @Autowired
    private WLMapper wlMapper;

    @Override
    public int add(OrderEntity entity) {
        entity.setMobile(entity.getMobile().trim());
        //查询对应的电话是否存在
        if (orderMapper.findByMobile(entity.getMobile()) != null) {
            entity.setDh_status(1);//重复
        }
        //查询对应的IP是否存在
        if (orderMapper.findByIp(entity.getIp()) != null) {
            entity.setIp_status(1);//重复
        }
        //查询对应的链接
        UrlEntity urlEntity = urlMapper.findById(entity.getUrl_id());
        if (urlEntity != null) {
            entity.setPriview_url(urlEntity.getPreview_url());
            entity.setUser_id(urlEntity.getUser_id());
        }
        orderMapper.add(entity);//保存订单信息
        if (!StringUtils.isEmpty(entity.getDetal())) {
            //保存订单属性
            List<OrderAttrEntity> attrEntities = JSON.parseArray(entity.getDetal(), OrderAttrEntity.class);
            if (attrEntities != null) {
                for (OrderAttrEntity attrEntity : attrEntities) {
                    attrEntity.setOrder_id(entity.getId());
                    orderAttrMapper.add(attrEntity);
                }
            }
        }
        return 0;
    }

    /**
     * 按照编号查询
     *
     * @param id
     * @return
     */
    @Override
    public OrderEntity findById(int id) {
        return orderMapper.findById(id);
    }

    /**
     * 分页查询
     *
     * @param id           订单编号
     * @param product_id   产品编号
     * @param username     客户名称
     * @param order_status 订单状态
     * @param bTime        开始时间
     * @param eTime        结束时间
     * @param cg_status    采购状态
     * @param wl_status    物流状态
     * @param mobile       手机号码
     * @param country      国家
     * @param page         页数大小
     * @param size         页面大小
     * @return
     */
    @Override
    public Page<OrderEntity> findPage(int id, int product_id, String username,
                                      int order_status, String bTime,
                                      String eTime, int cg_status,
                                      int wl_status, String mobile,
                                      String country, int page, int size,
                                      String user_id,int ck_status) {
        long count = orderMapper.countFind(id, product_id, username,
                order_status, bTime + " 00:00:00", eTime + " 23:59:59", cg_status, wl_status,
                mobile, country, user_id,ck_status);
        List<OrderEntity> list = orderMapper.findPage(id, product_id, username,
                order_status, bTime + " 00:00:00",
                eTime + " 23:59:59", cg_status, wl_status, mobile,
                country, page * size, size, user_id,ck_status);

        return new Page<>(list, count);
    }

    /**
     * 修改订单
     *
     * @param id
     * @param is_edit
     * @param postzip
     * @param email
     * @param detal
     * @param remark
     * @throws Exception
     */
    @Override
    public void editOrder(int id, boolean is_edit, String postzip,
                          String email, String detal, String remark, int order_status)
            throws Exception {
        //修改订单基本信息
        orderMapper.editOrderInfo(id, postzip, order_status, email, remark);
        if (order_status == 1) {
            if (!StringUtils.isEmpty(detal) && is_edit) {
                //修改订单详情
                orderAttrMapper.editOrderAttrStatus(id);
                List<OrderAttrEntity> list = JSON.parseArray(detal, OrderAttrEntity.class);
                if (list == null) return;
                for (OrderAttrEntity entity : list) {
                    OrderAttrEntity attr = orderAttrMapper.findByAttrTypeAndSize(entity.getCattr_value(),
                            entity.getCattr_size(), id);
                    if (attr != null) {
                        //修改信息
                        attr.setCnum(entity.getCnum());
                        attr.setCattr_value(entity.getCattr_value());
                        attr.setCattr_size(entity.getCattr_size());
                        orderAttrMapper.editOrderAttr(attr);
                        continue;
                    }
                    OrderAttrEntity attr1 = orderAttrMapper.findByCAttrTypeAndSize(entity.getCattr_value(),entity.getCattr_size(),id);
                    if(attr1 != null){
                        attr1.setCnum(entity.getCnum());
                        orderAttrMapper.editOrderAttr(attr1);
                        continue;
                    }
                    //添加属性信息
                    entity.setOrder_id(id);
                    orderAttrMapper.add(entity);
                }

            } else {
                //直接确认订单详情
                List<OrderAttrEntity> attrEntities = orderAttrMapper.findAttrByOrderId(id);
                orderAttrMapper.editOrderAttrStatus(id);
                if (attrEntities == null) return;
                for (OrderAttrEntity entity : attrEntities) {
                    entity.setCattr_size(entity.getAttr_size());
                    entity.setCattr_value(entity.getAttr_value());
                    entity.setCnum(entity.getNum());
                    orderAttrMapper.editOrderAttr(entity);
                }
            }
        }
    }

    @Override
    public Map<String, String> actionsOrderStatus(int edit_status, int estatus, String edit_order) {
        Map<String,String> map = new HashMap<>();
        int successNum =0;//成功个数
        int total =0;//修改总数
        List<String> excutList = new ArrayList<>();
        List<String> failList = new ArrayList<>();
        String[] orders = edit_order.split("\\n");
        for (String order : orders) {
           try{
               if(StringUtils.isEmpty(order.trim()))continue;
               int order_id = Integer.parseInt(order.trim());
               OrderEntity entity = orderMapper.findOrderById(order_id);
               if(entity != null){
                   if(edit_status == 1){//采购状态
                       if(entity.getCg_status() == estatus){
                           //状态已存在
                           excutList.add(order.trim());
                       }
                   }else if(edit_status == 2){//收款状态
                       if(entity.getCk_status() == estatus){
                           //状态已存在
                           excutList.add(order.trim());
                       }
                   }
               }
           }catch (Exception e){

           }
        }
        //状态不存在则修改数据
        if(excutList.size()<=0){
            for (String order : orders) {
                try {
                    if(StringUtils.isEmpty(order.trim()))continue;
                    total++;
                    int order_id = Integer.parseInt(order.trim());
                    if(edit_status == 1){//采购状态
                        orderMapper.editCgStatus(order_id,estatus);
                        successNum++;
                        if(estatus == 4){//出库，向物流数据表中添加一条数据
                           WLEntity wlEntity = wlMapper.findByOrderId(order_id);
                           if(wlEntity == null){
                               WLEntity entity = new WLEntity();
                               entity.setOrder_id(order_id);
                               wlMapper.add(entity);
                           }
                        }
                    }else if(edit_status == 2){//收款状态
                        orderMapper.editSkStatus(order_id,estatus);
                        successNum++;
                    }
                }catch (Exception e){
                    failList.add(order.trim());
                }
            }
            map.put("msg","总数:"+total+",成功:"+successNum+"失败:"+failList.size()+JSON.toJSONString(failList));
        }else{
            map.put("exc","状态已经存在："+JSON.toJSONString(excutList));
        }
        return map;
    }


    @Override
    public void exportBatchExcel(HttpServletResponse response, int id, int product_id, String username, int order_status, String bTime, String eTime, int cg_status, int wl_status, int ck_status, String user_id, String mobile, Integer sign) {
        try {
            List<OrderEntity> list = orderMapper.findExcel(id,product_id,username,
                    order_status,bTime +" 00:00:00",
                    eTime+" 23:59:59",cg_status,wl_status,mobile,"",user_id,ck_status);
            excel(response, list,bTime,eTime,sign);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 勾选导出
     * @param response
     * @param sign
     * @param checkedTest
     * @throws Exception
     */
    @Override
    public void checkReport(HttpServletResponse response, int sign, String checkedTest) throws Exception {
        List<OrderEntity> list = new ArrayList<>();
        String[] ids = checkedTest.split(",");
        for (String id : ids) {
            try {
                if(StringUtils.isEmpty(id.trim()))continue;
                int order_id = Integer.parseInt(id.trim());
                OrderEntity entity = orderMapper.findById(order_id);
                if(entity != null)
                    list.add(entity);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        excel(response,list, TimeUtils.getCurrentDateTime(),TimeUtils.getCurrentDateTime(),sign);
    }

    /**
     * excel导出
     * @param response
     * @param list 导出数据
     * @param btime 开始好司机
     * @param etime 结束时间
     * @param sign 是否需要导出详情
     * @throws Exception
     */
    public void excel(HttpServletResponse response, List<OrderEntity> list, String btime, String etime, int sign) throws Exception{
        //标题
        String[] title = {"订单编号","业务员", "链接编号", "下单产品", "客户名称", "手机号码", "数量", "支付金额",
                "Email", "详细地址", "邮编",  "订单状态",  "IP",  "客户留言", "备注信息",
                 "赠品信息", "下单时间", "IP 重复", "电话重复", "物态", "采购状态", "收款状态",  "状态修改时间",
                "配送方式", "属性详情"};
        //创建workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个sheet
        HSSFSheet sheet = workbook.createSheet();
        setColumnWidth(sheet, 25 * 260, title.length - 2);
        //创建标题行
        HSSFRow titleRow = sheet.createRow(0);
        //创建样式
        HSSFCellStyle style = style(workbook, HSSFCellStyle.ALIGN_CENTER, (short) 1, (short) 1, (short) 1, (short) 1);
        for (int i = 0; i < title.length; i++) {
            HSSFCell cell = titleRow.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 25, 30));
        if(list != null && list.size()>0){
            int r =1;
            for (OrderEntity entity : list) {
                HSSFRow content = sheet.createRow(r);
                if (entity.getAttrs() != null && entity.getAttrs().size() > 0 && sign == 1) {
                    for (int k = 0; k <= 23; k++) {
                        sheet.addMergedRegion(new CellRangeAddress(r, r + entity.getAttrs().size(), k, k));
                    }
                }
                //编号
                HSSFCell idCell = content.createCell(0);
                idCell.setCellValue(entity.getId());
                idCell.setCellStyle(style);
                //业务员
                HSSFCell ywy = content.createCell(1);
                ywy.setCellValue(entity.getUserEntity() != null?entity.getUserEntity().getUsername():"");
                ywy.setCellStyle(style);
                //链接编号
                HSSFCell offer = content.createCell(2);
                offer.setCellValue(entity.getUrl_id());
                offer.setCellStyle(style);
                //下单产品
                HSSFCell product = content.createCell(3);
                product.setCellValue(entity.getProductEntity() != null?entity.getProductEntity().getName():"");
                product.setCellStyle(style);
                //客户名称
                HSSFCell name = content.createCell(4);
                name.setCellValue(entity.getUsername());
                name.setCellStyle(style);
                //手机号码
                HSSFCell mobile = content.createCell(5);
                mobile.setCellValue(entity.getMobile());
                mobile.setCellStyle(style);
                //数量
                HSSFCell num = content.createCell(6);
                num.setCellValue(entity.getTotla_num());
                num.setCellStyle(style);
                //支付金额
                HSSFCell money = content.createCell(7);
                money.setCellValue(entity.getMoney());
                money.setCellStyle(style);
                //Email
                HSSFCell emaill = content.createCell(8);
                emaill.setCellValue(entity.getEmaill());
                emaill.setCellStyle(style);
                //详细地址
                HSSFCell addr = content.createCell(9);
                addr.setCellValue(entity.getAddr());
                addr.setCellStyle(style);
                //邮编
                HSSFCell postzip = content.createCell(10);
                postzip.setCellValue(StringUtils.isEmpty(entity.getCpostzip())?entity.getPostzip():entity.getCpostzip());
                postzip.setCellStyle(style);
                //订单状态
                String orderStatus ="待确认";
                switch (entity.getOrder_status()){
                    case 0:
                        orderStatus ="待确认";
                        break;
                    case 1:
                        orderStatus ="已确认";
                        break;
                    case 2:
                        orderStatus ="已取消";
                        break;
                    case 3:
                        orderStatus = "需再次确认";
                        break;
                      default:
                          orderStatus ="待确认";
                          break;
                }
                HSSFCell order_status = content.createCell(11);
                order_status.setCellValue(orderStatus);
                order_status.setCellStyle(style);
                //ip
                HSSFCell ip = content.createCell(12);
                ip.setCellValue(entity.getIp());
                ip.setCellStyle(style);
                //客户留言
                HSSFCell msg = content.createCell(13);
                msg.setCellValue(entity.getMsg());
                msg.setCellStyle(style);
                //备注信息
                HSSFCell remark = content.createCell(14);
                remark.setCellValue(entity.getRemark());
                remark.setCellStyle(style);
                //赠品信息
                HSSFCell male = content.createCell(15);
                male.setCellValue(entity.getMale());
                male.setCellStyle(style);
                //下单时间
                HSSFCell cDate = content.createCell(16);
                cDate.setCellValue(entity.getC_date());
                cDate.setCellStyle(style);
                //IP 重复
                String ipCf = "";
                if(entity.getIp_status() == 1){
                    ipCf ="IP 重复";
                }
                HSSFCell ipCf1 = content.createCell(17);
                ipCf1.setCellValue(ipCf);
                ipCf1.setCellStyle(style);
                //电话重复
                String mobileCf ="";
                if(entity.getDh_status() == 1) mobileCf="电话重复";
                HSSFCell mobileCell = content.createCell(18);
                mobileCell.setCellValue(mobileCf);
                mobileCell.setCellStyle(style);
                //物态
                String wt ="未知";
                switch (entity.getWl_status()){
                    case 0:
                        wt ="未知";
                        break;
                    case 1:
                        wt ="已发货";
                        break;
                    case 2:
                        wt ="派送中";
                        break;
                    case 3:
                        wt ="Peding";
                        break;
                    case 4:
                        wt ="拒收";
                        break;
                    case 5:
                        wt ="已退回";
                        break;
                    case 6:
                        wt ="签收";
                        break;
                    case 7:
                        wt ="退回仓库";
                        break;
                    case 8:
                        wt ="达到门市";
                        break;
                    case 9:
                        wt ="退货退款";
                        break;
                    case 10:
                        wt ="查询不到";
                        break;
                        default:
                            wt ="未知";
                            break;
                }
                HSSFCell wt_status =content.createCell(19);
                wt_status.setCellValue(wt);
                wt_status.setCellStyle(style);
                //采购状态
                String cg ="未采购";
                switch (entity.getCg_status()){
                    case 0:
                        cg ="未采购";
                        break;
                    case 1:
                        cg ="已采购";
                        break;
                    case 2:
                        cg ="不采购";
                        break;
                    case 3:
                        cg ="入库";
                        break;
                        default:
                            cg ="未采购";
                            break;
                }
                HSSFCell cg_status =content.createCell(20);
                cg_status.setCellValue(cg);
                cg_status.setCellStyle(style);
                //收款状态
                String sk ="未收款";
                if(entity.getCk_status() == 1)sk ="已收款";
                HSSFCell sk_status = content.createCell(21);
                sk_status.setCellValue(sk);
                sk_status.setCellStyle(style);
                //状态修改时间
                HSSFCell uDate = content.createCell(22);
                uDate.setCellValue(entity.getUp_date());
                uDate.setCellStyle(style);
                //配送方式
                String ps ="货到付款";
                switch (entity.getPs()){
                    case "roc_qj":
                        ps ="全家";
                        break;
                    case "roc_711":
                        ps ="711";
                        break;
                        default:
                            ps ="货到付款";
                            break;
                }
                HSSFCell psCell = content.createCell(23);
                psCell.setCellValue(ps);
                psCell.setCellStyle(style);
//                //属性详情
//                HSSFCell detal = content.createCell(24);
//                detal.setCellValue(JSON.toJSONString(entity.getAttrs()));
//                detal.setCellStyle(style);
                //属性详情
                if (entity.getAttrs() != null && entity.getAttrs().size() > 0 && sign == 1) {
                    //类型
                    HSSFCell typeCell = content.createCell(24);
                    typeCell.setCellValue("类型");
                    typeCell.setCellStyle(style);
                    //尺码
                    HSSFCell sizeCell = content.createCell(25);
                    sizeCell.setCellValue("尺码");
                    sizeCell.setCellStyle(style);
                    //数量
                    HSSFCell num1Cell = content.createCell(26);
                    num1Cell.setCellValue("数量");
                    num1Cell.setCellStyle(style);

                    //确认属性
                    HSSFCell confirmTypeCell = content.createCell(27);
                    confirmTypeCell.setCellValue("确认类型");
                    confirmTypeCell.setCellStyle(style);

                    //确认尺码
                    HSSFCell confirmSizeCell = content.createCell(28);
                    confirmSizeCell.setCellValue("确认尺码");
                    confirmSizeCell.setCellStyle(style);
                    //确认数量
                    HSSFCell confirmNumCell = content.createCell(29);
                    confirmNumCell.setCellValue("确认数量");
                    confirmNumCell.setCellStyle(style);
                    for (int x = 1; x <= entity.getAttrs().size(); x++) {
                        content = sheet.createRow(r + x);
                        //类型
                        HSSFCell typeCell1 = content.createCell(24);
                        typeCell1.setCellValue(entity.getAttrs().get(x - 1).getAttr_value());
                        typeCell1.setCellStyle(style);
                        //尺码
                        HSSFCell sizeCell1 = content.createCell(25);
                        sizeCell1.setCellValue(entity.getAttrs().get(x - 1).getAttr_size());
                        sizeCell1.setCellStyle(style);
                        //数量
                        HSSFCell num1Cell1 = content.createCell(26);
                        num1Cell1.setCellValue(entity.getAttrs().get(x - 1).getNum());
                        num1Cell1.setCellStyle(style);

                        //确认属性
                        HSSFCell confirmTypeCell1 = content.createCell(27);
                        confirmTypeCell1.setCellValue(entity.getAttrs().get(x - 1).getCattr_value());
                        confirmTypeCell1.setCellStyle(style);

                        //确认尺码
                        HSSFCell confirmSizeCell1 = content.createCell(28);
                        confirmSizeCell1.setCellValue(entity.getAttrs().get(x - 1).getCattr_size());
                        confirmSizeCell1.setCellStyle(style);
                        //确认数量
                        HSSFCell confirmNumCell1 = content.createCell(29);
                        confirmNumCell1.setCellValue(entity.getAttrs().get(x - 1).getCnum()==0?"":entity.getAttrs().get(x - 1).getCnum()+"");
                        confirmNumCell1.setCellStyle(style);
                    }
                    r = r + entity.getAttrs().size();
                }
                r++;
            }
        }
        String fileName = btime.substring(0, 10) + "~" + etime.substring(0, 10);
        ExcelUtil.writeExcel(response, workbook, fileName);
    }

    /**
     * 设置单元格的宽度
     *
     * @param sheet
     * @param width
     * @param count
     */
    public static void setColumnWidth(HSSFSheet sheet, int width, int count) {
        for (int i = 1; i <= count; i++) {
            sheet.setColumnWidth(i, width);
        }
    }
    /**
     * 获取单元格样式
     *
     * @param workbook
     * @param algin
     * @param btop
     * @param bbtm
     * @param bleft
     * @param bright
     * @return
     */
    public static HSSFCellStyle style(HSSFWorkbook workbook, Short algin, short btop, short bbtm, short bleft, short bright) {
        HSSFCellStyle style = workbook.createCellStyle();
        if (algin != null) {
            style.setAlignment(algin);

        }
        style.setWrapText(true);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setBorderTop(btop);
        style.setBorderBottom(bbtm);
        style.setBorderLeft(bleft);
        style.setBorderRight(bright);
        return style;
    }


    @Override
    public int del(int id) {
        orderAttrMapper.delAttr(id);
        return orderMapper.del(id);
    }

    @Override
    public List<OrderEntity> findMobileOrIpList(String mobile, String ip) {
        return orderMapper.findMobileOrIpList(mobile,ip);
    }
}
