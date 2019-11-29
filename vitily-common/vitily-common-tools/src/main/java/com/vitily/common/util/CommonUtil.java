package com.vitily.common.util;

import com.vitily.common.annotation.*;
import com.vitily.common.consts.CommonEnumContainer;
import com.vitily.common.exception.CustomerException;
import com.vitily.common.module.BaseEntity;
import com.vitily.common.module.Result;
import com.vitily.common.module.PageInfo;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

/**
 * 一些常用攻击集合
 * @author lether
 *
 */
@Slf4j
public class CommonUtil {
	private CommonUtil(){
		throw new AssertionError();
	}
	/**
	 * 判断某个短字符串是否在常字符串内
	 * @param longStr 1
	 * @param shortStr 2
	 * @param trimStr 3
	 * @return 4
	 */
	public static boolean inStr(String longStr,String shortStr,char trimStr){
		return !StringUtil.isEmpty(shortStr) && (trimStr + longStr + trimStr).contains(trimStr + shortStr + trimStr);
	}
	/**
	 * 判断是否是正常的sql 只有[,a-zA-Z_d]" 才是
	 * @param sql 1
	 * @return 2
	 */
	public static boolean isNormalSql(String sql){
		return null != sql && sql.matches("^[a-z0-9A-Z,_.]*$");
	}

