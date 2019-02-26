package com.code.mallservice.mall.entity;

/**
 * 产品模板信息
 */
public class TemplateEntity {
    private int id;
    private String t_name;
    private String t_value;
    private String t_order;
    private String t_url;
    private String c_date;
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getT_value() {
        return t_value;
    }

    public void setT_value(String t_value) {
        this.t_value = t_value;
    }

    public String getT_order() {
        return t_order;
    }

    public void setT_order(String t_order) {
        this.t_order = t_order;
    }

    public String getT_url() {
        return t_url;
    }

    public void setT_url(String t_url) {
        this.t_url = t_url;
    }

    public String getC_date() {
        return c_date;
    }

    public void setC_date(String c_date) {
        this.c_date = c_date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "TemplateEntity{" +
                "id=" + id +
                ", t_name='" + t_name + '\'' +
                ", t_value='" + t_value + '\'' +
                ", t_order='" + t_order + '\'' +
                ", t_url='" + t_url + '\'' +
                ", c_date='" + c_date + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
