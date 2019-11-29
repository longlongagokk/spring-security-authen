package com.vitily.service.impl;

import com.vitily.common.consts.CommonEnumContainer;
import com.vitily.common.module.BaseEntity;
import com.vitily.common.module.BaseSearch;
import com.vitily.common.module.PageInfo;
import com.vitily.common.module.TvPageList;
import com.vitily.mapper.BaseMapper;
import com.vitily.service.BasicService;
import com.vitily.common.util.Assert;
import com.vitily.common.util.CommonUtil;

import java.util.Date;
import java.util.List;

public abstract class BasicServiceImpl<T extends BaseEntity,S extends BaseSearch<T>,V extends T> implements BasicService<T,S,V> {

	protected final BaseMapper<T,S,V> mapper;
	@SuppressWarnings("WeakerAccess")
	protected final boolean checkAlterExits;
	@SuppressWarnings("WeakerAccess")
	protected final String checkAlterMessage;
    public BasicServiceImpl(BaseMapper<T,S,V> mapper){
		this(mapper,false,null);
    }
	public BasicServiceImpl(BaseMapper<T,S,V> mapper, boolean checkAlterExits){
		this(mapper,checkAlterExits,"要操作的项目在库中已经存在");
	}
	public BasicServiceImpl(BaseMapper<T,S,V> mapper, boolean checkAlterExits, String checkAlterMessage){
		this.mapper=mapper;
		this.checkAlterExits=checkAlterExits;
		this.checkAlterMessage=checkAlterMessage;
	}

	public TvPageList getPagingList(S bean){
		Assert.notNull(bean,"查询实体不能为空");
		PageInfo pageInfo = bean.getPageInfo();
		if(CommonUtil.isNull(pageInfo)){
			pageInfo = new PageInfo();
		}
		bean.setPageInfo(pageInfo);
		pageInfo.setRecordCount(mapper.getCountPaging(bean));
		TvPageList pageList = new TvPageList();
		pageList.setPageInfo(pageInfo);
		pageList.setList(mapper.getListByBean(bean));
		return pageList;
	}
	public Integer getCountPaging(S bean){
		return mapper.getCountPaging(bean);
	}
	public List<V> getListByBean(S bean){
		if(CommonUtil.isNotNull(bean.getPageInfo())){
			bean.getPageInfo().setPageSize(PageInfo.COMMON_MAX_PAGESIZE);
		}
		return mapper.getListByBean(bean); 
	}
	public T getMaxBean(S bean){
		return mapper.getMaxBean(bean);
	}
	public T selectByPrimaryKey(int id) {
		T t = mapper.selectByPrimaryKey(id);
		Assert.exists(t,"实体为空");
		return t;
	}
	protected void beforeInsert(S bean){}
	protected void afterInsert(S bean){}
	public int insert(S bean){
		beforeInsert(bean);
		if(checkAlterExits){
			Assert.isTrue(!mapper.isExists(bean),checkAlterMessage);
		}
		Date now=new Date();
		bean.getEntity().setCreateDate(now);
		bean.getEntity().setDeltag(CommonEnumContainer.Deltag.正常.getValue());
		int res = mapper.insertSelective(bean.getEntity());
		if(res > 0) {
			afterInsert(bean);
		}
		return res;
	}
	protected void beforeUpdate(S bean){}
	protected void afterUpdate(S bean){}
	public int update(S bean){
		beforeUpdate(bean);
		if(checkAlterExits){
			Assert.isTrue(!mapper.isExists(bean),checkAlterMessage);
		}
		bean.getEntity().setUpdateDate(new Date());
		int res = mapper.updateByPrimaryKeySelective(bean.getEntity());
		if(res > 0) {
			afterUpdate(bean);
		}
		return res;
	}
	public V selectByPrimaryKeyForTv(int id){
		V v = mapper.selectByPrimaryKeyForTv(id);
		Assert.exists(v,"实体为空");
		return v;
	}
	public V selectByPrimaryKeyForTvOfNoCheck(int id){
		return mapper.selectByPrimaryKeyForTv(id);
	}
	
	public void sort(T bean,List<Integer> ids,List<Double> sorts){
		Date now = new Date();
		for(int i=0;i<ids.size();++i){
			bean.setId(ids.get(i));
			bean.setSort(sorts.get(i));
			bean.setState(null);
			bean.setDeltag(null);
			bean.setUpdateDate(now);
			mapper.updateByPrimaryKeySelective(bean);
		}
	}
	public void updateDeltag(T bean,List<Integer> ids,int delTag){
		Date now = new Date();
		for (Integer id : ids) {
			bean.setId(id);
			bean.setDeltag(delTag);
			bean.setState(null);
			bean.setUpdateDate(now);
			mapper.updateByPrimaryKeySelective(bean);
		}
	}
	public V selectSingleByBean(S bean){
		return mapper.selectSingleByBean(bean);
	}
}
