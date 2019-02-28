package com.code.mallservice.mall.entity;

/**
 * 策略相关赠品
 */
public class PolicyGirEntity {
    private int id;
    private int zpnum;
    private int zpname;//赠品编号
    private int policy_id; //策略编号
    private String c_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getZpnum() {
        return zpnum;
    }

    public void setZpnum(int zpnum) {
        this.zpnum = zpnum;
    }

    public int getZpname() {
        return zpname;
    }

    public void setZpname(int zpname) {
        this.zpname = zpname;
    }

    public int getPolicy_id() {
        return policy_id;
    }

    public void setPolicy_id(int policy_id) {
        this.policy_id = policy_id;
    }

    public String getC_date() {
        return c_date;
    }

    public void setC_date(String c_date) {
        this.c_date = c_date;
    }


    @Override
    public String toString() {
        return "PolicyGirEntity{" +
                "id=" + id +
                ", zpnum=" + zpnum +
                ", zpname=" + zpname +
                ", policy_id=" + policy_id +
                ", c_date='" + c_date + '\'' +
                '}';
    }
}
