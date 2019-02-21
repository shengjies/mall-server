package com.code.mallservice.mall.entity;

/**
 * 尺码属性
 */
public class SizesEntity {
    private int id;
    private String size_label;
    private String size_value;
    private int product_id;//产品 赠品编号
    private String c_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize_label() {
        return size_label;
    }

    public void setSize_label(String size_label) {
        this.size_label = size_label;
    }

    public String getSize_value() {
        return size_value;
    }

    public void setSize_value(String size_value) {
        this.size_value = size_value;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getC_date() {
        return c_date;
    }

    public void setC_date(String c_date) {
        this.c_date = c_date;
    }
}
