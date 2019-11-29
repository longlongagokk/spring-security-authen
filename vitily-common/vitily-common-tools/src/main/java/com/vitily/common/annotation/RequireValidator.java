package com.vitily.common.annotation;

import java.lang.annotation.*;

/**
 * 必须
 */
@Target({ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireValidator {
    String paramName()default "";
}
