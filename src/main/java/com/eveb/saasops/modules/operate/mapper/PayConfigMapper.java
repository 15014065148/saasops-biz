package com.eveb.saasops.modules.operate.mapper;

import com.eveb.saasops.modules.operate.entity.TOpPayConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PayConfigMapper {

    /**
     *  查询api配置列表
     *
     * @param payConfig
     * @return
     */
    List<TOpPayConfig> queryPayListPage(TOpPayConfig payConfig);

    /**
     * 站点下关闭或开启支付配置
     * @param relateId
     * @param available
     * @return
     */
    int closeOrOpen(@Param("relateId") Integer relateId, @Param("available") Integer available);

    /**
     * 通过ids删除支付配置
     * @param list
     * @return
     */
    int deleteByIds(@Param("list") List list);

}
