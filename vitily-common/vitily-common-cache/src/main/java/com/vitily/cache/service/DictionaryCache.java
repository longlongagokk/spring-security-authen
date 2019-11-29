package com.vitily.cache.service;

import com.vitily.cache.foreign.TbDictionary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DictionaryCache extends BaseCache {
	/**
	 * 获取 static
	 * 返回的是左值 可以随意更新
	 * @return 1
	 */
	HashMap getPubDicHashMap();
	/**
	 * 单个更新:
	 */
	String setDictionary(TbDictionary dictionary);
	/**
	 * 批量更新
	 * @param map 1
	 * @return 2
	 */
	String ListenDictionary(Map<String, String> map);
	/**
	 * 获得字典值:直接从 map获取
	 * @param dictionaryKey 1
	 */
	String getValue(String dictionaryKey);
	
	/**
	 * 删除一个字典：一般不允许删除字典
	 * @param dicianaryKey 1
	 * @return 2
	 * @ 3
	 */
	String removeKey(String dicianaryKey);
	/**
	 * 初始化字典列表（服务器跟本地一起）
	 * @param list 1
	 */
	void init(List<TbDictionary> list);
}
