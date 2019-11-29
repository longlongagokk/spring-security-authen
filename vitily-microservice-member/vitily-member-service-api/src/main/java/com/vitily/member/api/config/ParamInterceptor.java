package com.vitily.member.api.config;

import com.vitily.common.consts.CommonEnumContainer;
import com.vitily.common.exception.CustomerException;
import com.vitily.common.module.Result;
import com.vitily.common.module.TqBase;
import com.vitily.common.util.CommonUtil;
import com.vitily.member.api.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Order(value = 1)
@Aspect
@Component
@Slf4j
public class ParamInterceptor {

	@Pointcut("execution(* com.vitily.member.api.controller.auth.*.*(..))")
	public void controllerMethodPointcut() {}

	@Around("controllerMethodPointcut()")
	public Object Interceptor(ProceedingJoinPoint jp)throws Throwable{
		return checkTokenAndParam(jp);
	}

	private Object checkTokenAndParam(JoinPoint jp)throws Throwable{
		Object[] args = jp.getArgs();
		if (args.length < 1 || !(args[0] instanceof TqBase)) {
			log.info("接口开发方书写接口有误，请联系接口开发方.并提供详细信息,接口需要至少1个参数，第一个为 TqBase 的扩展类");
			throw new CustomerException("接口开发方书写接口有误，请联系接口开发方.并提供详细信息", CommonEnumContainer.ResultStatus.接口不存在);
		}
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		Object ar1 = args[0];//取得请求参数
		TqBase tqBase = (TqBase) ar1;
		tqBase.setReqIp(HttpUtil.getIP(request));
		Result result = CommonUtil.isLegalParameter(ar1);
		if(result.isError()){
			throw new CustomerException(result);
		}
		//结束，返回指定值
		return ((ProceedingJoinPoint) jp).proceed();
	}

}
