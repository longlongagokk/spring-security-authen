package com.vitily.common.util;

public abstract class ServiceEncryUtil {
    public static String encodeMemberPassword(String rawPassword,String salt){
        return MD5.getMD5(rawPassword + salt);
    }
    public static String getMemberToken(String username,String secret){
        return MD5.getMD5(secret + username);
    }
}
