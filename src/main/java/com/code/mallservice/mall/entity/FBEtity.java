package com.code.mallservice.mall.entity;

/**
 * fb 信息管理
 */
public class FBEtity {
    private int id;
    private String fb_name;
    private String remark;
    private String c_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFb_name() {
        return fb_name;
    }

    public void setFb_name(String fb_name) {
        this.fb_name = fb_name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getC_date() {
        return c_date;
    }

    public void setC_date(String c_date) {
        this.c_date = c_date;
    }
}