	/**
	 * 匹配逗号、数字(1,2,...3)
	 * @param strVal 1
	 * @return 2
	 */
    public static boolean isNumOrD(String strVal)
    {
    	return null != strVal && strVal.matches("^[-]?[\\d]+([,][-]?[\\d]+)*$");
    }
    /**
     * is email
     * @param strVal 1
     * @return 2
     */
    public static boolean isEmail(String strVal)
    {
    	return null != strVal && strVal.indexOf('@') > -1 && strVal.matches("^([a-z0-9A-Z_]+[-|.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
    }
    /**
     * 是否手机号码
     * @param strVal 1
     * @return 2
     */
    public static boolean isPhone(String strVal)
    {
    	return null != strVal && strVal.matches("^((13)|(14)|(15)|(16)|(17)|(18)|(19))(([0-9]{9})$)");
    }

	/**
	 * 是否整数
	 * @param strVal 1
	 * @return 2
	 */
	public static boolean isNumber(String strVal)
    {
    	return null != strVal && strVal.matches("^-?\\d+$");
    }

	/**
	 * 匹配逗号、数字｛含小数点｝
	 * @param strVal 1
	 * @return 2
	 */
	public static boolean isDecimalOrD(String strVal)
    {
    	return null != strVal && strVal.matches("^(\\d+(\\.)?\\d*)([,]\\d+(\\.)?\\d*)*$");
    }
    public static boolean isDecimal(String strVal){
    	return null != strVal && strVal.matches("^-?\\d+((\\.)?\\d+)?$");
    }
    public static boolean isNormalFilepath(String strVal){
		return (strVal != null && strVal.length() != 0) && strVal.matches("^[a-z0-9A-Z\\-_/]*$");
	}
    /**
     * 获取随即字符串[A-Z1-9]
     * @param length 1
     * @return 2
     */
    public static String ranStr(int length){
    	String base = "ABCDEFGHIJKLMNPQRSTUVWXYZ123456789";   
	    Random random = new Random();
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
    }
	private static <T> boolean isEqualOfType(T left,T right){
        if (null != left) {
            return left.equals(right);
        } else if (null != right) {
            return false;
        }
		return true;
	}
	public final static boolean isEmpty(Object[] array){
		return array == null || array.length == 0;
	}
	public final static boolean isNotEmpty(Object[] array){
		return !isEmpty(array);
	}
	public final static boolean isEmpty(Collection<?> collection){
		return collection == null || collection.isEmpty();
	}
	public final static boolean isNotEmpty(Collection<?> collection){
		return !isEmpty(collection);
	}
	public final static boolean isEmpty(Map<?, ?> map){
		return map == null || map.isEmpty();
	}
	public final static boolean isNotEmpty(Map<?, ?> map){
		return !isEmpty(map);
	}
	public final static boolean isNull(Object o){
		return null == o;
	}
	public final static boolean isNotNull(Object o){
		return null != o;
	}

	public static boolean isEqual(String left,String right){
		return isEqualOfType(left,right);
	}
	public static boolean isEqual(BigDecimal left, BigDecimal right){
		return isEqualOfType(left,right);
	}
	public static boolean isEqual(Integer left, Integer right){
		return isEqualOfType(left,right);
	}
	public static boolean isEqual(Long left, Long right){
		return isEqualOfType(left,right);
	}
    public static boolean isEqual(Enum left, Enum right){
        return isEqualOfType(left,right);
    }
	public static void SystemPrintln(Object obj){
		System.out.println(obj);
	}
	public static void SystemDebugPrintln(Object obj){
		System.out.println(obj);
	}
	public static Result getResultByThrowable(Throwable e){
		Result result;
		if(e instanceof CustomerException){
			log.debug(e.getMessage());
			result = ((CustomerException) e).getResult();
		}else{
			log.error(e.getMessage(),e);
			result = Result.error(CommonEnumContainer.ResultStatus.操作异常);
		}
		return result;
	}
	public final static void formatInsertEntity(BaseEntity be){
		be.setCreateDate(new Date());
		be.setDeltag(CommonEnumContainer.Deltag.正常.getValue());
	}
	public final static void formatUpdateEntity(BaseEntity be){
		be.setUpdateDate(new Date());
	}
	/**
	 * 22位数：订单
	 * OrderTypeStr：3位订单类型
	 * serviceId:4位业务ID，不足补0，多的截取后4位
	 * memberId：4会员ID，不足补0，多的截取后4位
	 * timeMills:11位时间戳
	 *1234567890123456789012
	 * @param OrderTypeStr :订单分类：如001，002
	 * @param serviceId：涉及业务Id
	 * @param memberId：会员Id
	 * @return 1
	 */
	public static String getOrderNo(String OrderTypeStr,Integer serviceId,Integer memberId,boolean isMilSeconds){
		if(OrderTypeStr == null){
			OrderTypeStr = "001";
		}
		char oc[] = {
				'0','0','0','0','0',
				'0','0','0','0','0',
				'0','0','0','0','0',
				'0','0','0','0','0',
				'0','0'};
		char[] tc = OrderTypeStr.toCharArray();
		//3位订单类型号
		setCharOfString(oc,OrderTypeStr,0,3);
		//4位业务id号
		setCharOfString(oc,String.valueOf(serviceId),3,4);
		//4位会员
		setCharOfString(oc,String.valueOf(memberId),7,4);
		//11位时间戳
		if(isMilSeconds){
			setCharOfString(oc,DateUtil.dateToDateString(new Date(),"ddHHmmssSSS"),11,11);
		}else{
			setCharOfString(oc,DateUtil.dateToDateString(new Date(),"ddHHmmss"),11,8);
		}

		return String.valueOf(oc);
	}

	private static void setCharOfString(char[] cc,String v,int beginPos,int len){
		if(v == null){
			v = "";
		}
		char[] tc = v.toCharArray();
		for(int i = len-1,l = tc.length -1;i >= 0 && l >=0;--i,--l){
			cc[beginPos+i] = tc[l];
		}
	}
	public static Result isLegalParameter(Object obj) throws Exception {
		//判断参数是否合法
		Class <?> clazz = obj.getClass();
		if(obj instanceof PageInfo){
			return Result.success();
		}
		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			Field[] field = clazz.getDeclaredFields();
			for (Field f : field) {
				f.setAccessible(true);
				Object ov = f.get(obj);
				boolean isNull = CommonUtil.isNull(ov);
				//先判断是否有必填项
				RequireValidator require = f.getAnnotation(RequireValidator.class);
				if (CommonUtil.isNotNull(require) && isNull) {
					String _name = require.paramName();
					if(StringUtil.isEmpty(_name)){
						_name = f.getName();
					}
					return Result.error(CommonEnumContainer.ResultStatus.参数不全,"参数[" + _name + "]必填");
				}
				if(CommonUtil.isNull(ov)){
					continue;
				}
				if (ov instanceof Integer) {
					//获取属性值上的ApiIntegerParamCheck注解
					boolean hasRange = f.isAnnotationPresent(IntegerRangeValidator.class);
					if (!hasRange) {
						continue;
					}
					IntegerRangeValidator range = f.getAnnotation(IntegerRangeValidator.class);
					if (!isNull) {
						Integer value = (Integer) ov;
						int min = range.min();
						int max = range.max();
						if (value < min || value > max) {
							return Result.error(CommonEnumContainer.ResultStatus.参数不符合要求,"参数[" + f.getName() + "]范围的范围应为：{" + min + "，" + max + "}");
						}
					}
				}else if(ov instanceof Long){
					//获取属性值上的ApiIntegerParamCheck注解
					boolean hasRange = f.isAnnotationPresent(LongRangeValidator.class);
					if (!hasRange) {
						continue;
					}
					LongRangeValidator range = f.getAnnotation(LongRangeValidator.class);
					if (!isNull) {
						Long value = (Long) ov;
						long min = range.min();
						long max = range.max();
						if (value < min || value > max) {
							return Result.error(CommonEnumContainer.ResultStatus.参数不符合要求,"参数[" + f.getName() + "]范围的范围应为：{" + min + "，" + max + "}");
						}
					}
				}else if(ov instanceof BigDecimal){
					//获取属性值上的ApiIntegerParamCheck注解
					boolean hasRange = f.isAnnotationPresent(BigDecimalRangeValidator.class);
					if (!hasRange) {
						continue;
					}
					BigDecimalRangeValidator range = f.getAnnotation(BigDecimalRangeValidator.class);
					if (!isNull) {
						BigDecimal value = (BigDecimal) ov;
						double min = range.min();
						double max = range.max();
						if (value.doubleValue() < min || value.doubleValue() > max) {
							return Result.error(CommonEnumContainer.ResultStatus.参数不符合要求,"参数[" + f.getName() + "]范围的范围应为：{" + min + "，" + max + "}");
						}
					}
				}else if (ov instanceof String) {
					boolean hasRegex = f.isAnnotationPresent(RegexValidator.class);
					if (!hasRegex) {
						continue;
					}
					if (!isNull) {
						RegexValidator regex = f.getAnnotation(RegexValidator.class);
						if (!((String) ov).matches(regex.regexStr())) {
							return Result.error(CommonEnumContainer.ResultStatus.参数不符合要求,String.format(regex.errorContent(),f.getName(),regex.regexStr()));
						}
					}
				} else if (ov instanceof List) {//数组
					List list = (List) ov;
					for (Object o : list) {
						Result result = isLegalParameter(o);
						if(result.isError()){
							return result;
						}
					}
				} else {
					String className = ov.getClass().getName();
					if(className.indexOf("com.vitily") > -1) {
						Result result = isLegalParameter(ov);
						if(result.isError()){
							return result;
						}
					}
				}
			}
		}
		return Result.success();
	}
	public static boolean booleanOf(String val){
		return Boolean.valueOf(val) || !CommonUtil.isEqual("0",val);
	}
}
