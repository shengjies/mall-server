package com.code.mallservice.mall.entity;

import java.util.List;

/**
 * 产品
 */
public class ProductEntity {
    private int id;
    private int main_image_id;//产品主图
    private String name;
    private String introduction;//产品简介
    private String description;//产品详情
    private int cl_id;//产品营销策略
    private int price;//产品单价
    private int o_price;//产品原价
    private String country;//国家
    private String unit;//货币单位
    private int sold;//已经出售
    private int comment_num;//评论数量
    private int max_buy_num;//最大购买数量
    private String facebook;
    private boolean comment;//是否显示评论
    private int templ;//模板id
    private String c_date;//添加时间
    private int user_id;//添加者
    private String lang;//语言 如 zh-CN
    private String purchase_url;//采购链接
    private String remark;//备注信息

    private String teml_page;//模板主页
    private String teml_order;//模板订单页面

    //属性操作
    private ImageEntity mainImage;//主图

    private List<Integer> roundImage;//轮播图id;

    private List<ImageEntity> roundImageList;//轮播图

    private UserEntity userEntity;//创建者

    private List<SizesEntity> sizes;//尺码属性

    private List<TypeEntity> types;//类型

    private List<PolicyEntity> policys;//自定义策略

    private List<CommentEntity> commentEntityList;//评论

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMain_image_id() {
        return main_image_id;
    }

    public void setMain_image_id(int main_image_id) {
        this.main_image_id = main_image_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCl_id() {
        return cl_id;
    }

    public void setCl_id(int cl_id) {
        this.cl_id = cl_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getO_price() {
        return o_price;
    }

    public void setO_price(int o_price) {
        this.o_price = o_price;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getComment_num() {
        return comment_num;
    }

    public void setComment_num(int comment_num) {
        this.comment_num = comment_num;
    }

    public int getMax_buy_num() {
        return max_buy_num;
    }

    public void setMax_buy_num(int max_buy_num) {
        this.max_buy_num = max_buy_num;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public boolean isComment() {
        return comment;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    public int getTempl() {
        return templ;
    }

    public void setTempl(int templ) {
        this.templ = templ;
    }

    public String getC_date() {
        return c_date;
    }

    public void setC_date(String c_date) {
        this.c_date = c_date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getPurchase_url() {
        return purchase_url;
    }

    public void setPurchase_url(String purchase_url) {
        this.purchase_url = purchase_url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ImageEntity getMainImage() {
        return mainImage;
    }

    public void setMainImage(ImageEntity mainImage) {
        this.mainImage = mainImage;
    }

    public List<Integer> getRoundImage() {
        return roundImage;
    }

    public void setRoundImage(List<Integer> roundImage) {
        this.roundImage = roundImage;
    }

    public List<ImageEntity> getRoundImageList() {
        return roundImageList;
    }

    public void setRoundImageList(List<ImageEntity> roundImageList) {
        this.roundImageList = roundImageList;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<SizesEntity> getSizes() {
        return sizes;
    }

    public void setSizes(List<SizesEntity> sizes) {
        this.sizes = sizes;
    }

    public List<TypeEntity> getTypes() {
        return types;
    }

    public void setTypes(List<TypeEntity> types) {
        this.types = types;
    }

    public List<PolicyEntity> getPolicys() {
        return policys;
    }

    public void setPolicys(List<PolicyEntity> policys) {
        this.policys = policys;
    }


    public String getTeml_page() {
        return teml_page;
    }

    public void setTeml_page(String teml_page) {
        this.teml_page = teml_page;
    }

    public String getTeml_order() {
        return teml_order;
    }

    public void setTeml_order(String teml_order) {
        this.teml_order = teml_order;
    }

    public List<CommentEntity> getCommentEntityList() {
        return commentEntityList;
    }

    public void setCommentEntityList(List<CommentEntity> commentEntityList) {
        this.commentEntityList = commentEntityList;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", main_image_id=" + main_image_id +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", description='" + description + '\'' +
                ", cl_id=" + cl_id +
                ", price=" + price +
                ", o_price=" + o_price +
                ", country='" + country + '\'' +
                ", unit='" + unit + '\'' +
                ", sold=" + sold +
                ", comment_num=" + comment_num +
                ", max_buy_num=" + max_buy_num +
                ", facebook='" + facebook + '\'' +
                ", comment=" + comment +
                ", templ=" + templ +
                ", c_date='" + c_date + '\'' +
                ", user_id=" + user_id +
                ", lang='" + lang + '\'' +
                ", purchase_url='" + purchase_url + '\'' +
                ", remark='" + remark + '\'' +
                ", mainImage=" + mainImage +
                ", roundImage=" + roundImage +
                ", roundImageList=" + roundImageList +
                ", userEntity=" + userEntity +
                ", sizes=" + sizes +
                ", types=" + types +
                ", policys=" + policys +
                '}';
    }
}
