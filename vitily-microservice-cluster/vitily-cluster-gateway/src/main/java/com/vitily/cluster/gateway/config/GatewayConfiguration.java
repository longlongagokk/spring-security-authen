package com.vitily.cluster.gateway.config;

import com.vitily.cache.base.ViyBasicCache;
import com.vitily.cache.base.impl.*;
import com.vitily.common.util.CommonUtil;
import com.vitily.common.util.SpringContextUtil;
import com.vitily.common.util.thread_pools.ScheduledExecutorPool;
import com.vitily.common.util.thread_pools.ThreadExecutorPool;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.scheduling.annotation.EnableAsync;
import redis.clients.jedis.JedisPoolConfig;

/**
 * creator : whh-lether
 * date    : 2019/6/26 18:37
 * desc    : bean注入
 **/
@Configuration
@EnableAsync
//@EnableAspectJAutoProxy
@ComponentScan({"com.vitily.cache.service"})
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class GatewayConfiguration {
    @Bean
    public SpringContextUtil springContextUtil(){
        return new SpringContextUtil();
    }
    @Bean
    @ConfigurationProperties(prefix = "spring.ehcache")
    EhCacheConfig ehCacheConfig(){
        return new EhCacheConfig();
    }
    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    RedisConnectConfig redisConnectConfig(){
        RedisConnectConfig e = new RedisConnectConfig();
        return e;
    }
    @Bean
    @ConfigurationProperties(prefix = "spring.redis.pool")
    JedisPoolConfig jedisPoolConfig(){
        return new JedisPoolConfig();
    }
    @Bean
    public ThreadExecutorPool executor(
            @Value("${executor.corePoolSize}")Integer corePoolSize,
            @Value("${executor.maxPoolSize}")Integer maxPoolSize,
            @Value("${executor.keepAliveSeconds}")Integer keepAliveSeconds,
            @Value("${executor.awaitTerminationSeconds}")Integer awaitTerminationSeconds
    ){
        ThreadExecutorPool executor = ThreadExecutorPool.getInstance();
        executor.init(corePoolSize,maxPoolSize,keepAliveSeconds,awaitTerminationSeconds,"viy-pool-");
        return executor;
    }
    @Bean
    public ScheduledExecutorPool scheduledExecutorPool(){
        return ScheduledExecutorPool.getInstance();
    }


    @Value("${server.cacheFrame}")
    String cacheFrame;
    @Bean
    public ViyBasicCache viyBasicCache(@Qualifier("executor") ThreadExecutorPool executor,
                                       @Qualifier("jedisPoolConfig")JedisPoolConfig JedisPoolConfig,
                                       @Qualifier("redisConnectConfig")RedisConnectConfig redisConnectConfig,
                                       @Qualifier("ehCacheConfig")EhCacheConfig ehCacheConfig
    ){
        return CommonUtil.isEqual("redis", cacheFrame) ?
                JedisPoolOfJsonImpl.getInstance(JedisPoolConfig,redisConnectConfig,executor) : EhcacheImpl.getInstance(ehCacheConfig,executor);
    }
}
