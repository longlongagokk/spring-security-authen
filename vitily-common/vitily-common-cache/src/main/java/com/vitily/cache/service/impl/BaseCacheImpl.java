package com.vitily.cache.service.impl;

import com.vitily.cache.base.ViyBasicCache;
import com.vitily.cache.service.BaseCache;
import com.vitily.cache.service.DictionaryCache;
import com.vitily.common.util.CommonUtil;
import com.vitily.common.util.NumberUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * 通用缓存：服务器
 * @author lether
 *
 */
@Slf4j
public abstract class BaseCacheImpl<T> implements BaseCache<T> {
	public static final int COMMON_CACHE_DEFAULT_VALITY_SECONDS=60;
	/**
	 * 前置key：除了字典缓存，一般是字典表里的key
	 */
	private String preKey;
	private String split;
	/**
	 * 保存时长(秒)：如果为null 则从字典缓存里取数据，如果再为null，则设置为COMMON_CACHE_DEFAULT_VALITY_SECONDS：公共默认缓存时长
	 */
	private Integer seconds;
	protected DictionaryCache dicCache;
	protected ViyBasicCache viyBasicCache;
	public String getPreKey() {
		return preKey + split;
	}
	public Integer getSeconds() {
		if(CommonUtil.isNull(seconds)){
			Integer _seconds = COMMON_CACHE_DEFAULT_VALITY_SECONDS;
			try{
				_seconds = NumberUtil.toInteger(dicCache.getValue(this.preKey));
				if(CommonUtil.isNull(_seconds)){
					_seconds = COMMON_CACHE_DEFAULT_VALITY_SECONDS;
				}
			} catch (Exception e) {
				log.error("系统异常",e);
			}
			return _seconds;//每次都需要重新获取因为是单例模式
		}
		return seconds;
	}
    public DictionaryCache getDicCache() {
        return dicCache;
    }
	public ViyBasicCache getViyBasicCache() {
		return viyBasicCache;
	}
	public void setViyBasicCache(ViyBasicCache viyBasicCache) {
		this.viyBasicCache = viyBasicCache;
	}

	/**
	 * seconds为null时 dictionaryCache必不为null否则报错
	 * @param preKey 1
	 * @param seconds 2
	 * @param dictionaryCache 3
	 * @param viyBasicCache 4
	 */
	protected BaseCacheImpl(String preKey, Integer seconds, DictionaryCache dictionaryCache, ViyBasicCache viyBasicCache){
		this(preKey,seconds,dictionaryCache,viyBasicCache,":");
	}
	/**
	 * seconds为null时 dictionaryCache必不为null否则报错
	 * @param preKey 1
	 * @param seconds 2
	 * @param dictionaryCache 3
	 * @param viyBasicCache 4
	 */
	protected BaseCacheImpl(String preKey, Integer seconds, DictionaryCache dictionaryCache, ViyBasicCache viyBasicCache,String split){
		this.preKey=preKey;
		this.seconds=seconds;
		this.dicCache = dictionaryCache;
		this.viyBasicCache = viyBasicCache;
		this.split = split;
	}

	@Override
	public void setToServer(String key,Object value){
		viyBasicCache.put(getPreKey() + key, value, getSeconds(),false);

	}
	@Override
	public void setToServer(String key,Object value,Integer seconds){
		viyBasicCache.put(getPreKey() + key, value, seconds,false);
	}
	@Override
	public Object getFromServer(String key){
		return viyBasicCache.get(getPreKey()+key);
	}
	/**
	 * 只更新值，不更新失效时间
	 * @param key
	 * @param value
	 */
	@Override
	public void upToServer(String key,Object value){
		viyBasicCache.update(getPreKey() + key,value,false);
	}
	@Override
	public void removeFromServer(String key){
		viyBasicCache.remove(getPreKey()+key);
	}
	@Override
	public boolean existsInServer(String key){
		boolean res = false;
		try{
			res =  viyBasicCache.exists(getPreKey()+key);
		}catch (Exception e){
			log.error(e.getMessage(),e);
		}
		return res;
	}
	/**
	 * 入列队
	 * @param key 1
	 * @param value 2
	 */
	@Override
	public void lpush(String key,Object value){
		viyBasicCache.lpush(getPreKey()+key, value);
	}
	/**
	 * 出列队
	 * @param key 1
	 * @return 2
	 */
	@Override
	public Object rpop(String key){
		return viyBasicCache.rpop(getPreKey()+key);
	}
	@Override
	public Long hset(String key,String field,Object value){
		return viyBasicCache.hset(getPreKey()+key, field, value);
	}
	@Override
	public String hmset(String key,Map<String, ?> map){
		return viyBasicCache.hmset(getPreKey()+key, map);
	}
	@Override
	public Object hget(String key,String field){
		return viyBasicCache.hget(getPreKey()+key, field);
	}
	@Override
	public List<Object> hmget(String key, String...fileds){
		return viyBasicCache.hmget(getPreKey()+key, fileds);
	}
	@Override
	public Map<String,?> hgetAll(String key){
		return viyBasicCache.hgetAll(getPreKey()+key);
	}
	@Override
	public Long hdel(String key,String field){
		return viyBasicCache.hdel(getPreKey()+key, field);
	}

}
