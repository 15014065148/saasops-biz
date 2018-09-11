package com.eveb.saasops.modules.game.service;

import com.eveb.saasops.common.utils.BeanUtil;
import com.eveb.saasops.common.utils.PageUtils;
import com.eveb.saasops.common.validator.Assert;
import com.eveb.saasops.modules.base.service.BaseService;
import com.eveb.saasops.modules.game.dao.TGmApiMapper;
import com.eveb.saasops.modules.game.dao.TGmDepotcatMapper;
import com.eveb.saasops.modules.game.entity.TGmApi;
import com.eveb.saasops.modules.game.entity.TGmDepotcat;
import com.eveb.saasops.modules.game.entity.TGmGame;
import com.eveb.saasops.modules.game.mapper.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.eveb.saasops.common.utils.DateUtil.FORMAT_18_DATE_TIME;
import static com.eveb.saasops.common.utils.DateUtil.getCurrentDate;


@Service
public class TGmApiService extends BaseService<TGmApiMapper, TGmApi> {
    @Autowired
    private GameMapper gameMapper;
    @Autowired
    private TGmApiMapper tGmApiMapper;
    @Autowired
    private TGmDepotcatMapper tGmDepotcatMapper;

    public PageUtils queryListPage(TGmApi tGmApi, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        PageHelper.orderBy("createTime desc");
        return BeanUtil.toPagedResult(gameMapper.listTGmApi(tGmApi));
    }

    public PageUtils getApiBySiteCode(String siteCode, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(gameMapper.getApiBySiteCode(siteCode));
    }

    public PageUtils queryApiListPage(String siteCode, String apiName, Integer available, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(gameMapper.queryApiListPage(siteCode, apiName, available));
    }

    public int save(TGmApi tGmApi) {
        tGmApi.setCreateUser(getUser().getUsername());
        tGmApi.setCreateTime(getCurrentDate(FORMAT_18_DATE_TIME));
        judgeIsRepetition(tGmApi);
        isNullSaveOrUpdate(tGmApi);
        return tGmApiMapper.insertSelective(tGmApi);
    }

    /**
     * 查询平台与分类的中间表及强制添加关联关系和不能添加重复数据
     *
     * @param tGmApi
     */
    public void judgeIsRepetition(TGmApi tGmApi) {
        List<TGmDepotcat> tGmDepotcatList = tGmDepotcatMapper.selectAll();
        tGmDepotcatList.stream().forEach(ls -> {
            if (ls.getCatId().equals(tGmApi.getCatId())
                    && ls.getDepotId().equals(tGmApi.getDepotId())) {
            } else {
                TGmDepotcat tGmDepotcat = new TGmDepotcat();
                tGmDepotcat.setCatId(tGmApi.getCatId());
                tGmDepotcat.setDepotId(tGmApi.getDepotId());
                tGmDepotcatMapper.insert(tGmDepotcat);
            }
        });
    }

    public int update(TGmApi tGmApi) {
        tGmApi.setModifyUser(getUser().getUsername());
        tGmApi.setModifyTime(getCurrentDate(FORMAT_18_DATE_TIME));
        judgeIsRepetition(tGmApi);
        isNullSaveOrUpdate(tGmApi);
        return tGmApiMapper.updateByPrimaryKeySelective(tGmApi);
    }

    public int updateAvailable(TGmApi tGmApi) {
        tGmApi.setModifyUser(getUser().getUsername());
        tGmApi.setModifyTime(getCurrentDate(FORMAT_18_DATE_TIME));
        return tGmApiMapper.updateByPrimaryKeySelective(tGmApi);
    }

    public void deleteBatch(TGmApi tGmApi) {
        Assert.isNull(tGmApi, "不能为空");
        String idStr = Arrays.stream(tGmApi.getIds()).boxed().map(i -> i.toString())
                .collect(Collectors.joining(","));
        tGmApiMapper.deleteByIds(idStr);
    }

    private void isNullSaveOrUpdate(@RequestBody TGmApi tGmApi) {
        Assert.isBlank(tGmApi.getMd5Key(), "密钥不能为空");
        Assert.isNull(tGmApi.getSortId(), "排序号不能为空");
        Assert.isBlank(tGmApi.getAgyAcc(), "代理号不能为空");
        Assert.isBlank(tGmApi.getMbUrl(), "移动接口不能为空");
        Assert.isNull(tGmApi.getDepotId(), "游戏平台不能为空");
        Assert.isBlank(tGmApi.getApiName(), "API名称不能为空");
        Assert.isBlank(tGmApi.getPcUrl(), "PC端口接口不能为空");
        Assert.isBlank(tGmApi.getProxyFore(), "代理前缀不能为空");
    }

    public PageUtils queryGameListPage(String apiName, String gameName, String gameTag, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        PageHelper.orderBy("modifyTime desc");
        TGmGame paraGame = new TGmGame();
        paraGame.setGameName(gameName);
        paraGame.setGameTag(gameTag);
        paraGame.setApiName(apiName);
        List<TGmGame> resultList = gameMapper.queryGameListPage(paraGame);
        return BeanUtil.toPagedResult(resultList);
    }

    public int openOrCloseGame(Integer id, Integer status) {
        return gameMapper.openOrCloseGame(id, status);
    }
}
