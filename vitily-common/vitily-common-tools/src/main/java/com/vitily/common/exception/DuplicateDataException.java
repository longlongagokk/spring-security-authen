package com.vitily.common.exception;

import com.vitily.common.consts.CommonEnumContainer;

/**
 * 重复数据异常
 * @author lether
 *
 */
public class DuplicateDataException extends CustomerException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 通过捕捉该异常返回json数据
	 */
	public DuplicateDataException(){
		this(CommonEnumContainer.ResultStatus.重复数据.getDesc());
	}
	/**
	 * 通过捕捉该异常返回json数据
	 * @param message 重复数据异常信息
	 */
	public DuplicateDataException(String message){
		super(message,CommonEnumContainer.ResultStatus.重复数据);
	}
}
