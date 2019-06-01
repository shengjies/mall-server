package com.code.mallservice.mall.controller;

import com.alibaba.fastjson.JSON;
import com.code.mallservice.mall.entity.OrderEntity;
import com.code.mallservice.mall.entity.UserEntity;
import com.code.mallservice.mall.service.IOrderService;
import com.code.mallservice.mall.service.IUserService;
import com.code.mallservice.mall.utils.AuthorUtils;
import com.code.mallservice.mall.utils.JwtUtils;
import com.code.mallservice.mall.utils.Result;
import com.code.mallservice.mall.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 下单界面
 */
@Controller
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, OrderEntity entity) {
        try {
            entity.setIp(request.getRemoteAddr());
            entity.setWl_status(-1);//未知
            orderService.add(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> mvMap = new HashMap<>();
        mvMap.put("pay", Math.round(entity.getMoney()));//取整
        mvMap.put("orderId", entity.getId());
        mvMap.put("currencyUnit", entity.getUnit());

        return new ModelAndView(new RedirectView("/order/"), mvMap);
    }

    @RequestMapping("/order/")
    public String toOrder(Model model, String pay, String orderId, String currencyUnit, String lang) {
        model.addAttribute("unit", currencyUnit);
        model.addAttribute("pay", pay);
        model.addAttribute("orderId", orderId);
        return "success";
    }

    /**
     * 分页查询
     *
     * @param id
     * @param product_id
     * @param username
     * @param order_status
     * @param bTime
     * @param eTime
     * @param cg_status
     * @param wl_status
     * @param mobile
     * @param country
     * @param page
     * @param size
     * @return
     */
    @ResponseBody
    @RequestMapping("/order/find")
    public Result findPage(HttpServletRequest request,
                           @RequestParam(name = "id", defaultValue = "-1") Integer id,
                           @RequestParam(name = "product_id", defaultValue = "-1") Integer product_id,
                           String username,
                           @RequestParam(name = "order_status", defaultValue = "-1") Integer order_status,
                           String bTime, String eTime,
                           @RequestParam(name = "cg_status", defaultValue = "-1") Integer cg_status,
                           @RequestParam(name = "wl_status", defaultValue = "-1") Integer wl_status,
                           @RequestParam(name = "ck_status", defaultValue = "-1") Integer ck_status,
                           @RequestParam(name = "user_id", defaultValue = "") String user_id,
                           String mobile, String country,
                           @RequestParam(name = "page", defaultValue = "0") Integer page,
                           @RequestParam(name = "size", defaultValue = "50") Integer size) {
        try {
            String token =request.getHeader("token");
            UserEntity userEntity = JwtUtils.getUserByToken(token);
            if(userEntity ==null) return Result.error();
            bTime = StringUtils.isEmpty(bTime) ? TimeUtils.getCurrentDate() : bTime;
            eTime = StringUtils.isEmpty(eTime) ? TimeUtils.getCurrentDate() : eTime;
            //判断是否是组长
            if(StringUtils.isEmpty(user_id) && AuthorUtils.userRole(userEntity) == 1){
                List<String> ids = userService.groupId(userEntity.getId());
                String item = userEntity.getId()+"";
                if(ids.size()>0){
                    item+=","+JSON.toJSONString(ids);
                }
                user_id =item.replaceAll("\"","").replace("[","").replace("]","");
            }else if(StringUtils.isEmpty(user_id) && AuthorUtils.userRole(userEntity) == 2){
                user_id=userEntity.getId()+"";
            }
            return Result.ok(orderService.findPage(id, product_id, username, order_status, bTime, eTime
                    , cg_status, wl_status, mobile, country, page, size,user_id,ck_status));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 编辑订单信息
     * @param id 订单编号
     * @param is_edit
     * @param postzip
     * @param email
     * @param detal
     * @param remark
     * @return
     */
    @ResponseBody
    @RequestMapping("/order/edit")
    public Result editOrder(Integer id, boolean is_edit, String postzip, String email,
                            String detal, String remark,Integer order_status) {
        try {
            if (id == null) return Result.error();
            orderService.editOrder(id,is_edit,postzip,email,detal,remark,order_status);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 批量修改订单状态
     * @param edit_status 需要修改的状态 1、采购状态 2、收款状态
     * @param estatus 各个状态的值
     * @param edit_order 订单编号
     * @return
     */
    @ResponseBody
    @RequestMapping("/order/actions")
    public Result actionsOrderStatus(Integer edit_status,Integer estatus,String edit_order){
        try {
            return Result.ok(orderService.actionsOrderStatus(edit_status,estatus,edit_order));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 条件导出
     * @param response
     * @param id 编号
     * @param product_id 产品
     * @param username 客户名称
     * @param order_status 订单状态
     * @param bTime 开始时间
     * @param eTime 结束时间
     * @param cg_status 采购状态
     * @param wl_status 物流状态
     * @param ck_status 收款状态
     * @param user_id 用户
     * @param mobile 手机
     * @param sign 标记是否需要导出详情
     */
    @ResponseBody
    @RequestMapping("/order/condition/report")
    public void exportBatchExcel(HttpServletResponse response,HttpServletRequest request,
                                 @RequestParam(name = "id", defaultValue = "-1") Integer id,
                                 @RequestParam(name = "product_id", defaultValue = "-1") Integer product_id,
                                 String username,
                                 @RequestParam(name = "order_status", defaultValue = "-1") Integer order_status,
                                 String bTime, String eTime,
                                 @RequestParam(name = "cg_status", defaultValue = "-1") Integer cg_status,
                                 @RequestParam(name = "wl_status", defaultValue = "-1") Integer wl_status,
                                 @RequestParam(name = "ck_status", defaultValue = "-1") Integer ck_status,
                                 @RequestParam(name = "user_id", defaultValue = "") String user_id,
                                 String mobile,
                                 @RequestParam(name = "sign", defaultValue = "0")Integer sign) {
        try {
            String token =request.getParameter("token");
            UserEntity userEntity = JwtUtils.getUserByToken(token);
            if(userEntity ==null) return ;
            bTime = StringUtils.isEmpty(bTime) ? TimeUtils.getCurrentDate() : bTime;
            eTime = StringUtils.isEmpty(eTime) ? TimeUtils.getCurrentDate() : eTime;
            //判断是否是组长
            if(StringUtils.isEmpty(user_id) && AuthorUtils.userRole(userEntity) == 1){
                List<String> ids = userService.groupId(userEntity.getId());
                String item = userEntity.getId()+"";
                if(ids.size()>0){
                    item+=","+JSON.toJSONString(ids);
                }
                user_id =item.replaceAll("\"","").replace("[","").replace("]","");
            }else if(StringUtils.isEmpty(user_id) && AuthorUtils.userRole(userEntity) == 2){
                user_id=userEntity.getId()+"";
            }
            orderService.exportBatchExcel(response,id,product_id,username,order_status,bTime,eTime,
                    cg_status,wl_status,ck_status,user_id,mobile,sign);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 勾选导出
     * @param response
     * @param sign
     * @param checkedTest
     */
    @ResponseBody
    @RequestMapping("/order/check/report")
    public void checkReport(HttpServletResponse response,
                            @RequestParam(name = "sign", defaultValue = "0")Integer sign,
                            String checkedTest){
        try {
            orderService.checkReport(response,sign,checkedTest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 删除订单
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/order/del")
    public Result del(@RequestParam(name = "id",defaultValue = "-1") Integer id){
        try {
            if(id ==-1)return Result.error();
            return  Result.ok(orderService.del(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 查询电话或者ip重复
     * @param mobile
     * @param ip
     * @return
     */
    @ResponseBody
    @RequestMapping("/find/morip")
    public Result findMobileOrIpList(String mobile, String ip){
        try {
            if(StringUtils.isEmpty(mobile) && StringUtils.isEmpty(ip)){
                return Result.error();
            }
            return Result.ok(orderService.findMobileOrIpList(mobile,ip));
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error();
    }
}
