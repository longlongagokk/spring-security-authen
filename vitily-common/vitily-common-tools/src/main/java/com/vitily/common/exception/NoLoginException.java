package com.vitily.common.exception;

import com.vitily.common.consts.CommonEnumContainer;

/**
 *未登录 异常
 */
public class NoLoginException extends CustomerException {
	private static final long serialVersionUID = 1L;
	/**
	 * 通过捕捉该异常返回json数据
	 */
	public NoLoginException(){
		this(CommonEnumContainer.ResultStatus.未登录.getDesc());
	}
	/**
	 * 通过捕捉该异常返回json数据
	 * @param message 未登录提示消息
	 */
	public NoLoginException(String message){
		super(message,CommonEnumContainer.ResultStatus.未登录);
	}
}
