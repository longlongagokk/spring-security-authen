package com.vitily.member.util;

import com.vitily.common.consts.CommonEnumContainer;
import com.vitily.common.exception.CustomerException;
import com.vitily.common.util.*;
import com.vitily.member.module.view.TvMemBase;

/**
 * 会员相关
 * @author lether
 *
 */
public class MemberUtil {
	public static void checkMemberName(String name,String phone,String email){
		if(CommonUtil.isPhone(name) && !StringUtil.isEmpty(phone) && !CommonUtil.isEqual(name, phone)){
			throw new CustomerException("登录名为手机号但是跟用户手机号不一致", CommonEnumContainer.ResultStatus.参数不符合要求);
		}else if(CommonUtil.isEmail(name) && !StringUtil.isEmpty(email) && !CommonUtil.isEqual(name, email)){
			throw new CustomerException("登录名为Email但是跟用户Email不一致", CommonEnumContainer.ResultStatus.参数不符合要求);
		}
	}

	public static void outputMemBase(TvMemBase e){
		e.setUpdateDate(null);
		e.setDeltag(null);
		e.setSort(null);
		e.setSalt(null);
		e.setPassword(null);
		e.setQuestion(null);
		e.setAnswer(null);
	}
}
