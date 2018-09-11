package com.eveb.saasops.modules.game.mapper;

import com.eveb.saasops.modules.game.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GameMapper {
    /**
     * 根据条件查询游戏平台列表
     *
     * @param tGmDepot
     * @return
     */
    List<TGmDepot> listTGmDepot(TGmDepot tGmDepot);

    /**
     * 开启维护
     *
     * @param tGmAnnouncement
     * @return
     */
    int saveAnnouncement(TGmAnnouncement tGmAnnouncement);

    /**
     * 根据条件查询平台维护列表
     *
     * @param tGmAnnouncement
     * @return
     */
    List<TGmAnnouncement> selectAnnouncementList(TGmAnnouncement tGmAnnouncement);

    /**
     * 根据条件查询游戏分類
     *
     * @return
     */
    List<TGmCat> findGameType();

    /**
     * 根据条件查询子分类
     *
     * @return
     */
    List<TGmCat> findSubCat();

    /**
     * 根据条件查询API配置列表
     *
     * @param tGmApi
     * @return
     */
    List<TGmApi> listTGmApi(TGmApi tGmApi);

    /**
     * 通过siteCode查询api
     *
     * @param siteCode
     * @return
     */
    List<TGmApi> getApiBySiteCode(@Param("siteCode") String siteCode);

    /**
     * 根据条件查询API组列表
     *
     * @param tGmCaseDel
     * @return
     */
    List<TGmCaseDel> listTGmCaseDel(TGmCaseDel tGmCaseDel);


    /**
     * 根据条件查询游戏分类列表
     *
     * @param tGmCat
     * @return
     */
    List<TGmCat> listTGmCat(TGmCat tGmCat);

    /**
     * 根据条件查询游戏列表及统计
     *
     * @param tGmGame
     * @return
     */
    List<TGmGame> listTGmGame(TGmGame tGmGame);

    /**
     * 根据条件查询游戏列表(由于多个分类，故多连了几张表)
     *
     * @param tGameLogo
     * @return
     */
    List<TGameLogo> listtGameLogo(TGameLogo tGameLogo);

    /**
     * 根据条件查询游戏列表
     *
     * @param tGmGame
     * @return
     */
    List<TGmGame> findGameList(TGmGame tGmGame);

    /**
     * @return
     */
    List<TGameLogo> listInfo();

    /**
     * 根据条件查询游戏平台
     *
     * @return
     */
    List<TGmDepot> findGameDepot();

    /**
     * 通过gameId删除logo数据
     *
     * @param id
     * @return
     */
    int deleteLogoByGameId(@Param("id") Integer id);

    /**
     * 通过平台id查询列表
     *
     * @param depotLogoId
     * @return
     */
    List<TLabelGameDepot> findTLabelGameDepotId(@Param("depotLogoId") Integer depotLogoId);

    /**
     * 通过平台id查询列表
     *
     * @param gameId
     * @return
     */
    List<TLabelGameDepot> findTLabelGameDepotGameId(@Param("gameId") Integer gameId);

    /**
     * 查询分类下各个平台的游戏总数
     *
     * @param depotId
     * @return
     */
    Integer findTGmGameNums(@Param("depotId") Integer depotId);

    /**
     * 查询分类下各个平台打开游戏总数
     *
     * @param depotId
     * @return
     */
    Integer findTGmGameOpenNums(@Param("depotId") Integer depotId);

    /**
     * 通过api名称查询游戏控制列表
     *
     * @param paraGame
     * @return
     */
    List<TGmGame> queryGameListPage(TGmGame paraGame);

    /**
     * 开启或关闭游戏
     *
     * @param id
     * @return
     */
    int openOrCloseGame(@Param("id") Integer id, @Param("status") Integer status);

    /**
     * 查询当前站点下没有的api线路
     *
     * @param siteCode
     * @param apiName
     * @param available
     * @return
     */
    List<TGmApi> queryApiListPage(@Param("siteCode") String siteCode, @Param("apiName") String apiName, @Param("available") Integer available);
}
