package com.vitily.cache.base.impl;

import com.vitily.cache.base.ViyBasicCache;
import com.vitily.cache.base.ViyCacheListener;
import com.vitily.common.exception.NoExistsException;
import com.vitily.common.util.CommonUtil;
import com.vitily.common.util.thread_pools.ThreadExecutorPool;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import redis.clients.jedis.Tuple;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by whh 
 */
@Slf4j
public final class EhcacheImpl implements ViyBasicCache {
    private Cache cache;
    private CacheManager manager;
    private static volatile ViyBasicCache ehcacheUtil;
	private ThreadExecutorPool executor;
    EhcacheImpl(EhCacheConfig p, ThreadExecutorPool executor) {
		cache = new Cache(
				p.getName(),
				p.getMaxElementsInMemory(),
				MemoryStoreEvictionPolicy.fromString(p.getMemoryStoreEvictionPolicy()),
				true,
				null,
				false,
				p.getTimeToLiveSeconds(),
				p.getTimeToIdleSeconds(),
				false,
				p.getDiskExpiryThreadIntervalSeconds(),
				null
				);
		manager = CacheManager.create();
        manager.addCache(cache);
		this.executor = executor;
    }

	@PreDestroy
	public void destroy(){
		cache.dispose();
	}
    public static ViyBasicCache getInstance(EhCacheConfig p, ThreadExecutorPool executor) {
        if(CommonUtil.isNull(ehcacheUtil)){
            ehcacheUtil = new EhcacheImpl(p,executor);
        }
        return ehcacheUtil;
    }
    public boolean put(String key,Object value,boolean isLock){
		if(CommonUtil.isNull(key) || CommonUtil.isNull(value)){
			return false;
		}
		Element element = new Element(key,value);
		element.setTimeToLive(60*60*24*365*10);
		cache.put(element);
		return true;
	}
	/**
	 *加入缓存对象:本地缓存
	 *存在更新
	 *注意，已验证:
	 *1,保存的如果是对象那么ehcache存的是对象的引用：
	 *2,但是如果是string等内置类型的话就是保存string了
	 * @param key
	 * @param value
	 * @param second 秒数
	 */
	public boolean put(String key,Object value,int second,boolean isLock){
		if(CommonUtil.isNull(key) || CommonUtil.isNull(value)){
			return false;
		}
		Element element = new Element(key,value);
		element.setTimeToLive(second);
		cache.put(element);
		return true;
	}

	/**
	 * 获取缓存对象
	 *
	 * @param key
	 * @return
	 */
	public Object get(String key){
		if(CommonUtil.isNull(key)){
			return null;
		}
		Element element = cache.get(key);
		return CommonUtil.isNotNull(element) ? element.getValue() : null;
	}

	public long ttl(String key){
		return 0;
	}

    public boolean update(String key,Object value,boolean isLock){
		if(CommonUtil.isNull(key) || CommonUtil.isNull(value)){
			return false;
		}
		Element element = cache.get(key);
		if(CommonUtil.isNull(element)){
			return false;
		}
		Element upEle = new Element(key,value);
		upEle.setTimeToLive(element.getTimeToLive());
		cache.remove(key);//先删
		cache.put(upEle);
		return true;
	}

