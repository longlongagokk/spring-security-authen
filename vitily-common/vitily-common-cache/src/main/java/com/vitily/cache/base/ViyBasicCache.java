package com.vitily.cache.base;

import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ViyBasicCache {
    boolean put(String key, Object value, boolean isLock);
    boolean put(String key, Object value, int seconds, boolean isLock);
    Object get(String key);
    long ttl(String key);
    boolean update(String key, Object value, boolean isLock);
    boolean remove(String key);
    boolean exists(String key);
    Long lpush(String key, Object value);
    Object lpop(String key);
    long llen(String key);
    List<Object> lrange(String key, long start, long end);
    Object rpop(String key);
    Long hset(String key, String field, Object value);
    String hmset(String key, Map<String, ?> map);
    Object hget(String key, String field);
    List<Object> hmget(String key, String... fields);
    Map<String,?> hgetAll(String key);
    long hlen(String key);
    Long hdel(String key, String field);

    /**
     *  多发布单订阅:适用于日志收集，不适用于集群状态发布
     * @param topic
     * @param message
     * @return
     * @
     */
    Long publishMulToOne(String topic, String message);
    void publishMulToMul(String topic, String message);

    /**
     * 多发布单订阅
     * @param topic
     * @param listener
     */
    void subscribeMulToOne(final String topic, final ViyCacheListener listener);
    void subscribeMulToMul(final String topic, final ViyCacheListener listener);


    /**
     * long > 0 success
     * @param key
     * @param score
     * @param member
     * @return
     * @
     */
    long zadd(String key, Double score, String member);
    /**
     * long > 0 success
     * @param key
     * @param members
     * @return
     */
    long zremove(String key, String... members);
    long zremrangeByScore(String key, double start, double end);

    /**
     * 获取指定成员的分数
     * @param key
     * @param member
     * @return
     */
    Double zscore(String key, String member);
    /**
     * 取出分值内的数据,低到高
     * @param key
     * @param min
     * @param max
     * @return
     */
    Set<Tuple> zrangeByScore(String key, double min, double max);

    /**
     * 取出分值内的数据,高到低
     * @param key
     * @param min
     * @param max
     * @return
     */
    Set<Tuple> zrevrangeByScore(String key, double min, double max);

    /**
     * 分数从高到低取出指定数量的成员
     * @param key
     * @param start
     * @param stop
     * @return
     */
    Set<Tuple> zrevrangeWithScores(String key, long start, long stop);
    /**
     * 分数从低到高取出指定数量的成员
     * @param key
     * @param start
     * @param stop
     * @return
     */
    Set<Tuple> zrangeWithScores(String key, long start, long stop);

}
