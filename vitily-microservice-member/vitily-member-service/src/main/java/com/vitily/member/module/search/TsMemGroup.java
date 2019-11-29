package com.vitily.member.module.search;

import com.vitily.common.module.BaseSearch;
import com.vitily.common.util.CommonUtil;
import com.vitily.member.module.entity.TbMemGroup;


public class TsMemGroup extends BaseSearch<TbMemGroup> {
	public TsMemGroup(){super(new TbMemGroup());}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String defaultStr;
	private Double beginPercent;
	private Double endPercent;
	private String selName;

	public String getDefaultStr() {
		return defaultStr;
	}
	public TsMemGroup setDefaultStr(String defaultStr) {
		if(CommonUtil.isNumOrD(defaultStr)){
			this.defaultStr = defaultStr;
		}
		return this;
	}
	public Double getBeginPercent() {
		return beginPercent;
	}
	public TsMemGroup setBeginPercent(Double beginPercent) {
		this.beginPercent = beginPercent;
		return this;
	}
	public Double getEndPercent() {
		return endPercent;
	}
	public TsMemGroup setEndPercent(Double endPercent) {
		this.endPercent = endPercent;
		return this;
	}
	public String getSelName() {
		return selName;
	}
	public TsMemGroup setSelName(String selName) {
		this.selName = selName;
		return this;
	}
}
