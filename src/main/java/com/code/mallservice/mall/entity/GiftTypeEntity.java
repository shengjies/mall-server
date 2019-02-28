package com.code.mallservice.mall.entity;

/**
 * 赠品类型
 */
public class GiftTypeEntity {
    private int id;
    private String zptlable;
    private String zptvalue;
    private int zptimg;
    private int gift_id;
    private String c_date;

    private ImageEntity imageEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZptlable() {
        return zptlable;
    }

    public void setZptlable(String zptlable) {
        this.zptlable = zptlable;
    }

    public String getZptvalue() {
        return zptvalue;
    }

    public void setZptvalue(String zptvalue) {
        this.zptvalue = zptvalue;
    }

    public int getZptimg() {
        return zptimg;
    }

    public void setZptimg(int zptimg) {
        this.zptimg = zptimg;
    }

    public int getGift_id() {
        return gift_id;
    }

    public void setGift_id(int gift_id) {
        this.gift_id = gift_id;
    }

    public String getC_date() {
        return c_date;
    }

    public void setC_date(String c_date) {
        this.c_date = c_date;
    }

    public ImageEntity getImageEntity() {
        return imageEntity;
    }

    public void setImageEntity(ImageEntity imageEntity) {
        this.imageEntity = imageEntity;
    }

    @Override
    public String toString() {
        return "GiftTypeEntity{" +
                "id=" + id +
                ", zptlable='" + zptlable + '\'' +
                ", zptvalue='" + zptvalue + '\'' +
                ", zptimg=" + zptimg +
                ", gift_id=" + gift_id +
                ", c_date='" + c_date + '\'' +
                ", imageEntity=" + imageEntity +
                '}';
    }
}
