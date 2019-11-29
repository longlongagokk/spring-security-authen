package com.vitily.cache.service.impl;

import com.vitily.cache.base.ViyBasicCache;
import com.vitily.cache.service.ComServiceFrequentCache;
import com.vitily.cache.service.DictionaryCache;
import com.vitily.cache.service.SessionCache;
import com.vitily.common.consts.DictionaryKey;
import com.vitily.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * session
 * @author lether
 *
 */
@Component
@Slf4j
public final class SessionCacheImpl extends BaseCacheImpl implements SessionCache {

	@Autowired
	private SessionCacheImpl(DictionaryCache dictionaryCache, ComServiceFrequentCache comServiceFrequentCache, ViyBasicCache viyBasicCache){
		//null使用字典缓存里的时长
		super(DictionaryKey.Keys.SESSION保存时长.getValue(),null,dictionaryCache,viyBasicCache);
	}

	@Override
	public Object getSession(String sessionKey) {
		if(StringUtil.isEmpty(sessionKey)){
			return null;
		}
		Object result = null;
		try{
			result = super.getFromServer(sessionKey);
		}catch (Exception e){
			log.warn(e.getMessage(),e);
		}
		return result;
	}

	@Override
	public boolean setSession(String sessionKey,Object value) {
		if(StringUtil.isEmpty(sessionKey)){
			return false;
		}
		try{
			super.setToServer(sessionKey,value);
		}catch (Exception e){
			log.warn(e.getMessage(),e);
			return false;
		}
		return true;
	}

	@Override
	public boolean removeSession(String sessionKey) {
		try{
			super.removeFromServer(sessionKey);
		}catch (Exception e){
			log.warn(e.getMessage(),e);
			return false;
		}
		return true;
	}
}
