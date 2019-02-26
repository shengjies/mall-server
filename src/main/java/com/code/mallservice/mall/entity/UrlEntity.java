package com.code.mallservice.mall.entity;

/**
 * 产品链接
 */
public class UrlEntity {
    private String id;
    private String title;
    private String preview_url;
    private String c_date;
    private int product_id;
    private int user_id;
    private String remark;//备注

    private String domaim;//域名 用来接收前端传入的域名拼接链接

    private UserEntity userEntity;

    private ProductEntity productEntity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPreview_url() {
        return preview_url;
    }

    public void setPreview_url(String preview_url) {
        this.preview_url = preview_url;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDomaim() {
        return domaim;
    }

    public void setDomaim(String domaim) {
        this.domaim = domaim;
    }

    @Override
    public String toString() {
        return "UrlEntity{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", preview_url='" + preview_url + '\'' +
                ", c_date='" + c_date + '\'' +
                ", product_id='" + product_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", remark='" + remark + '\'' +
                ", domaim='" + domaim + '\'' +
                ", userEntity=" + userEntity +
                ", productEntity=" + productEntity +
                '}';
    }
}
