package com.vitily.member.module.view;

import com.vitily.common.consts.CommonEnumContainer;
import com.vitily.common.util.CommonUtil;
import com.vitily.common.util.EnumHelperUtil;
import com.vitily.member.module.entity.TbMemGroup;

import java.util.List;

public class TvMemGroup extends TbMemGroup {

	private List<String> urlPatterns;

	public List<String> getUrlPatterns() {
		return urlPatterns;
	}

	public TvMemGroup setUrlPatterns(List<String> urlPatterns) {
		this.urlPatterns = urlPatterns;
		return this;
	}

	/**
	 * 默认状态
	 */
	private String defaultDesc;
	public String getDefaultDesc() {
		if(CommonUtil.isNull(defaultDesc)){
			this.defaultDesc = EnumHelperUtil.getEnumWrapDescByValue(CommonEnumContainer.Default.class,this.getIsDefault());
		}
		return defaultDesc;
	}

	protected String stateDesc;
	public String getStateDesc(){
		this.stateDesc = EnumHelperUtil.getEnumWrapDescByValue(CommonEnumContainer.State.class, this.getState());
		return this.stateDesc;
	}
}
