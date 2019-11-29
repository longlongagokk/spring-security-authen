package com.vitily.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EnumHelperUtil {
    /**
     *
     * @param clazz 枚举类型
     * @param ordinal 枚举值在枚举中的顺序
     * @param <T> 枚举类型
     * @return 返回枚举类型项
     */
    public static <T extends Enum<T>> T indexOf(Class<T> clazz, int ordinal){
        return clazz.getEnumConstants()[ordinal];
    }

    /**
     * 通过枚举值名称获取枚举
     * @param clazz 枚举类型
     * @param name 枚举项名称
     * @param <T> 枚举类型
     * @return 对应枚举项
     */
    public static <T extends Enum<T>> T nameOf(Class<T> clazz, String name){
        return Enum.valueOf(clazz, name);
    }

    /**
     *  根据枚举值获取 类型
     * @param clazz 枚举类型
     * @param getValueMethodName 获取值的方法
     * @param value 值
     * @param <T> 枚举类型
     * @return 对应枚举项
     */
    public static <T extends Enum<T>> T getByValue(Class<T> clazz,String getValueMethodName, Object value){
        T result = null;
        try{
            T[] arr = clazz.getEnumConstants();
            Method targetMethod = clazz.getDeclaredMethod(getValueMethodName);
            if(value instanceof Integer){
                Integer ival;
                Integer pval = (Integer) value;
                for(T entity:arr){
                    ival = Integer.valueOf(targetMethod.invoke(entity).toString());
                    if(ival.equals(pval)){
                        result = entity;
                        break;
                    }
                }
            }else if(value instanceof String){
                String sval;
                String pval = (String)value;
                for(T entity:arr){
                    sval = targetMethod.invoke(entity).toString();
                    if(sval.contentEquals(pval)){
                        result = entity;
                        break;
                    }
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static <T extends Enum<T>> T getByValue(Class<T> clazz, Object value){
        return getByValue(clazz,"getValue",value);
    }
    public static <T extends Enum<T>> String getEnumWrapDescByEntity(Enum<T> e,String getDescMethod){
        String output = "";
        if(e != null) {
            try {
                Method getDesc = e.getClass().getDeclaredMethod(getDescMethod);
                output = getDesc.invoke(e).toString();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return output;
    }
    public static <T extends Enum<T>> String getEnumWrapDescByEntity(Enum<T> e){
        return getEnumWrapDescByEntity(e,"getDesc");
    }
    public static <T extends Enum<T>> String getEnumWrapDescByValue(Class<T> clazz,Object value){
        return getEnumWrapDescByEntity(getByValue(clazz,value));
    }
    public static <T extends Enum<T>> Map<String,Object> enumToHash(Class<T> clazz){
        Map map = new HashMap();
        try{
            T[] arr = clazz.getEnumConstants();
            Method getValue = clazz.getDeclaredMethod("getValue");
            for(T entity:arr){
                map.put(entity,getValue.invoke(entity));
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return map;
    }
}
