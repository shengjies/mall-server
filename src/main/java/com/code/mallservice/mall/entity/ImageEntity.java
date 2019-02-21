package com.code.mallservice.mall.entity;

/**
 * 图片素材管理
 */
public class ImageEntity {
    private int uid;
    private String url;
    private String min_url;
    private String c_date;
    private int product_id;//产品编号

    public int getId() {
        return uid;
    }

    public void setId(int id) {
        this.uid = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMin_url() {
        return min_url;
    }

    public void setMin_url(String min_url) {
        this.min_url = min_url;
    }

    public String getC_date() {
        return c_date;
    }

    public void setC_date(String c_date) {
        this.c_date = c_date;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
