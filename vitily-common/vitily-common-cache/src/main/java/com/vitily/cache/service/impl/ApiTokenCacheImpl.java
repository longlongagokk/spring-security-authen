package com.vitily.cache.service.impl;

import com.vitily.cache.base.ViyBasicCache;
import com.vitily.cache.foreign.TmApiToken;
import com.vitily.cache.service.ApiTokenCache;
import com.vitily.cache.service.ComServiceFrequentCache;
import com.vitily.cache.service.DictionaryCache;
import com.vitily.common.consts.CommonEnumContainer;
import com.vitily.common.consts.DictionaryKey;
import com.vitily.common.exception.CustomerException;
import com.vitily.common.util.CommonUtil;
import com.vitily.common.util.GUIDGenerator;
import com.vitily.common.util.MD5;
import com.vitily.common.util.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 商户token每次请求都延长x分钟，当x分钟不获取自动取消
 * @author lether
 *
 */
@Component
@Slf4j
public final class ApiTokenCacheImpl extends BaseCacheImpl implements ApiTokenCache {

	private ComServiceFrequentCache comServiceFrequentCache;
	@Autowired
	private ApiTokenCacheImpl(DictionaryCache dictionaryCache, ComServiceFrequentCache comServiceFrequentCache, ViyBasicCache viyBasicCache){
		//null使用字典缓存里的时长
		super(DictionaryKey.Keys.APIToken保存时长.getValue(),null,dictionaryCache,viyBasicCache);
		this.comServiceFrequentCache = comServiceFrequentCache.decorate(DictionaryKey.Keys.APIToken保存时长);
	}
	private final Object oo= new Object();
	private static Object ooo= new Object();
	private final static Object oooo= new Object();
	/**
	 * 获取apitoken：所有接口必须先获取token，然后每次调用接口都需要请求token方可进行调用。
	 * @param ip
	 * @return
	 * @throws Exception
	 */
	@Override
	public TmApiToken getApiToken(String ip)throws Exception{
		if (CommonUtil.isNotNull(ip)) {
			log.info("request ip address:" + ip);
			String hip = MD5.getMD5(ip);
			int perIpMaxCount = NumberUtil.toInteger(getDicCache().getValue(DictionaryKey.Keys.每个IP最多可以同时存在TOKEN数.getValue()));
			int count = comServiceFrequentCache.getCache(DictionaryKey.MemServiceKeyType.每个IP最多可以同时存在TOKEN数, hip);
			if (count >= perIpMaxCount) {
				throw new CustomerException("该IP在同一时间内请求次数过多token，请使用以前有效的token", CommonEnumContainer.ResultStatus.请求次数过多);
			}
			//设置次数
			comServiceFrequentCache.setToServer(DictionaryKey.MemServiceKeyType.每个IP最多可以同时存在TOKEN数, hip);
			TmApiToken token = new TmApiToken();
			token.setApiToken(MD5.getMD5(GUIDGenerator.getGUID()));
			token.setLiveSeconds(getSeconds());
			super.setToServer(token.getApiToken(), ip);

			return token;
		}
		return null;
	}
	@Override
	public boolean isValidAuthToken(String apiToken)throws Exception {
		return super.existsInServer(apiToken);
	}
}
