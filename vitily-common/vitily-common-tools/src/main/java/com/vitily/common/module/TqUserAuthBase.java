package com.vitily.common.module;

import com.vitily.common.annotation.RegexValidator;
import com.vitily.common.annotation.RequireValidator;
import com.vitily.common.util.Validator;

/**
 * creator : whh-lether
 * date    : 2018/12/19 17:37
 * desc    :
 **/
public class TqUserAuthBase extends TqBase {
    private static final long serialVersionUID = 1L;
    /**
     * 用户token token
     */
    @RequireValidator
    @RegexValidator(regexStr = Validator.STR_MAX_LENGTH64)
    private String userAuthToken;
    private Integer userAuthId;

    public String getUserAuthToken() {
        return userAuthToken;
    }

    public TqUserAuthBase setUserAuthToken(String userAuthToken) {
        this.userAuthToken = userAuthToken;
        return this;
    }

    public Integer getUserAuthId() {
        return userAuthId;
    }

    public TqUserAuthBase setUserAuthId(Integer userAuthId) {
        this.userAuthId = userAuthId;
        return this;
    }
}