    //删除缓存对象
    public boolean remove(String key){
		if(CommonUtil.isNull(key)){
            return false;
        }
        log.debug("删除缓存对象 key : "+key );
        return cache.remove(key);
    }
    public boolean exists(String key){
		return CommonUtil.isNotNull(get(key));
	}
    /**
     * 左边入队
     * @param key
     */
    public Long lpush(String key,Object value){
    	BlockingDeque<Object> queue = (BlockingDeque<Object>)get(key);
		if(CommonUtil.isNull(queue)){
    		queue = new LinkedBlockingDeque<>();
        	put(key, queue, 60*60*24*365*10,true);//10年
    	}
    	queue.addFirst(value);
		return 1L;
    }
    public Object lpop(String key){
		BlockingDeque<Object> queue = (BlockingDeque<Object>)get(key);
		if(CommonUtil.isNotNull(queue)){
			return queue.pollFirst();
		}
		return null;
	}
	public long llen(String key){
		BlockingDeque<Object> queue = (BlockingDeque<Object>)get(key);
		return CommonUtil.isNotNull(queue)?queue.size():0;
	}
	public List<Object> lrange(String key,long start,long end){
    	return new ArrayList<>();
	}
    /**
     * 右出队
     * @param key
     * @return
     */
    public Object rpop(String key){
    	BlockingDeque<Object> queue = (BlockingDeque<Object>)get(key);
		if(CommonUtil.isNotNull(queue)){
    		return queue.pollLast();
    	}
    	return null;
    }
    /**
     * hset ：map
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hset(String key,String field,Object value){
		Map<String, Object> map= (Map<String, Object>)get(key);
		if(CommonUtil.isNull(map)){
			map = new ConcurrentHashMap<>();
			put(key, map, 60*60*24*365*10,true);//10年
		}
		map.put(field, value);
    	return 1L;
    }
    public String hmset(String key,Map<String, ?> map){
		put(key, map, 60*60*24*365*10,true);//10年
    	return "1";
    }
	public Object hget(String key,String field){
		Map<String, Object> map= (Map<String, Object>)get(key);
		if(CommonUtil.isNotNull(map)){
			return map.get(field);
		}
		return null;
	}
	public List<Object> hmget(String key,String...fileds){
    	Map<String, Object> map= (Map<String, Object>)get(key);
    	List<Object> list= new ArrayList<>();
        if(CommonUtil.isNotNull(map)){
    		for(String s:fileds){
                Object e = map.get(s);
                if(CommonUtil.isNotNull(e)){
    				list.add(e);
    			}
    		}
    	}
    	return list;
	}
	public Map<String,?> hgetAll(String key){
		return (Map<String,?>)get(key);
	}
	public long hlen(String key){
    	return ((Map<String, Object>)get(key)).size();
	}
	public Long hdel(String key,String field){
		Map<String, Object> map= (Map<String, Object>)get(key);
		if(CommonUtil.isNull(map)){
			throw new NoExistsException("无map在缓存中");
		}
		Object e = map.remove(field);
		return CommonUtil.isNotNull(e) ? 1L : 0L;
	}
	@Override
	public Long publishMulToOne(String topic, String message){
		throw new NoExistsException("ehcache暂未实现消息发送功能");
	}
	@Override
	public void subscribeMulToOne(final String topic, final ViyCacheListener listener){
		log.info("ehcache暂未实现消息接收功能");
	}
	@Override
	public void publishMulToMul(String topic,String message){
		log.info("ehcache暂未实现消息发送功能");
	}
	@Override
	public void subscribeMulToMul(final String topic,final ViyCacheListener listener){
		log.info("ehcache暂未实现消息接收功能");
	}

	@Override
	public long zadd(String key, Double score, String member)  {
		return 0;
	}

	@Override
	public long zremove(String key, String... members) {
		return 0;
	}

	public long zremrangeByScore(String key,double start,double end){
		return 0;
	}

	/**
	 *
	 * @param key
	 * @param member
	 * @return
	 */
	public Double zscore(String key,String member){
		Double score = null;
		return score;
	}

	@Override
	public Set<Tuple> zrangeByScore(String key, double min, double max) {
		return null;
	}
	/**
	 * 取出分值内的数据，高到低
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	@Override
	public Set<Tuple> zrevrangeByScore(String key, double min, double max){
		return null;
	}

	@Override
	public Set<Tuple> zrevrangeWithScores(String key, long start, long stop) {
		return null;
	}

	@Override
	public Set<Tuple> zrangeWithScores(String key, long start, long stop) {
		return null;
	}
}

