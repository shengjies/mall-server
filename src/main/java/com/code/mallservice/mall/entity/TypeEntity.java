package com.code.mallservice.mall.entity;

/**
 * 产品类型
 */
public class TypeEntity {
    private int id;
    private String type_lable;
    private String type_value;
    private int img_id;
    private int product_id;

    private ImageEntity imageEntity;//图片
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType_lable() {
        return type_lable;
    }

    public void setType_lable(String type_lable) {
        this.type_lable = type_lable;
    }

    public String getType_value() {
        return type_value;
    }

    public void setType_value(String type_value) {
        this.type_value = type_value;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public ImageEntity getImageEntity() {
        return imageEntity;
    }

    public void setImageEntity(ImageEntity imageEntity) {
        this.imageEntity = imageEntity;
    }

    @Override
    public String toString() {
        return "TypeEntity{" +
                "id=" + id +
                ", type_lable='" + type_lable + '\'' +
                ", type_value='" + type_value + '\'' +
                ", img_id=" + img_id +
                ", product_id=" + product_id +
                ", imageEntity=" + imageEntity +
                '}';
    }
}
