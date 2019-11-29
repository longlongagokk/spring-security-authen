package com.vitily.cache.service.impl;

import com.vitily.cache.service.ComServiceFrequentCache;
import com.vitily.cache.service.CommonServiceCache;
import com.vitily.common.consts.DictionaryKey;
import com.vitily.common.util.CommonUtil;
import com.vitily.common.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 公共缓存保存次数
 * 
 * @author lether
 *
 */
@Component
public final class ComServiceFrequentCacheImpl implements ComServiceFrequentCache {

	CommonServiceCache commonServiceCache;
	private static volatile boolean init;

	@Autowired
	public ComServiceFrequentCacheImpl(CommonServiceCache commonServiceCache) {
		if(init){
			this.commonServiceCache = commonServiceCache;
		}else{
			init = true;
			this.commonServiceCache = commonServiceCache.getInstance(DictionaryKey.Keys.公共缓存次数保存时长);
		}

	}
	public ComServiceFrequentCache decorate(DictionaryKey.Keys k){
		return new ComServiceFrequentCacheImpl(commonServiceCache.getInstance(k));
	}
	private String getLastKey(DictionaryKey.MemServiceKeyType type, String key){
		return "FREQUENT_"+type.getValue() + "_count:" + key;
	}
	/**
	 *
	 */
	public void setToServer(DictionaryKey.MemServiceKeyType type, String key){
		String lastKey=getLastKey(type,key);
		Integer count= NumberUtil.toInteger(this.commonServiceCache.getFromServer(lastKey));
		if(CommonUtil.isNull(count)){
			count = 0;
		}
		++count;
		this.commonServiceCache.setToServer(lastKey, count);
	}
	public int getCache(DictionaryKey.MemServiceKeyType type, String key){
		String lastKey=getLastKey(type,key);
		Integer count= NumberUtil.toInteger(this.commonServiceCache.getFromServer(lastKey));
		if(CommonUtil.isNull(count)){
			count = 0;
		}
		return count;
	}
	public void removeFromServer(DictionaryKey.MemServiceKeyType type, String key){
		String lastKey=getLastKey(type,key);
		this.commonServiceCache.removeFromServer(lastKey);
	}
}
