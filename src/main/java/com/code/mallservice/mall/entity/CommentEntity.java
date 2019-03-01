package com.code.mallservice.mall.entity;

/**
 * 产品评论
 */
public class CommentEntity {
    private int id;
    private String comment_name;
    private String comment_content;
    private int product_id;
    private int img_id;
    private String c_date;

    private ImageEntity imageEntity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment_name() {
        return comment_name;
    }

    public void setComment_name(String comment_name) {
        this.comment_name = comment_name;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
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
        return "CommentEntity{" +
                "id=" + id +
                ", comment_name='" + comment_name + '\'' +
                ", comment_content='" + comment_content + '\'' +
                ", product_id=" + product_id +
                ", img_id=" + img_id +
                ", c_date='" + c_date + '\'' +
                ", imageEntity=" + imageEntity +
                '}';
    }
}
