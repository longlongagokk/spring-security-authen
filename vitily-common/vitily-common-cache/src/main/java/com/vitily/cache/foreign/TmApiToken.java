package com.vitily.cache.foreign;

import java.io.Serializable;

public class TmApiToken implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String apiToken;
    private Integer liveSeconds;

    public String getApiToken() {
        return apiToken;
    }

    public TmApiToken setApiToken(String apiToken) {
        this.apiToken = apiToken;
        return this;
    }

    public Integer getLiveSeconds() {
        return liveSeconds;
    }

    public TmApiToken setLiveSeconds(Integer liveSeconds) {
        this.liveSeconds = liveSeconds;
        return this;
    }
}
