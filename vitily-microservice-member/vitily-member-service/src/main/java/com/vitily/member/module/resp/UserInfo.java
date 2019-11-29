package com.vitily.member.module.resp;

import java.io.Serializable;
import java.util.List;

public class UserInfo implements Serializable{
    private String token;
    private String username;
    private Integer userId;
    private Integer expire;
    private List<String> roles;//所属角色

    public String getToken() {
        return token;
    }

    public UserInfo setToken(String token) {
        this.token = token;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserInfo setUsername(String username) {
        this.username = username;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public UserInfo setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getExpire() {
        return expire;
    }

    public UserInfo setExpire(Integer expire) {
        this.expire = expire;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public UserInfo setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }
}
