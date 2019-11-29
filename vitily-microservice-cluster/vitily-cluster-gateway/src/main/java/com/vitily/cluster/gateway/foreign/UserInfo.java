package com.vitily.cluster.gateway.foreign;

import java.io.Serializable;
import java.util.List;

public class UserInfo implements Serializable{
    /**
     * token
     */
    private String token;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * token存活期，最好使用时间戳，Long类型
     */
    private Integer expire;
    private List<Integer> roles;//所属角色

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

    public List<Integer> getRoles() {
        return roles;
    }

    public UserInfo setRoles(List<Integer> roles) {
        this.roles = roles;
        return this;
    }
}
