package com.vitily.cache.foreign;

import java.io.Serializable;

public class SMSVerifyCodeFrequent implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int count = 0;
    /**
     * 操作时间
     */
    private long millTime = 0;
    /**
     * 短信内容：仅保存验证码
     */
    private String content;

    /**
     * 错误次数
     */
    private int errorCount=0;

    public int getCount() {
        return count;
    }

    public SMSVerifyCodeFrequent setCount(int count) {
        this.count = count;
        return this;
    }

    public long getMillTime() {
        return millTime;
    }

    public SMSVerifyCodeFrequent setMillTime(long millTime) {
        this.millTime = millTime;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SMSVerifyCodeFrequent setContent(String content) {
        this.content = content;
        return this;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public SMSVerifyCodeFrequent setErrorCount(int errorCount) {
        this.errorCount = errorCount;
        return this;
    }
}
