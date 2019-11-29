package com.vitily.common.annotation;

import java.lang.annotation.*;

/**
 * 正则匹配范围
 */
@Target({ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RegexValidator {
    String regexStr();
    String errorContent()default "参数[%s]不符合要求，请参考[%s]";
}
