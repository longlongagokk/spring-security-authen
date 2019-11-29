package com.vitily.common.module;

import com.vitily.common.consts.CommonEnumContainer;

import java.io.Serializable;
import java.util.List;

public class BaseSearch<T extends BaseEntity> implements Serializable {
	public BaseSearch(T entity){
		this.entity = entity;
		this.entity.setDeltag(CommonEnumContainer.Deltag.正常.getValue());
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PageInfo pageInfo;
	private String beginCreateDate;
	private String endCreateDate;
	private String beginUpdateDate;
	private String endUpdateDate;
	
	private T entity;
	private List<Integer> ids;
	private List<Integer> states;

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public String getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(String beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}

	public String getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(String endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public String getBeginUpdateDate() {
		return beginUpdateDate;
	}

	public void setBeginUpdateDate(String beginUpdateDate) {
		this.beginUpdateDate = beginUpdateDate;
	}

	public String getEndUpdateDate() {
		return endUpdateDate;
	}

	public void setEndUpdateDate(String endUpdateDate) {
		this.endUpdateDate = endUpdateDate;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public List<Integer> getStates() {
		return states;
	}

	public void setStates(List<Integer> states) {
		this.states = states;
	}
	public <S extends BaseSearch> S valueOf(T entity){
		this.setEntity(entity);
		return (S)this;
	}
	public static <T extends BaseEntity,S extends BaseSearch<T>> S valueOf(T entity,Class<S> sClass)throws Exception{
		S s = sClass.newInstance();
		s.setEntity(entity);
		return s;
	}
}
