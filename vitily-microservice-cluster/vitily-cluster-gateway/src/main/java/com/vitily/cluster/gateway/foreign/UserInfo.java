package com.vitily.cluster.gateway.foreign;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserInfo implements Serializable{
    /**
     * token
     */
    private String token;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * token存活期，最好使用时间戳，Long类型
     */
    private Long expire;
    private List<Integer> roles;//所属角色
}
