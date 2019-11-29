package com.vitily.common.annotation;

import java.lang.annotation.*;

/**
 * 整数范围
 */
@Target({ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IntegerRangeValidator {
    int min();
    int max();
}
