package com.vitily.member.server.mapper;

import com.vitily.mapper.BaseMapper;
import com.vitily.member.module.entity.TbMemBase;
import com.vitily.member.module.req.TqMemLogin;
import com.vitily.member.module.search.TsMemBase;
import com.vitily.member.module.view.TvMemBase;

public interface MemBaseMapper extends BaseMapper<TbMemBase,TsMemBase,TvMemBase> {
	TvMemBase selectByLogin(TqMemLogin req);
}