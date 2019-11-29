package com.vitily.cache.service;


import java.util.List;
import java.util.Map;

/**
 * 通用缓存
 * @author lether
 *
 */
public interface BaseCache<T> {
	void setToServer(String key, Object value);
	void setToServer(String key, Object value, Integer seconds);
	Object getFromServer(String key);
	/**
	 * 只更新值，不更新失效时间
	 * @param key
	 * @param value
	 */
	void upToServer(String key, Object value);
	void removeFromServer(String key);
	boolean existsInServer(String key);
	/**
	 * 入列队
	 * @param key 1
	 * @param value 2
	 */
	void lpush(String key, Object value);
	/**
	 * 出列队
	 * @param key 1
	 * @return 2
	 */
	Object rpop(String key);
//=====================map操作=============================//
	Long hset(String key, String field, Object value);
	String hmset(String key, Map<String, ?> map);
	Object hget(String key, String field);
	List<Object> hmget(String key, String... fileds);
	Map<String, ?> hgetAll(String key);
	Long hdel(String key, String field);
}
