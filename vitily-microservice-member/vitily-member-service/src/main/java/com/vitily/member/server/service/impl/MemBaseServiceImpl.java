package com.vitily.member.server.service.impl;

import com.vitily.cache.service.ComServiceFrequentCache;
import com.vitily.cache.service.CommonServiceCache;
import com.vitily.cache.service.DictionaryCache;
import com.vitily.common.consts.CommonEnumContainer;
import com.vitily.common.consts.DictionaryKey;
import com.vitily.common.exception.CustomerException;
import com.vitily.common.util.*;
import com.vitily.member.module.entity.TbMemBase;
import com.vitily.member.module.req.TqMemLogin;
import com.vitily.member.module.resp.UserInfo;
import com.vitily.member.module.search.TsMemBase;
import com.vitily.member.module.view.TvMemBase;
import com.vitily.member.server.mapper.*;
import com.vitily.member.server.service.MemBaseService;
import com.vitily.service.impl.BasicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;

@Component
public class MemBaseServiceImpl extends BasicServiceImpl<TbMemBase,TsMemBase,TvMemBase> implements MemBaseService {

	private final DictionaryCache dictionaryCache;
	private final ComServiceFrequentCache comServiceFrequentCache;
	private final CommonServiceCache commonServiceCache;

	@Autowired()
	public MemBaseServiceImpl(MemBaseMapper mapper,
							  DictionaryCache dictionaryCache,
							  ComServiceFrequentCache comServiceFrequentCache,
							  CommonServiceCache commonServiceCache
	) {
		super(mapper,true);
		this.dictionaryCache = dictionaryCache;
		this.comServiceFrequentCache = comServiceFrequentCache;
		this.commonServiceCache = commonServiceCache;
	}

	@Value("${member.token.secret:123456}")
	private String memberTokenSecret;
	@Override
	@Transactional
	public UserInfo getTokenByLogin(TqMemLogin req){

		//判断登陆次数是否已经达到极限
		Integer maxErrorCount= NumberUtil.toInteger(dictionaryCache.getValue(DictionaryKey.Keys.会员登陆尝试次数.getValue()));
		String hashIp = MD5.getMD5(req.getReqIp());
		int ipErrCount = comServiceFrequentCache.getCache(DictionaryKey.MemServiceKeyType.会员登陆尝试次数,hashIp);
		if(CommonUtil.isNotNull(maxErrorCount)){
			if(maxErrorCount.compareTo(ipErrCount) <= 0){
				throw new CustomerException("您今日已经尝试登陆次数过多，请明天此时再尝试！", CommonEnumContainer.ResultStatus.操作异常);
			}
		}
		TbMemBase member = ((MemBaseMapper)mapper).selectByLogin(req);
		if(CommonUtil.isNull(member)){
			throw new CustomerException("登陆信息错误，用户/密码不正确.", CommonEnumContainer.ResultStatus.用户名或密码错误);
		}
		//加密密码对比
		String secPassword= MD5.getMD5(req.getPassword()+member.getSalt());
		if(!secPassword.equals(member.getPassword())){
			comServiceFrequentCache.setToServer(DictionaryKey.MemServiceKeyType.会员登陆尝试次数,hashIp);
			throw new CustomerException("登陆信息错误，用户/密码不正确.", CommonEnumContainer.ResultStatus.用户名或密码错误);
		}
		if(!CommonUtil.isEqual(CommonEnumContainer.State.正常.getValue(),member.getState())){
			comServiceFrequentCache.setToServer(DictionaryKey.MemServiceKeyType.会员登陆尝试次数,hashIp);
			throw new CustomerException("登录信息错误，用户已被禁用,请联系客服.", CommonEnumContainer.ResultStatus.用户已禁用);
		}

		//登陆成功，清除错误次数
		comServiceFrequentCache.removeFromServer(DictionaryKey.MemServiceKeyType.会员登陆尝试次数,hashIp);

		TbMemBase upMember=new TbMemBase();
		upMember.setId(member.getId());
		upMember.setLastLoginDate(new Date());
		mapper.updateByPrimaryKeySelective(upMember);

		//		 //查询反向token
		CommonServiceCache memCache=commonServiceCache.getInstance(DictionaryKey.Keys.会员Token);

		//将登陆token存至缓存里: 接口存的是id
		//
		UserInfo userInfo = new UserInfo();
		userInfo.setExpire(1000);//60*30 = 30分钟
		userInfo.setRoles(Collections.singletonList(String.valueOf(member.getGroupId())));
		userInfo.setUserId(member.getId());
		userInfo.setUsername(member.getName());
		userInfo.setToken(ServiceEncryUtil.getMemberToken(member.getName(),memberTokenSecret));
		memCache.setToServer(userInfo.getToken(),userInfo,userInfo.getExpire());
		return userInfo;
	}
}