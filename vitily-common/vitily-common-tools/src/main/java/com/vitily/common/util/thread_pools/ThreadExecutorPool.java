package com.vitily.common.util.thread_pools;

import com.vitily.common.util.CommonUtil;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 执行普通任务线程池,定时任务不允许使用该类
 */
public class ThreadExecutorPool {
    private static ThreadExecutorPool instance = new ThreadExecutorPool();
    public static ThreadExecutorPool getInstance(){
        return instance;
    }
    private ThreadExecutorPool(){}
    public void init(int corePoolSize,int maxPoolSize,Integer keepAliveSeconds,Integer awaitTerminationSeconds,String poolName){
        _executor = new ThreadPoolTaskExecutor();
        _executor.setCorePoolSize(corePoolSize);
        _executor.setMaxPoolSize(maxPoolSize);
        _executor.setKeepAliveSeconds(keepAliveSeconds);
        _executor.setWaitForTasksToCompleteOnShutdown(true);
        _executor.setAwaitTerminationSeconds(awaitTerminationSeconds);
        _executor.setThreadNamePrefix(poolName);
        _executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        _executor.initialize();
    }
    private ThreadPoolTaskExecutor _executor;
    public ThreadPoolTaskExecutor getPools(){
        return _executor;
    }
    private List<Runnable> beforeShutdownEvents = new ArrayList<>();
    public void execute(Runnable runnable){
       _executor.execute(runnable);
    }
    public void execute(Runnable runnable,Runnable closeRunnable){
        _executor.execute(runnable);
        beforeShutdownEvents.add(closeRunnable);
    }
    @PreDestroy
    public void shutdown0(){
        for(Runnable runnable:beforeShutdownEvents){
            runnable.run();
        }
        if(CommonUtil.isNotNull(_executor)) {
            _executor.shutdown();
            _executor.destroy();
        }
    }
}
