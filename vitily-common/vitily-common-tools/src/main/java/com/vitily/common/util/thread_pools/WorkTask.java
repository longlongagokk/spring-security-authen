package com.vitily.common.util.thread_pools;

public abstract class WorkTask implements Runnable {
    private String workId;
    private long initialDelay;
    private long delayOfMillSeconds;

    /**
     *
     * @param workId
     * @param initialDelay 第一次执行毫秒时间（距离距离现在）
     * @param delayOfMillSeconds 之后每个多少毫秒执行一次
     */
    public WorkTask(String workId, long initialDelay, long delayOfMillSeconds){
        this.workId = workId;
        this.initialDelay = initialDelay;
        this.delayOfMillSeconds = delayOfMillSeconds;
    }
    public long getInitialDelay() {
        return initialDelay;
    }

    public long getDelayOfMillSeconds() {
        return delayOfMillSeconds;
    }

    public void setDelayOfMillSeconds(long delayOfMillSeconds) {
        this.delayOfMillSeconds = delayOfMillSeconds;
    }

    public String getWorkId() {
        return workId;
    }
}
