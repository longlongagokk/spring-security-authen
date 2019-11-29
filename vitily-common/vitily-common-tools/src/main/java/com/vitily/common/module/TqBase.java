package com.vitily.common.module;

import com.vitily.common.annotation.RegexValidator;
import com.vitily.common.annotation.RequireValidator;
import com.vitily.common.util.Validator;

import java.io.Serializable;

public class TqBase implements Serializable {
	/**
	 * 基础token
	 */
	//@RequireValidator
	//@RegexValidator(regexStr = Validator.STR_MAX_LENGTH64)
	private String apiToken;
	private String reqIp;

	public String getApiToken() {
		return apiToken;
	}

	public TqBase setApiToken(String apiToken) {
		this.apiToken = apiToken;
		return this;
	}

	public String getReqIp() {
		return reqIp;
	}

	public TqBase setReqIp(String reqIp) {
		this.reqIp = reqIp;
		return this;
	}
}
