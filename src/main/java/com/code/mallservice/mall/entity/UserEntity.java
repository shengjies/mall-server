package com.code.mallservice.mall.entity;

import java.util.List;

/**
 * 用户信息
 */
public class UserEntity {
    private  int id;
    private String username;
    private String password;
    private String role_code;
    private int is_group;//默认未0 未分配
    private String  c_date;

    private List<String> groupId;//组员编号

    private List<Integer> fb_id;
    private List<Integer> domain_id;

    private List<FBEtity> fbEtities;
    private List<DomailEntity> domailEntities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getC_date() {
        return c_date;
    }

    public void setC_date(String c_date) {
        this.c_date = c_date;
    }

    public String getRole_code() {
        return role_code;
    }

    public void setRole_code(String role_code) {
        this.role_code = role_code;
    }

    public List<Integer> getFb_id() {
        return fb_id;
    }

    public void setFb_id(List<Integer> fb_id) {
        this.fb_id = fb_id;
    }

    public List<Integer> getDomain_id() {
        return domain_id;
    }

    public void setDomain_id(List<Integer> domain_id) {
        this.domain_id = domain_id;
    }

    public List<FBEtity> getFbEtities() {
        return fbEtities;
    }

    public void setFbEtities(List<FBEtity> fbEtities) {
        this.fbEtities = fbEtities;
    }

    public List<DomailEntity> getDomailEntities() {
        return domailEntities;
    }

    public void setDomailEntities(List<DomailEntity> domailEntities) {
        this.domailEntities = domailEntities;
    }

    public int getIs_group() {
        return is_group;
    }

    public void setIs_group(int is_group) {
        this.is_group = is_group;
    }

    public List<String> getGroupId() {
        return groupId;
    }

    public void setGroupId(List<String> groupId) {
        this.groupId = groupId;
    }
}
