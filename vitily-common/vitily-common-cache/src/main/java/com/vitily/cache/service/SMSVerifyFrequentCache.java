package com.vitily.cache.service;

/**
 * 手机验证码频繁缓存
 * 5分钟一次：由于时间较短，只在服务器本地做(ehcache)
 * 
 * @author lether
 *
 */
public interface SMSVerifyFrequentCache extends BaseCache {

	/**
	 * 更新调用次数至本机：如果不存在则设置为1
	 * 否则依次叠加
	 * @param key 1
	 * @param content:短信内容：每次都会改的
	 */
	void setToServer(String key, String content);
	/**
	 * 更新错误次数 ＋1
	 * @param key 1
	 * @ 2
	 */
	void upErrorCount(String key);
}
