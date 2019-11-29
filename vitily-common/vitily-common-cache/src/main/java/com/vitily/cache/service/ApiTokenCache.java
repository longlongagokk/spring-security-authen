package com.vitily.cache.service;

import com.vitily.cache.foreign.TmApiToken;

/**
 * apiToken缓存相关
 * @version
 * @author:  lether
 * @创建时间: 2017-07-21
 */
public interface ApiTokenCache extends BaseCache {
	/**
	 * 获取apitoken：所有接口必须先获取token，然后每次调用接口都需要请求token方可进行调用。
	 * @param ip
	 * @return
	 * @throws Exception
	 */
	TmApiToken getApiToken(String ip)throws Exception;

	/**
	 * 判断token是否有效
	 * @param apiToken
	 * @throws Exception
	 * @return
	 */
	boolean isValidAuthToken(String apiToken)throws Exception;
}
