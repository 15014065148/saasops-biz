package com.eveb.saasops.modules.operate.dao;

import com.eveb.saasops.modules.base.dao.MyMapper;
import com.eveb.saasops.modules.operate.entity.TOpPay;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.IdsMapper;


@Mapper
public interface TOpPayMapper extends MyMapper<TOpPay>,IdsMapper<TOpPay> {

}
