package com.vitily.mapper;
import java.util.List;

public interface BaseMapper<T,S,V> {

    void deleteByPrimaryKey(Integer id);

	/**
	 * 不判断属性直接插入
	 * @param bean 实体
	 * @return 影响行数：1
	 */
	int insert(T bean);
	Integer getCountPaging(S bean);
	
	List<V> getListByBean(S bean);
	
	T selectByPrimaryKey(int id);
	/**
	 * 插入数据并返回影响行数：判断是否为null
	 * 注意返回的是影响的行数，而主键id已经保存在bean.getId()里了。
	 * @param bean 实体
	 * @return 影响行数
	 */
	int insertSelective(T bean);

	/**
	 * 判断属性是否为null后更新
	 * @param bean 1
	 * @return 影响行数
	 */
	int updateByPrimaryKeySelective(T bean);

	/**
	 * 不判断直接更新
	 * @param bean 实体
	 * @return 返回影响次数
	 */
	int updateByPrimaryKey(T bean);
	T getMaxBean(S bean);
	V selectByPrimaryKeyForTv(int id);
	boolean isExists(S bean);
	V selectSingleByBean(S bean);
}
