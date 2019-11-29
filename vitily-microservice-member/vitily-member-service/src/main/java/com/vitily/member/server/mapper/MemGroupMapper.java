package com.vitily.member.server.mapper;

import com.vitily.mapper.BaseMapper;
import com.vitily.member.module.entity.TbMemGroup;
import com.vitily.member.module.search.TsMemGroup;
import com.vitily.member.module.view.TvGroupHasResources;
import com.vitily.member.module.view.TvMemGroup;

import java.util.List;

public interface MemGroupMapper extends BaseMapper<TbMemGroup,TsMemGroup,TvMemGroup> {
	void updateByBean(TsMemGroup bean);
	List<TvGroupHasResources> getOwnResources();
}
