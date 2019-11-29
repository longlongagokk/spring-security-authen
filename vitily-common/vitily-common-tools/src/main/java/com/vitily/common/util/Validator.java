package com.vitily.common.util;
/**
 * Validator
 */
public class Validator {

    /**
     * STR_MAX_LENGTH2
     */
    public static final String STR_MAX_LENGTH2 = ".{0,2}";

    /**
     * STR_MAX_LENGTH8
     */
    public static final String STR_MAX_LENGTH8 = ".{0,8}";
    
    /**
     * STR_MAX_LENGTH16
     */
    public static final String STR_MAX_LENGTH16 = ".{0,16}";

    /**
     * STR_MAX_LENGTH32
     */
    public static final String STR_MAX_LENGTH32 = ".{0,32}";
    
    /**
     * STR_MAX_LENGTH64
     */
    public static final String STR_MAX_LENGTH64 = ".{0,64}";

    /**
     * STR_MAX_LENGTH128
     */
    public static final String STR_MAX_LENGTH128 = ".{0,128}";

    /**
     * STR_MAX_LENGTH255
     */
    public static final String STR_MAX_LENGTH255 = ".{0,255}";

    /**
     * STR_MAX_LENGTH511
     */
    public static final String STR_MAX_LENGTH511 = ".{0,511}";

    /**
     * STR_MAX_LENGTH1023
     */
    public static final String STR_MAX_LENGTH1023 = ".{0,1023}";
    /**
     * 6位整数
     */
    public static final String Number_MAX_LENGTH6 = "^\\d{0,6}?$";

    /**
     * 8位数字2位小数
     */
    public static final String Number_MAX_LENGTH8 = "^\\d{0,8}(\\.\\d{0,2})?$";
    /**
     * 11位整数
     */
    public static final String Number_MAX_LENGTH11 = "^\\d{0,11}?$";
    /**
     * 32位整数
     */
    public static final String Number_MAX_LENGTH32 = "^\\d{0,32}?$";
    public static final String PHONE_NUMBER = "^((13)|(14)|(15)|(16)|(17)|(18)|(19))(([0-9]{9})$)";
    public static final String EMAIL = "^([a-z0-9A-Z_]+[-|.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,10}$";
    /**
     * 日期
     */
    public static final String DATE_MAX_LENGTH11 = "^\\d{4}-\\d{2}-\\d{2}$";
    /**
     * 时间
     */
    public static final String DATE_TIME_REG = "^\\d{4}-\\d{2}-\\d{2}\\W\\d{2}-\\d{2}-\\d{2}$";
    public static final String PRICE_REG = "(^[1-9]\\d*(\\.\\d{1,2})?$)|(^0(\\.\\d{1,2})?$)";
}
