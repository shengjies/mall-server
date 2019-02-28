package com.code.mallservice.mall.entity;

/**
 * 赠品尺码
 */
public class GiftSizeEntity {
    private int id;
    private String zpzlable;
    private String zpzvalue;
    private int gift_id;
    private String c_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZpzlable() {
        return zpzlable;
    }

    public void setZpzlable(String zpzlable) {
        this.zpzlable = zpzlable;
    }

    public String getZpzvalue() {
        return zpzvalue;
    }

    public void setZpzvalue(String zpzvalue) {
        this.zpzvalue = zpzvalue;
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

    @Override
    public String toString() {
        return "GiftSizeEntity{" +
                "id=" + id +
                ", zpzlable='" + zpzlable + '\'' +
                ", zpzvalue='" + zpzvalue + '\'' +
                ", gift_id=" + gift_id +
                ", c_date='" + c_date + '\'' +
                '}';
    }
}
