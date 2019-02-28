package com.code.mallservice.mall.entity;

import java.util.List;

/**
 * 赠品信息
 */
public class GiftEntity {
    private int id;
    private String name;
    private int zp_image_id;//主图编号
    private int price;
    private String cgurl;
    private String remark;
    private int user_id;
    private String c_date;

    private ImageEntity imageEntity;//主图

    private UserEntity userEntity;

    private List<GiftTypeEntity> zptypes;//赠品类型

    private List<GiftSizeEntity> zpsizes;//赠品尺码

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCgurl() {
        return cgurl;
    }

    public void setCgurl(String cgurl) {
        this.cgurl = cgurl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getC_date() {
        return c_date;
    }

    public void setC_date(String c_date) {
        this.c_date = c_date;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public int getZp_image_id() {
        return zp_image_id;
    }

    public void setZp_image_id(int zp_image_id) {
        this.zp_image_id = zp_image_id;
    }

    public List<GiftTypeEntity> getZptypes() {
        return zptypes;
    }

    public void setZptypes(List<GiftTypeEntity> zptypes) {
        this.zptypes = zptypes;
    }

    public List<GiftSizeEntity> getZpsizes() {
        return zpsizes;
    }

    public void setZpsizes(List<GiftSizeEntity> zpsizes) {
        this.zpsizes = zpsizes;
    }

    public ImageEntity getImageEntity() {
        return imageEntity;
    }

    public void setImageEntity(ImageEntity imageEntity) {
        this.imageEntity = imageEntity;
    }

    @Override
    public String toString() {
        return "GiftEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", zp_image_id=" + zp_image_id +
                ", price=" + price +
                ", cgurl='" + cgurl + '\'' +
                ", remark='" + remark + '\'' +
                ", user_id=" + user_id +
                ", c_date='" + c_date + '\'' +
                ", userEntity=" + userEntity +
                ", zptypes=" + zptypes +
                ", zpsizes=" + zpsizes +
                '}';
    }
}
