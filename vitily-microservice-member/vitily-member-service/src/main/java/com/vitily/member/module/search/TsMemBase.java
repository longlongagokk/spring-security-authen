package com.vitily.member.module.search;

import com.vitily.common.module.BaseSearch;
import com.vitily.common.util.CommonUtil;
import com.vitily.member.module.entity.TbMemBase;


public class TsMemBase extends BaseSearch<TbMemBase> {
	public TsMemBase(){
		super(new TbMemBase());
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String selName;
	private String email;
	private String qq;
	private String beginLastLoginDate;
	private String endLastLoginDate;
	
	private String typeStr;
	private String groupIdStr;
	private String regFromStr;

	private Boolean selOpenCretidfile;
	public String getSelName() {
		return selName;
	}
	public TsMemBase setSelName(String selName) {
		this.selName = selName;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public TsMemBase setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getQq() {
		return qq;
	}
	public TsMemBase setQq(String qq) {
		this.qq = qq;
		return this;
	}

	public String getTypeStr() {
		return typeStr;
	}

	public TsMemBase setTypeStr(String typeStr) {
		if(CommonUtil.isNumOrD(typeStr)){
			this.typeStr = typeStr;
		}
		return this;
	}

	public String getGroupIdStr() {
		return groupIdStr;
	}
	public TsMemBase setGroupIdStr(String groupIdStr) {
		if(CommonUtil.isNumOrD(groupIdStr)){
			this.groupIdStr = groupIdStr;
		}
		return this;
	}

	public String getRegFromStr() {
		return regFromStr;
	}
	public TsMemBase setRegFromStr(String regFromStr) {
		if(CommonUtil.isNumOrD(regFromStr)){
			this.regFromStr = regFromStr;
		}
		return this;
	}

	public String getBeginLastLoginDate() {
		return beginLastLoginDate;
	}
	public TsMemBase setBeginLastLoginDate(String beginLastLoginDate) {
		this.beginLastLoginDate = beginLastLoginDate;
		return this;
	}
	public String getEndLastLoginDate() {
		return endLastLoginDate;
	}
	public TsMemBase setEndLastLoginDate(String endLastLoginDate) {
		this.endLastLoginDate = endLastLoginDate;
		return this;
	}

	public Boolean getSelOpenCretidfile() {
		return selOpenCretidfile;
	}

	public TsMemBase setSelOpenCretidfile(Boolean selOpenCretidfile) {
		this.selOpenCretidfile = selOpenCretidfile;
		return this;
	}

}
