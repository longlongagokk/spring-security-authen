package com.vitily.common.exception;


import com.vitily.common.consts.CommonEnumContainer;

/**
 * 无权限
 * @author lether
 *
 */
public class NoPermissionException extends CustomerException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 通过捕捉该异常返回json数据
	 */
	public NoPermissionException(){
		this(CommonEnumContainer.ResultStatus.无权限.getDesc());
	}
	/**
	 * 通过捕捉该异常返回json数据
	 * @param message 无权限异常信息
	 */
	public NoPermissionException(String message){
		super(message,CommonEnumContainer.ResultStatus.无权限);
	}
}
