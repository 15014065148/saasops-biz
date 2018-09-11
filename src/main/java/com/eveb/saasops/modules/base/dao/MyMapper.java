package com.eveb.saasops.modules.base.dao;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface MyMapper<T> extends  MySqlMapper<T>,Mapper<T>,IdsMapper<T> {

}