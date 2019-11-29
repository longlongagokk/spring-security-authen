package com.vitily.cache.base.impl;

import com.vitily.cache.SerializeUtil;
import com.vitily.cache.base.ViyBasicCache;
import com.vitily.cache.base.ViyCacheListener;
import com.vitily.common.consts.CommonEnumContainer;
import com.vitily.common.exception.CustomerException;
import com.vitily.common.util.CommonUtil;
import com.vitily.common.util.JSONUtil;
import com.vitily.common.util.thread_pools.ThreadExecutorPool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.*;

import javax.annotation.PreDestroy;
import java.util.*;

/**
 * jedis 操作 redis 类：集群模式
 * @author lether
 *
 */
@Slf4j
public class JedisPoolOfJsonImpl implements ViyBasicCache {
	private JedisPool pool;
    private static volatile ViyBasicCache viyBasicCache;
    private ThreadExecutorPool executor;
    JedisPoolOfJsonImpl(JedisPoolConfig pconfig, RedisConnectConfig cconfig, ThreadExecutorPool executor){
        pool = new JedisPool(pconfig,
                cconfig.getHost(),
                cconfig.getPort(),
                cconfig.getSoTimeout(),
                cconfig.getPassword(),
                cconfig.getDatabase(),
                null
        );
        this.executor = executor;
    }
    public static ViyBasicCache getInstance(JedisPoolConfig pconfig, RedisConnectConfig cconfig,ThreadExecutorPool executor){
        if(CommonUtil.isNull(viyBasicCache)){
            viyBasicCache = new JedisPoolOfJsonImpl(pconfig,cconfig,executor);
        }
        return viyBasicCache;
    }
    @PreDestroy
    public void destroy(){
        pool.close();
    }
	/**
	 * 上锁的话不用分布式锁，只在一台机子上锁
	 * @param key
	 * @param value
	 * @param isLock
	 * @return
	 */
	public boolean put(String key,Object value,boolean isLock) {
		if(CommonUtil.isNull(value)){
			return false;
		}
        try(Jedis jedis = pool.getResource()) {
            if (isLock) {
                jedis.setnx(key, JSONUtil.toJSONString(value));
            } else {
                jedis.set(key, JSONUtil.toJSONString(value));
            }
        }
		return true;
	}
    /**
     * 配置缓存数据
     * @param key
     * @param value: 如果不是string，必须是可以序列化的,不允许存储null
     * @param
     */
    public boolean put(String key,Object value,int seconds,boolean isLock){
        if(CommonUtil.isNull(value)){
            return false;
        }
        try(Jedis jedis = pool.getResource()) {
            jedis.setex(key, seconds, JSONUtil.toJSONString(value));
        }
        return true;
    }
    /**
     * 获取缓存中数据
     * @param key
     * @return
     */
    public Object get(String key){
        try(Jedis jedis = pool.getResource()) {
            return jedis.get(key);
        }
    }
	public long ttl(String key){
        try(Jedis jedis = pool.getResource()) {
            return jedis.ttl(key);
        }
    }
    public boolean update(String key,Object value,boolean isLock){
        if(CommonUtil.isNull(value)){
            return false;
        }
        try(Jedis jedis = pool.getResource()) {
            long seconds =  jedis.ttl(key);
            if(seconds < -1){
                return false;
            }
            jedis.setex(key, (int)seconds, JSONUtil.toJSONString(value));
        }
        return true;
    }
	public boolean remove(String key){
        try(Jedis jedis = pool.getResource()) {
            return jedis.del(key) > 0;
        }
	}
	public boolean exists(String key){
        try(Jedis jedis = pool.getResource()){
            return jedis.exists(key.getBytes());
        }
	}
	public Long lpush(String key,Object value){
		if(CommonUtil.isNotNull(value)){
            try(Jedis jedis = pool.getResource()) {
                return jedis.lpush(key, JSONUtil.toJSONString(value));
            }
		}
		return 0L;
	}
	public List<Object> lrange(String key,long start,long end){
        List<Object> res = new ArrayList<>();
        try(Jedis jedis = pool.getResource()) {
            List<String> lst= jedis.lrange(key,start,end);
            for(String s:lst){
                res.add(s);
            }
        }
        return res;
    }
    public Object lpop(String key){
        try(Jedis jedis = pool.getResource()) {
            return jedis.lpop(key);
        }
    }
    public long llen(String key){
        try(Jedis jedis = pool.getResource()) {
            return jedis.llen(key);
        }
    }
	public Object rpop(String key){
        try(Jedis jedis = pool.getResource()) {
            return jedis.rpop(key);
        }
	}
	public Long hset(String key,String field,Object value){
        try(Jedis jedis = pool.getResource()) {
            return jedis.hset(key, field, JSONUtil.toJSONString(value));
        }
	}
	public String hmset(String key,Map<String,?> map){
		Map<String,String> bmap = new HashMap<>();
		for(Map.Entry<String, ?> item:map.entrySet()){
			bmap.put(item.getKey(), JSONUtil.toJSONString(item.getValue()));
		}
        try(Jedis jedis = pool.getResource()) {
            return jedis.hmset(key,bmap);
        }
	}
	public Object hget(String key,String field){
        try(Jedis jedis = pool.getResource()) {
            return jedis.hget(key, field);
        }
	}
	public List<Object> hmget(String key,String...fields){
        List<String> blist;
        try(Jedis jedis = pool.getResource()){
            blist = jedis.hmget(key,fields);
        }
        List<Object> olist = new ArrayList<>();
        if(blist != null){
            for(String bt:blist){
                olist.add(bt);
            }
        }
		return olist;
	}
	public Map<String,?> hgetAll(String key){
        try(Jedis jedis = pool.getResource()){
            return jedis.hgetAll(key);
        }
	}
    public long hlen(String key){
        try(Jedis jedis = pool.getResource()){
            return jedis.hlen(key.getBytes());
        }
    }
	public Long hdel(String key,String field){
        try(Jedis jedis = pool.getResource()){
            return jedis.hdel(key,field);
        }
	}

