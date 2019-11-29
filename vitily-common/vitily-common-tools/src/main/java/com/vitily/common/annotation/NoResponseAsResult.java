package com.vitily.common.annotation;

import java.lang.annotation.*;

/**
 * creator : whh-lether
 * date    : 2019/8/7 18:24
 * desc    :
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoResponseAsResult {

}