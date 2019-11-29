package com.vitily.cache.service.impl;

import com.vitily.cache.base.ViyBasicCache;
import com.vitily.cache.foreign.TbDictionary;
import com.vitily.cache.service.DictionaryCache;
import com.vitily.common.consts.CommonEnumContainer;
import com.vitily.common.consts.DictionaryKey;
import com.vitily.common.util.CommonUtil;
import com.vitily.common.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统字典
 *
 * @author lether
 *
 */
@Component
@Slf4j
public final class DictionaryCacheImpl extends BaseCacheImpl implements DictionaryCache {
	private static volatile Map<String,String> dics = new HashMap <>();

	@Autowired
	private DictionaryCacheImpl(ViyBasicCache viyBasicCache){
		//第三个参数必须为null
		super(DictionaryKey.Keys.系统字典_NOSQL.getValue(),60 * 60 * 24 * 30 * 12,null,viyBasicCache,"");
	}

	/**
	 * 后台跟api服务器redis一个集群：下直接从集群中获取
	 */
	@Override
	public HashMap<String, String> getPubDicHashMap() {
		return new HashMap(dics);
	}

	/**
	 * 字典设置：
	 */
	@Override
	public String setDictionary(TbDictionary entity) {
		viyBasicCache.publishMulToMul(DictionaryKey.ViyCacheSubstrTopic.更新字典.getValue(), JSONUtil.toJSONString(entity));
		return CommonEnumContainer.ResultStatus.正常.getValue();
	}

	/**
	 * 本地监听更新
	 */
	@Override
	public String ListenDictionary(Map<String, String> map) {
		viyBasicCache.subscribeMulToMul(DictionaryKey.ViyCacheSubstrTopic.更新字典.getValue(), receiveMessage -> {
            TbDictionary dictionary = JSONUtil.parseObject(receiveMessage,TbDictionary.class);
            if(CommonUtil.isNotNull(dictionary)){
                if(CommonUtil.isEqual(CommonEnumContainer.State.正常.getValue(),dictionary.getState())){
                    log.info("update or add dictionary.");
                    dics.put(dictionary.getKey(),dictionary.getValue());
                }else{
                    log.info("delete dictionary.");
                    dics.remove(dictionary.getKey());
                }
            }
        });
		return "1";
	}

	/**
	 * 后台跟api服务器redis一个集群：下直接从集群中获取
	 */
	@Override
	public String getValue(String dictionaryKey) {
		return dics.get(dictionaryKey);
	}

	/**
	 * 删除一个字典：客户端无权限
	 * @param dictionaryKey 3
	 * @return 1
	 */
	public String removeKey(String dictionaryKey) {
		dics.remove(dictionaryKey);
		return "ok";
	}

	/**
	 * 初始化，每次初始化将本地的 dics 重新定位
	 * @param list 1
	 * @ x
	 */
	@Override
	public void init(List<TbDictionary> list) {
		dics = new HashMap <>();
        for(TbDictionary item : list){
            dics.put(item.getKey(), item.getValue());
        }
		ListenDictionary(null);
	}
}
