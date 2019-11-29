package com.vitily.member.module.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vitily.common.module.BaseEntity;
import com.vitily.common.util.DateUtil;

import java.util.Date;

/**
 * 描述:会员主表表的实体类
 * @version
 * @author:  pc
 * @创建时间: 2019-10-30
 */
public class TbMemBase extends BaseEntity {
    /**
     * 会员登录名
     */
    private String name;

    /**
     * 分组id
     */
    private Integer groupId;

    /**
     * 0个人用户1企业用户
     */
    private Integer type;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * email
     */
    private String email;

    /**
     * 来源
     */
    private Integer regFrom;

    /**
     * qq号
     */
    private String qq;

    /**
     * 描述
     */
    private String description;

    /**
     * 用户头像
     */
    private String headPortrait;

    /**
     * salt
     */
    private String salt;

    /**
     * 密码
     */
    private String password;

    /**
     * tokenkey
     */
    private String tokenKey;

    /**
     * 问题
     */
    private String question;

    /**
     * 问题答案
     */
    private String answer;

    /**
     * 是否已开通信用信息：0未开通1已开通
     */
    private Boolean openCreditfile;

    /**
     * 最后一次登陆时间
     */
    @JsonFormat(pattern = DateUtil.JSONOUTPUT_yyyy_MM_dd_HH_mm_ss_EN,timezone = DateUtil.timeZone)
    private Date lastLoginDate;

    /**
     * 推荐人id
     */
    private Integer recommendMemberId;

    /**
     * 我的推荐码
     */
    private String recommendCode;

    /**
     * 会员登录名
     * [whh]@return name 会员登录名
     */
    public String getName() {
        return name;
    }

    /**
     * 会员登录名
     * @param name 会员登录名
     */
    public TbMemBase setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 分组id
     * [whh]@return group_id 分组id
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * 分组id
     * @param groupId 分组id
     */
    public TbMemBase setGroupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * 0个人用户1企业用户
     * [whh]@return type 0个人用户1企业用户
     */
    public Integer getType() {
        return type;
    }

    /**
     * 0个人用户1企业用户
     * @param type 0个人用户1企业用户
     */
    public TbMemBase setType(Integer type) {
        this.type = type;
        return this;
    }

    /**
     * 昵称
     * [whh]@return nick_name 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 昵称
     * @param nickName 昵称
     */
    public TbMemBase setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    /**
     * email
     * [whh]@return email email
     */
    public String getEmail() {
        return email;
    }

    /**
     * email
     * @param email email
     */
    public TbMemBase setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * 来源
     * [whh]@return reg_from 来源
     */
    public Integer getRegFrom() {
        return regFrom;
    }

    /**
     * 来源
     * @param regFrom 来源
     */
    public TbMemBase setRegFrom(Integer regFrom) {
        this.regFrom = regFrom;
        return this;
    }

    /**
     * qq号
     * [whh]@return qq qq号
     */
    public String getQq() {
        return qq;
    }

    /**
     * qq号
     * @param qq qq号
     */
    public TbMemBase setQq(String qq) {
        this.qq = qq;
        return this;
    }

    /**
     * 描述
     * [whh]@return description 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 描述
     * @param description 描述
     */
    public TbMemBase setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * 用户头像
     * [whh]@return head_portrait 用户头像
     */
    public String getHeadPortrait() {
        return headPortrait;
    }

    /**
     * 用户头像
     * @param headPortrait 用户头像
     */
    public TbMemBase setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
        return this;
    }

    /**
     * salt
     * [whh]@return salt salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * salt
     * @param salt salt
     */
    public TbMemBase setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    /**
     * 密码
     * [whh]@return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password 密码
     */
    public TbMemBase setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * tokenkey
     * [whh]@return token_key tokenkey
     */
    public String getTokenKey() {
        return tokenKey;
    }

    /**
     * tokenkey
     * @param tokenKey tokenkey
     */
    public TbMemBase setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
        return this;
    }

    /**
     * 问题
     * [whh]@return question 问题
     */
    public String getQuestion() {
        return question;
    }

    /**
     * 问题
     * @param question 问题
     */
    public TbMemBase setQuestion(String question) {
        this.question = question;
        return this;
    }

    /**
     * 问题答案
     * [whh]@return answer 问题答案
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * 问题答案
     * @param answer 问题答案
     */
    public TbMemBase setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    /**
     * 是否已开通信用信息：0未开通1已开通
     * [whh]@return open_creditfile 是否已开通信用信息：0未开通1已开通
     */
    public Boolean getOpenCreditfile() {
        return openCreditfile;
    }

    /**
     * 是否已开通信用信息：0未开通1已开通
     * @param openCreditfile 是否已开通信用信息：0未开通1已开通
     */
    public TbMemBase setOpenCreditfile(Boolean openCreditfile) {
        this.openCreditfile = openCreditfile;
        return this;
    }

    /**
     * 最后一次登陆时间
     * [whh]@return last_login_date 最后一次登陆时间
     */
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * 最后一次登陆时间
     * @param lastLoginDate 最后一次登陆时间
     */
    public TbMemBase setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
        return this;
    }

    /**
     * 推荐人id
     * [whh]@return recommend_member_id 推荐人id
     */
    public Integer getRecommendMemberId() {
        return recommendMemberId;
    }

    /**
     * 推荐人id
     * @param recommendMemberId 推荐人id
     */
    public TbMemBase setRecommendMemberId(Integer recommendMemberId) {
        this.recommendMemberId = recommendMemberId;
        return this;
    }

    /**
     * 我的推荐码
     * [whh]@return recommend_code 我的推荐码
     */
    public String getRecommendCode() {
        return recommendCode;
    }

    /**
     * 我的推荐码
     * @param recommendCode 我的推荐码
     */
    public TbMemBase setRecommendCode(String recommendCode) {
        this.recommendCode = recommendCode;
        return this;
    }
}