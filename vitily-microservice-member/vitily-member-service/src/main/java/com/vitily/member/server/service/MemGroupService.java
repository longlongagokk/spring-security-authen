package com.vitily.member.server.service;

import com.vitily.member.module.entity.TbMemGroup;
import com.vitily.member.module.search.TsMemGroup;
import com.vitily.member.module.view.TvGroupHasResources;
import com.vitily.member.module.view.TvMemGroup;
import com.vitily.service.BasicService;

import java.util.List;

public interface MemGroupService extends BasicService<TbMemGroup,TsMemGroup,TvMemGroup> {
    List<TvGroupHasResources> getOwnResources(TsMemGroup group);
}