package com.code.mallservice.mall.utils;


import okhttp3.FormBody;
import okhttp3.RequestBody;

public class CloakRequest {

    /**
     * 必须
     * 用户名（正常用户名为手机号或QQ号）
     */
    private String username = "2857470439";

    /**
     * 必须
     * 用户密码（16位大小写字母和数字的组合)
     */
    private String password = "u1cW2rkGMqfE7ZiG";

    /**
     * 必须
     * 提交判断的IP地址（支持ipv4和ipv6)
     */
    private String ip;

    /**
     * 必须
     * getHost
     * 调用接口的网站
     */
    private String site;

    /**
     * 当前访问设备（手机：phone, 平板：tablet, 电脑：computer， ''： 表示不传递设备值），默认为空
     */
    private String device = "";

    /**
     * 打广告的国家, 一定要填写2位国家代码，多个国家之间用+号连接，国家代码务必参见 国家代码
     * 'US+GB+CA'
     */
    private String ads_countries;

    /**
     * 'computer+tablet'
     * 屏蔽设备，参数值：phone(手机)，tablet(平板)，computer(电脑)，默认为空表示不屏蔽任何设备
     */
    private String ban_devices = "";

    /**
     * true：屏蔽代理; false: 允许代理IP访问, 默认屏蔽
     */
    private boolean ban_agent = true;

    /**
     * true: 屏蔽公司IP; false: 允许公司IP访问, 默认屏蔽
     */
    private boolean ban_company = true;

    /**
     * true: 爬虫IP; false: 正常IP, 默认false
     */
    private boolean is_crawler = false;


    public RequestBody toRequestBody(String ip, String host, String country) {

        return new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .add("ip", ip)
                .add("site", host)
                .add("device",device)
                .add("ban_devices",ban_devices)
                .add("ads_countries", country)
                .build();

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getAds_countries() {
        return ads_countries;
    }

    public void setAds_countries(String ads_countries) {
        this.ads_countries = ads_countries;
    }

    public String getBan_devices() {
        return ban_devices;
    }

    public void setBan_devices(String ban_devices) {
        this.ban_devices = ban_devices;
    }

    public boolean isBan_agent() {
        return ban_agent;
    }

    public void setBan_agent(boolean ban_agent) {
        this.ban_agent = ban_agent;
    }

    public boolean isBan_company() {
        return ban_company;
    }

    public void setBan_company(boolean ban_company) {
        this.ban_company = ban_company;
    }

    public boolean isIs_crawler() {
        return is_crawler;
    }

    public void setIs_crawler(boolean is_crawler) {
        this.is_crawler = is_crawler;
    }

}
