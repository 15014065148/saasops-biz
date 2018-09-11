package com.eveb.saasops.modules.game.mapper;

import com.eveb.saasops.modules.game.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GamePrefixMapper {

    /**
     *  查询api配置列表
     *
     * @param TGmApiprefix
     * @return
     */
    List<TGmApiprefix> queryListPage(TGmApiprefix TGmApiprefix);

    /**
     * 获取站点下api列表
     * @param siteCode
     * @return
     */
    List<TGmApiprefix> getApiBySiteCode(@Param("siteCode") String siteCode);


    /**
     * 查询站点信息
     * @param siteId
     * @return
     */
    TGmApiprefix querySiteInfo(String siteId);

    /**
     * 通过配置id删除配置
     * @param list
     * @return
     */
    int deleteInfoById(@Param("list") List list);

    /**
     * 停用站点下线路
     * @param id
     * @return
     */
    int closeApi(@Param("id") Integer id, @Param("available") Integer available);
}
