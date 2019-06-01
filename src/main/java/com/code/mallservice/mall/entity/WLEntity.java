package com.code.mallservice.mall.entity;

public class WLEntity {
    private int id;
    private int order_id;
    private int wl_status;//物流状态
    private int sign;//标记 0、不拉取 1、拉取
    private int wl;//物流公司 1、非凡鸿运物流
    private String tracking_number;//物流单号
    private String u_date;//更新时间
    private String c_date;//创建时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getWl_status() {
        return wl_status;
    }

    public void setWl_status(int wl_status) {
        this.wl_status = wl_status;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public int getWl() {
        return wl;
    }

    public void setWl(int wl) {
        this.wl = wl;
    }

    public String getTracking_number() {
        return tracking_number;
    }

    public void setTracking_number(String tracking_number) {
        this.tracking_number = tracking_number;
    }

    public String getU_date() {
        return u_date;
    }

    public void setU_date(String u_date) {
        this.u_date = u_date;
    }

    public String getC_date() {
        return c_date;
    }

    public void setC_date(String c_date) {
        this.c_date = c_date;
    }
}
