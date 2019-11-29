package com.vitily.cache.service;

import com.vitily.common.consts.DictionaryKey;

/**
 * 公共缓存：判断是否存在的依据
 * @author lether
 *
 */
public interface CommonServiceCache extends BaseCache {
	CommonServiceCache getInstance(DictionaryKey.Keys key);
}
