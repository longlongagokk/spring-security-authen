package com.vitily.member.api.config;

import com.vitily.common.consts.CommonEnumContainer;
import com.vitily.common.exception.CustomerException;
import com.vitily.common.module.Result;
import com.vitily.common.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.UndeclaredThrowableException;

@RestControllerAdvice
@RequestMapping(value = "error")
@Slf4j
public class CustomExceptionHandler implements ErrorController {
    @Override
    public String getErrorPath() {
        return "error/error";
    }

    @RequestMapping
    public Result error() {
        return Result.error(CommonEnumContainer.ResultStatus.查询不存在,"资源找不到！[请求路径无法匹配]");
    }

    @ExceptionHandler({UndeclaredThrowableException.class,})
    public Result undeclaredThrowableException(HttpServletRequest request, Throwable e)throws Exception{
        log.error("UndeclaredThrowableException, URL = {" + request.getRequestURI() + "}");
        return CommonUtil.getResultByThrowable(e.getCause());
    }
    @ExceptionHandler({Exception.class})
    public Result exception(HttpServletRequest request, Throwable e)throws Exception{
        e.printStackTrace();
        log.error("Exception, URL = {" + request.getRequestURI() + "}");
        return CommonUtil.getResultByThrowable(e);
    }
    //ClassCastException
    @ExceptionHandler({CustomerException.class,})
    public Result customerException(HttpServletRequest request, Throwable e)throws Exception{
        log.debug("CustomerException, URL = {" + request.getRequestURI() + "}");
        return CommonUtil.getResultByThrowable(e);
    }
    @ExceptionHandler({TypeMismatchException.class, BindException.class})
    public Result handleNumberFormatException(HttpServletRequest request, Throwable e){
        log.debug("TypeMismatchException, URL = {" + request.getRequestURI() + "}");
        return Result.error(CommonEnumContainer.ResultStatus.参数不符合要求,"请求参数不匹配，请检查");
    }
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Result httpRequestMethodNotSupportedException(HttpServletRequest request, Throwable e){
        log.debug("HttpRequestMethodNotSupportedException, URL = {" + request.getRequestURI() + "}");
        log.debug(e.toString());
        return Result.error(CommonEnumContainer.ResultStatus.页面请求异常,"不支持该请求方式["+request.getMethod()+"]，请使用正确的调用方式，详情咨询开发人员。");
    }
}
