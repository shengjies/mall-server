package com.code.mallservice.mall.entity;

/**
 * 订单属性
 */
public class OrderAttrEntity {
    private int id;
    private String attr_value;//原始类型
    private String attr_size;//原始尺码
    private int num;//原始数量
    private String cattr_value;//确认类型
    private String cattr_size;//确认尺码
    private int cnum;//确认数量
    private int order_id;//订单编号
    private int attr_status =1;// 0、取消 1、确认
    private String up_date;//修改时间
    private String c_date;//添加时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAttr_value() {
        return attr_value;
    }

    public void setAttr_value(String attr_value) {
        this.attr_value = attr_value;
    }

    public String getAttr_size() {
        return attr_size;
    }

    public void setAttr_size(String attr_size) {
        this.attr_size = attr_size;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCattr_value() {
        return cattr_value;
    }

    public void setCattr_value(String cattr_value) {
        this.cattr_value = cattr_value;
    }

    public String getCattr_size() {
        return cattr_size;
    }

    public void setCattr_size(String cattr_size) {
        this.cattr_size = cattr_size;
    }

    public int getCnum() {
        return cnum;
    }

    public void setCnum(int cnum) {
        this.cnum = cnum;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
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

    public int getAttr_status() {
        return attr_status;
    }

    public void setAttr_status(int attr_status) {
        this.attr_status = attr_status;
    }

    @Override
    public String toString() {
        return "OrderAttrEntity{" +
                "id=" + id +
                ", attr_value='" + attr_value + '\'' +
                ", attr_size='" + attr_size + '\'' +
                ", num=" + num +
                ", cattr_value='" + cattr_value + '\'' +
                ", cattr_size='" + cattr_size + '\'' +
                ", cnum=" + cnum +
                ", order_id=" + order_id +
                ", attr_status=" + attr_status +
                ", up_date='" + up_date + '\'' +
                ", c_date='" + c_date + '\'' +
                '}';
    }
}
