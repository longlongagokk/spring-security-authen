package com.vitily.service;

import com.vitily.common.module.BaseEntity;
import com.vitily.common.module.TvPageList;

import java.util.List;

public interface BasicService<T extends BaseEntity,S,V extends T> {

	TvPageList getPagingList(S bean);
	Integer getCountPaging(S bean);
	List<V> getListByBean(S bean);
	T getMaxBean(S bean);
	T selectByPrimaryKey(int id);
	V selectByPrimaryKeyForTv(int id);
	V selectByPrimaryKeyForTvOfNoCheck(int id);
	
	void sort(T bean, List<Integer> ids, List<Double> sorts);
	void updateDeltag(T bean, List<Integer> ids, int delTag);
	int insert(S bean);
	int update(S bean);
	V selectSingleByBean(S bean);
}
