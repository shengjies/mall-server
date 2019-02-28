package com.code.mallservice.mall.entity;

import java.util.List;

/**
 * 自定义策略
 */
public class PolicyEntity {
    private int id;
    private String tcname;//策略名称
    private int tcnum;//策略数量
    private int tcmoney;//策略金额
    private boolean tcattr;//策略属性
    private int product_id;
    private String c_date;

    private List<PolicyGirEntity> gir;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTcname() {
        return tcname;
    }

    public void setTcname(String tcname) {
        this.tcname = tcname;
    }

    public int getTcnum() {
        return tcnum;
    }

    public void setTcnum(int tcnum) {
        this.tcnum = tcnum;
    }

    public int getTcmoney() {
        return tcmoney;
    }

    public void setTcmoney(int tcmoney) {
        this.tcmoney = tcmoney;
    }

    public boolean isTcattr() {
        return tcattr;
    }

    public void setTcattr(boolean tcattr) {
        this.tcattr = tcattr;
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

    public List<PolicyGirEntity> getGir() {
        return gir;
    }

    public void setGir(List<PolicyGirEntity> gir) {
        this.gir = gir;
    }


    @Override
    public String toString() {
        return "PolicyEntity{" +
                "id=" + id +
                ", tcname='" + tcname + '\'' +
                ", tcnum=" + tcnum +
                ", tcmoney=" + tcmoney +
                ", tcattr=" + tcattr +
                ", product_id=" + product_id +
                ", c_date='" + c_date + '\'' +
                ", gir=" + gir +
                '}';
    }
}
