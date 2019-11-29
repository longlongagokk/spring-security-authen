package com.vitily.member.server.service.impl;

import com.vitily.common.consts.CommonEnumContainer;
import com.vitily.common.util.CommonUtil;
import com.vitily.member.module.entity.TbMemGroup;
import com.vitily.member.module.search.TsMemGroup;
import com.vitily.member.module.view.TvGroupHasResources;
import com.vitily.member.module.view.TvMemGroup;
import com.vitily.member.server.mapper.MemGroupMapper;
import com.vitily.member.server.service.MemGroupService;
import com.vitily.service.impl.BasicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MemGroupServiceImpl extends BasicServiceImpl<TbMemGroup,TsMemGroup,TvMemGroup> implements MemGroupService {

	@Autowired()
	public MemGroupServiceImpl(MemGroupMapper mapper) {
		super(mapper,true);
	}

	@Override
	protected final void beforeInsert(TsMemGroup bean){
		updateOtherByCheck(bean.getEntity());
	}
	@Override
	protected final void beforeUpdate(TsMemGroup bean){
		updateOtherByCheck(bean.getEntity());
	}
	@Override
	public void updateDeltag(TbMemGroup bean, List<Integer> ids, int delTag){
		for (Integer id : ids) {
			bean.setId(id);
			bean.setDeltag(delTag);
			bean.setState(null);
			bean.setUpdateDate(new Date());
			mapper.updateByPrimaryKeySelective(bean);
			//通知商品系统，删除会员商品的会员价
		}
	}

	private void updateOtherByCheck(TbMemGroup entity){
		//如果是默认的则更新其他默认组为非默认组
		TsMemGroup upOtherBean = new TsMemGroup();
		if(CommonUtil.isEqual(CommonEnumContainer.Default.是.getValue(), entity.getIsDefault())){
			upOtherBean.getEntity().setName(null);
			upOtherBean.getEntity().setDeltag(null);
			upOtherBean.getEntity().setId(0);
			upOtherBean.setDefaultStr(String.valueOf(CommonEnumContainer.Default.是.getValue()));
			upOtherBean.getEntity().setIsDefault(CommonEnumContainer.Default.否.getValue());
			((MemGroupMapper)mapper).updateByBean(upOtherBean);
		}
	}
	@Override
	public List<TvGroupHasResources> getOwnResources(TsMemGroup group){
		List<TvGroupHasResources> groupHasResources = ((MemGroupMapper)mapper).getOwnResources();
		return groupHasResources;
	}
}