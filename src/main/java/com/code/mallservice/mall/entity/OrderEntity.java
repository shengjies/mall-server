package com.code.mallservice.mall.entity;

import java.util.List;

/**
 * 订单模块
 */
public class OrderEntity {
    private int id;//从10000开始
    private int product_id;//产品
    private String url_id;//链接编号
    private String priview_url;//产品链接
    private String username;//客户名称
    private String mobile;//手机号码
    private String country;//国家
    private String addr;//详细地址
    private String caddres;//确认地址
    private String ip;//下单ip信息
    private int totla_num;//总数量
    private int money;//金额
    private String postzip;//邮编
    private String cpostzip;//确认邮编
    private String emaill;//emaill
    private String msg;//留言
    private String male;//赠品信息
    private String remark;//备注信息
    private String ps;//派送方式
    private int order_status =0;//订单状态 0、待确认 1、已确认 2、已取消 3、需再次确认
    private int wl_status =0;//物流状态 0、未知 1、已发货 2、派送中 3、Peding 4、拒收 5、已退回 6、签收  7、退回仓库 8、达到门市 9、退货退款 10查询不到
    private int cg_status =0;//采购状态 0、未采购 1、已采购 2、不采购 3、入库
    private int dh_status = 0;//电话是否重复 0、未重复 1、已重复
    private int ip_status =0;//ip是否重复  0、未重复 1、已重复
    private int ck_status =0;//收款状态 0、未收款 1、已收款
    private String up_date;//修改时间
    private String c_date;//下单时间
    private int user_id;
    private String unit;//金额单位
    private String tracking_number;//运单号
    private String wl_uptime;//物流更新时间

    //物流表
    private int wl;//物流
    private String trackingnumber;
    private String u_date;
    private String track_msg;

    private List<OrderAttrEntity> attrs;
    private UserEntity userEntity;
    private ProductEntity productEntity;

    //详情信息
    private String detal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getUrl_id() {
        return url_id;
    }

    public void setUrl_id(String url_id) {
        this.url_id = url_id;
    }

    public String getPriview_url() {
        return priview_url;
    }

    public void setPriview_url(String priview_url) {
        this.priview_url = priview_url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCaddres() {
        return caddres;
    }

    public void setCaddres(String caddres) {
        this.caddres = caddres;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getTotla_num() {
        return totla_num;
    }

    public void setTotla_num(int totla_num) {
        this.totla_num = totla_num;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getPostzip() {
        return postzip;
    }

    public void setPostzip(String postzip) {
        this.postzip = postzip;
    }

    public String getCpostzip() {
        return cpostzip;
    }

    public void setCpostzip(String cpostzip) {
        this.cpostzip = cpostzip;
    }

    public String getEmaill() {
        return emaill;
    }

    public void setEmaill(String emaill) {
        this.emaill = emaill;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public int getWl_status() {
        return wl_status;
    }

    public void setWl_status(int wl_status) {
        this.wl_status = wl_status;
    }

    public int getCg_status() {
        return cg_status;
    }

    public void setCg_status(int cg_status) {
        this.cg_status = cg_status;
    }

    public int getDh_status() {
        return dh_status;
    }

    public void setDh_status(int dh_status) {
        this.dh_status = dh_status;
    }

    public int getIp_status() {
        return ip_status;
    }

    public void setIp_status(int ip_status) {
        this.ip_status = ip_status;
    }

    public String getUp_date() {
        return up_date;
    }

    public void setUp_date(String up_date) {
        this.up_date = up_date;
    }

    public String getC_date() {
        return c_date;
    }

    public void setC_date(String c_date) {
        this.c_date = c_date;
    }

    public String getDetal() {
        return detal;
    }

    public void setDetal(String detal) {
        this.detal = detal;
    }

    public int getCk_status() {
        return ck_status;
    }

    public void setCk_status(int ck_status) {
        this.ck_status = ck_status;
    }

    public List<OrderAttrEntity> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<OrderAttrEntity> attrs) {
        this.attrs = attrs;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTracking_number() {
        return tracking_number;
    }

    public void setTracking_number(String tracking_number) {
        this.tracking_number = tracking_number;
    }

    public String getWl_uptime() {
        return wl_uptime;
    }

    public void setWl_uptime(String wl_uptime) {
        this.wl_uptime = wl_uptime;
    }

    public int getWl() {
        return wl;
    }

    public void setWl(int wl) {
        this.wl = wl;
    }

    public String getTrackingnumber() {
        return trackingnumber;
    }

    public void setTrackingnumber(String trackingnumber) {
        this.trackingnumber = trackingnumber;
    }

    public String getU_date() {
        return u_date;
    }

    public void setU_date(String u_date) {
        this.u_date = u_date;
    }

    public String getTrack_msg() {
        return track_msg;
    }

    public void setTrack_msg(String track_msg) {
        this.track_msg = track_msg;
    }
}
