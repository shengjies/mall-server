package com.code.mallservice.mall.entity;

/**
 * 物流信息
 */
public class WlInfoEntity {
    private int id;
    private int wl_id;
    private int order_id;
    private int state;
    private String details;
    private String date;
    private String c_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWl_id() {
        return wl_id;
    }

    public void setWl_id(int wl_id) {
        this.wl_id = wl_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getC_date() {
        return c_date;
    }

    public void setC_date(String c_date) {
        this.c_date = c_date;
    }
}
