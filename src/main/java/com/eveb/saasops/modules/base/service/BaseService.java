package com.eveb.saasops.modules.base.service;

import com.eveb.saasops.modules.base.dao.MyMapper;
import com.eveb.saasops.modules.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class BaseService<D extends MyMapper<T>, T> {

	@Autowired
	protected D mapper;

	public int insertSelective(T entity) {
		return mapper.insertSelective(entity);
	}

	public int delete(T entity) {
		return mapper.deleteByPrimaryKey(entity);
	}

	public int deleteById(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	public int update(T entity) {
		return mapper.updateByPrimaryKeySelective(entity);
	}
    public int update(T entity,Object example)
	{
		return mapper.updateByExampleSelective(entity,example);
	}
	public T queryObject(Integer key) {
		return mapper.selectByPrimaryKey(key);
	}

	public T queryObjectCond(T entity) {
		return mapper.selectOne(entity);
	}

	public List<T> queryList() {
		return mapper.selectAll();
	}

	public List<T> queryListCond(T entity) {
		return mapper.select(entity);
	}

	public int selectCount(T entity)
	{
		return mapper.selectCount(entity);
	}

	protected SysUserEntity getUser() {
		return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
	}

	protected Long getUserId() {
		return getUser().getUserId();
	}

}