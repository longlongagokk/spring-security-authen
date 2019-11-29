package com.vitily.member.server.service;

import com.vitily.member.module.entity.TbMemBase;
import com.vitily.member.module.req.TqMemLogin;
import com.vitily.member.module.resp.UserInfo;
import com.vitily.member.module.search.TsMemBase;
import com.vitily.member.module.view.TvMemBase;
import com.vitily.service.BasicService;

public interface MemBaseService extends BasicService<TbMemBase,TsMemBase,TvMemBase> {
    UserInfo getTokenByLogin(TqMemLogin req);
}