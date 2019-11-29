package com.vitily.common.exception;


import com.vitily.common.consts.CommonEnumContainer;

/**
 * 记录不存在
 * @author lether
 *
 */
public class NoExistsException extends CustomerException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 通过捕捉该异常返回json数据
	 */
	public NoExistsException(){
		this(CommonEnumContainer.ResultStatus.查询不存在.getDesc());
	}
	/**
	 * 通过捕捉该异常返回json数据
	 * @param message
	 */
	public NoExistsException(String message){
		super(message,CommonEnumContainer.ResultStatus.查询不存在);
	}
}