    private final static String MTO_PRE_KEY = "MTO_PUB_SUB:";
    private final static String MTM_PRE_KEY = "MTM_PUB_SUB:";
    @Override
    public Long publishMulToOne(final String topic, final String message){
        if(CommonUtil.isNull(message)){
            throw new CustomerException("消息不能为空！", CommonEnumContainer.ResultStatus.参数不全);
        }
        final String _topic = MTO_PRE_KEY+topic;
        executor.execute(()-> {
            try(Jedis jedis = pool.getResource()){
                jedis.publish(_topic, message);
            }
        });
        return 1L;
    }
    @Override
    public void subscribeMulToOne(final String topic, final ViyCacheListener listener){
        JedisPubSub pubSub = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                listener.run(message);
            }
        };
        final String _topic = MTO_PRE_KEY+topic;
        executor.execute(() -> {
            try {
                Thread.sleep(new Random().nextInt(50) * 100L);
            }catch (Exception ex){
                log.warn(ex.getMessage(),ex);
            }
            final Jedis jedis = pool.getResource();
            List<String> lst = jedis.pubsubChannels(_topic);
            //已有订阅，退出
            if(!lst.isEmpty()){
                return;
            }
            log.info("subscribe topic[mto]:" + _topic);
            jedis.subscribe(pubSub, _topic);
        },()->{
            log.info("unSubscribe topic[mto]:" + _topic);
            pubSub.unsubscribe(_topic);
        });
    }
    @Override
    public void publishMulToMul(final String topic,final String message){
        if(CommonUtil.isNull(message)){
            throw new CustomerException("消息不能为空！", CommonEnumContainer.ResultStatus.参数不全);
        }
        final String _topic = MTM_PRE_KEY+topic;
        executor.execute(() -> {
            try(Jedis jedis = pool.getResource()){
                jedis.publish(_topic,message);
            }
        });
    }

    /**
     * 订阅的生活会阻塞线程池，这样导致应用无法进行资源回收，因此
     * 当线程池关闭的之前应该先调用取消订阅
     * @param topic
     * @param listener
     */
    @Override
    public void subscribeMulToMul(final String topic,final ViyCacheListener listener){
        JedisPubSub pubSub = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                listener.run(message);
            }
        };
        final String _topic = MTM_PRE_KEY+topic;
        executor.execute(() -> {
            final Jedis jedis = pool.getResource();
            log.info("subscribe topic[mtm]:" + _topic);
            jedis.subscribe(pubSub, _topic);
        }, ()-> {
            log.info("unSubscribe topic[mtm]:" + _topic);
            pubSub.unsubscribe(_topic);
        });
    }

    /**
     * long > 0 success
     * @param key
     * @param score
     * @param member
     * @return
     * @
     */
    public long zadd(String key,Double score,String member){
        long result;
        try(Jedis jedis = pool.getResource()){
            result = jedis.zadd(key,score,member);
        }
        return result;
    }

    /**
     * long > 0 success
     * @param key
     * @param members
     * @return
     */
    public long zremove(String key,String... members){
        long result;
        try(Jedis jedis = pool.getResource()){
            result = jedis.zrem(key,members);
        }
        return result;
    }
    public long zremrangeByScore(String key,double start,double end){
        long result;
        try(Jedis jedis = pool.getResource()){
            result = jedis.zremrangeByScore(key,start,end);
        }
        return result;
    }
    public Double zscore(String key,String member){
        Double score;
        try(Jedis jedis = pool.getResource()){
            score = jedis.zscore(key,member);
        }
        return score;
    }

    /**
     * 取出分值内的数据，低到高
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set<Tuple> zrangeByScore(String key,double min,double max){
        try(Jedis jedis = pool.getResource()){
            return jedis.zrangeByScoreWithScores(key,min,max);
        }
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
        try(Jedis jedis = pool.getResource()){
            return jedis.zrevrangeByScoreWithScores(key,max,min);
        }
    }
    /**
     * 分数从高到低取出指定数量的成员,按序号取
     * @param key
     * @param start
     * @param stop
     * @return
     */
    public Set<Tuple> zrevrangeWithScores(String key,long start,long stop){
        try(Jedis jedis = pool.getResource()){
            return jedis.zrevrangeWithScores(key,start,stop);
        }
    }
    /**
     * 分数从低到高取出指定数量的成员,按序号取
     * @param key
     * @param start
     * @param stop
     * @return
     */
    public Set<Tuple> zrangeWithScores(String key,long start,long stop){
        try(Jedis jedis = pool.getResource()){
            return jedis.zrangeWithScores(key,start,stop);
        }
    }
}
