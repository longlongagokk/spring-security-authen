package com.vitily.common.annotation;

import java.lang.annotation.*;

/**
 * 长整数范围
 */
@Target({ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LongRangeValidator {
    long min();
    long max();
}