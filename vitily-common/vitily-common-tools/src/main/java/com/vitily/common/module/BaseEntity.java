package com.vitily.common.module;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vitily.common.util.DateUtil;
import com.vitily.common.util.serializer.Scale2DoubleSerializer;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	@JsonFormat(pattern = DateUtil.JSONOUTPUT_yyyy_MM_dd_HH_mm_ss_EN,timezone = DateUtil.timeZone)
	private Date createDate;

	@JsonFormat(pattern = DateUtil.JSONOUTPUT_yyyy_MM_dd_HH_mm_ss_EN,timezone = DateUtil.timeZone)
	private Date updateDate;
	@JsonIgnore
	private Integer deltag;
	@JsonSerialize(using = Scale2DoubleSerializer.class)
	private Double sort;
	
	private Integer state;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getDeltag() {
		return deltag;
	}

	public void setDeltag(Integer deltag) {
		this.deltag = deltag;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}
