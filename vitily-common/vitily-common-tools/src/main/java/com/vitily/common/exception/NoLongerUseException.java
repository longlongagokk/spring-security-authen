package com.vitily.common.exception;


import com.vitily.common.consts.CommonEnumContainer;

/**
 * 不再使用
 * @author lether
 *
 */
public class NoLongerUseException extends CustomerException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 通过捕捉该异常返回json数据
	 */
	public NoLongerUseException(){
		this("不要欺负我，会把你弄哭的哟.");
	}
	/**
	 * 通过捕捉该异常返回json数据
	 * @param message
	 */
	public NoLongerUseException(String message){
		super(message, CommonEnumContainer.ResultStatus.不再使用该录);
	}
}
