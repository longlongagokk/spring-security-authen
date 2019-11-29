package com.vitily.member.module.resp;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Data
public class UserInfo implements Serializable{
    private String token;
    private String userName;
    private Integer userId;
    private Long expire;
    private List<String> roles;//所属角色
}
