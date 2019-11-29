package com.vitily.common.module;

import com.vitily.common.consts.CommonEnumContainer;
import com.vitily.common.util.CommonUtil;

import java.beans.Transient;
import java.io.Serializable;

/**
 * creator : whh-lether
 * date    : 2019/6/20 9:12
 * desc    :
 **/
public final class Result implements Serializable{
    private static final long serialVersionUID = 1L;
    final String code;
    final String message;
    final Object content;

    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public Object getContent() {
        return content;
    }

    private Result(){
        this.code = null;
        this.message = null;
        this.content = null;
    }
    private Result(Builder builder) {
        this.code = builder.code;
        this.message = builder.message;
        this.content = builder.content;
    }
    @Transient
    public final boolean isSuccess(){
        return CommonUtil.isEqual(this.getCode(),CommonEnumContainer.ResultStatus.正常.getValue());
    }
    @Transient
    public final boolean isNotSuccess(){
        return !isSuccess();
    }
    @Transient
    public final boolean isError(){
        return !CommonUtil.isEqual(this.getCode(),CommonEnumContainer.ResultStatus.正常.getValue());
    }
    public static Result success(){
        return success(CommonEnumContainer.ResultStatus.正常.getDesc());
    }
    public static Result success(String message){
        return new Builder(CommonEnumContainer.ResultStatus.正常.getValue())
                .message(message)
                .build();
    }
    public static Result success(Object content){
        return new Builder(CommonEnumContainer.ResultStatus.正常.getValue())
                .message(CommonEnumContainer.ResultStatus.正常.getDesc())
                .content(content)
                .build();
    }
    public static Result error(CommonEnumContainer.ResultStatus status){
        return error(status,status.getDesc());
    }
    public static Result error(CommonEnumContainer.ResultStatus status,String message){
        return new Builder(status.getValue())
                .message(message)
                .build();
    }
    public static final class Builder{
        final String code;
        String message;
        Object content;
        public Builder(String code){
            this.code = code;
        }
        public Builder message(String message){
            this.message = message;
            return this;
        }
        public Builder content(Object content){
            this.content = content;
            return this;
        }
        public Result build(){
            return new Result(this);
        }
    }
}
