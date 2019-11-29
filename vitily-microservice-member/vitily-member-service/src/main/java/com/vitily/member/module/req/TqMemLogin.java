package com.vitily.member.module.req;

import com.vitily.common.annotation.IntegerRangeValidator;
import com.vitily.common.annotation.RegexValidator;
import com.vitily.common.annotation.RequireValidator;
import com.vitily.common.module.TqBase;
import com.vitily.common.util.Validator;

public class TqMemLogin extends TqBase {
	private static final long serialVersionUID = 1L;
	/**
	 * 登录用户名：可能也通过phone号码等登录
	 */
	@RequireValidator
	@RegexValidator(regexStr = Validator.STR_MAX_LENGTH128)
	private String userName;
	/**
	 * 登录密码：第一次加密的密码。
	 */
	@RequireValidator
	@RegexValidator(regexStr = Validator.STR_MAX_LENGTH128)
	private String password;
	/**
	 * 保存时长：小时
	 */
	@IntegerRangeValidator(min = 0,max = 24*7)
	private Integer storeTime;

	public String getUserName() {
		return userName;
	}

	public TqMemLogin setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public TqMemLogin setPassword(String password) {
		this.password = password;
		return this;
	}

	public Integer getStoreTime() {
		return storeTime;
	}

	public TqMemLogin setStoreTime(Integer storeTime) {
		this.storeTime = storeTime;
		return this;
	}
}
