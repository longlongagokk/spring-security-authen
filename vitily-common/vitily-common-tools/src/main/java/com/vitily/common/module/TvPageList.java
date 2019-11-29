package com.vitily.common.module;

import java.io.Serializable;
import java.util.List;

public class TvPageList<T> implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    PageInfo pageInfo;
    List<T> list;
    public PageInfo getPageInfo() {
        return pageInfo;
    }
    public TvPageList<T> setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
        return this;
    }
    public List <T> getList() {
        return list;
    }
    public TvPageList<T> setList(List <T> list) {
        this.list = list;
        return this;
    }
}
