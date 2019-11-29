package com.vitily.common.annotation;

import java.lang.annotation.*;

/**
 * decimal范围
 */
@Target({ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BigDecimalRangeValidator {
    double min();
    double max();
}
