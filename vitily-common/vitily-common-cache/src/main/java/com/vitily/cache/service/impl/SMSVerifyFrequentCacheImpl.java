package com.vitily.cache.service.impl;

import com.vitily.cache.base.ViyBasicCache;
import com.vitily.cache.foreign.SMSVerifyCodeFrequent;
import com.vitily.cache.service.DictionaryCache;
import com.vitily.cache.service.SMSVerifyFrequentCache;
import com.vitily.common.consts.DictionaryKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 手机验证码频繁缓存
 * 5分钟一次：由于时间较短，只在服务器本地做(ehcache)
 * 
 * @author lether
 *
 */
@Component
public final class SMSVerifyFrequentCacheImpl extends BaseCacheImpl implements SMSVerifyFrequentCache {

	@Autowired
	private SMSVerifyFrequentCacheImpl(DictionaryCache dictionaryCache, ViyBasicCache viyBasicCache){
		//存一天:以最有一次为保存标准
		super(DictionaryKey.Keys.会员每天可发送短信次数.getValue(),60 * 60 * 24,dictionaryCache,viyBasicCache);
	}
	/**
	 * 更新调用次数至本机：客户端无权限
	 * 否则依次叠加
	 * @param key 1
	 * @param content :短信内容：每次都会改的
	 */
	public void setToServer(String key,String content){
		SMSVerifyCodeFrequent frequent=(SMSVerifyCodeFrequent)getFromServer(key);
		if(frequent == null){//说明还没调用过
			frequent = new SMSVerifyCodeFrequent();
		}
		frequent.setCount(frequent.getCount() + 1);
		frequent.setMillTime(System.currentTimeMillis());//
		frequent.setContent(content);
		super.setToServer(key, frequent);
	}
	/**
	 * 更新错误次数 ＋1：客户端无权限
	 * @param key 1
	 * @ 2
	 */
	public void upErrorCount(String key){
//		SMSVerifyCodeFrequent frequent=(SMSVerifyCodeFrequent)getFromServer(key);
//		if(frequent == null){//说明还没调用过
//			throw new CustomerException("手机验证码不存在！");
//		}
//		frequent.setErrorCount(frequent.getErrorCount() + 1);
//		setToServer(key, frequent);
	}
}
