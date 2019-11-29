package com.vitily.common.util;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * creator : whh-lether
 * date    : 2018/11/19 15:27
 * desc    :
 **/
public class SegmentLock<T> {
    private Integer segments = 1<<4;
    private final HashMap<Integer,ReentrantLock> lockMap = new HashMap<>();
    private void init(Integer counts,boolean fair){
        if(null != counts){
            segments = counts;
        }
        for(int i=0;i<segments;++i){
            lockMap.put(i,new ReentrantLock(fair));
        }
    }
    public SegmentLock(){
        init(null,false);
    }
    public SegmentLock(Integer count,boolean fair){
        this.init(null,fair);
    }
    public <T> RtLock getLock(T entry){
        return new RtLock(entry,this);
    }
    public static class RtLock<T>{
        ReentrantLock lock;
        private RtLock(T entry,SegmentLock<T> segmentLock){
            this.lock = segmentLock.lockMap.get((entry.hashCode()>>>1) % segmentLock.segments);
        }
        public void lock(){
            lock.lock();
        }
        public void unlock(){
            lock.unlock();
        }
    }
}
