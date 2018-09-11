package com.eveb.saasops.modules.game.service;

import com.eveb.saasops.common.constants.Constants;
import com.eveb.saasops.common.exception.RRException;
import com.eveb.saasops.common.utils.BeanUtil;
import com.eveb.saasops.common.utils.Collections3;
import com.eveb.saasops.common.utils.PageUtils;
import com.eveb.saasops.common.validator.Assert;
import com.eveb.saasops.modules.base.service.BaseService;
import com.eveb.saasops.modules.game.dao.TGmDepotMapper;
import com.eveb.saasops.modules.game.dao.TGmGameMapper;
import com.eveb.saasops.modules.game.dao.TLabelGameDepotMapper;
import com.eveb.saasops.modules.game.entity.TGameLogo;
import com.eveb.saasops.modules.game.entity.TGmGame;
import com.eveb.saasops.modules.game.entity.TLabelGameDepot;
import com.eveb.saasops.modules.game.mapper.GameMapper;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.eveb.saasops.common.utils.DateUtil.FORMAT_18_DATE_TIME;
import static com.eveb.saasops.common.utils.DateUtil.getCurrentDate;


@Service
public class TGmGameService extends BaseService<TGmGameMapper, TGmGame> {
    @Autowired
    private GameMapper gameMapper;
    @Autowired
    private TGmGameMapper tGmGameMapper;
    @Autowired
    private TLabelGameDepotMapper tLabelGameDepotMapper;
    @Autowired
    private TGmDepotMapper tGmDepotMapper;

    public PageUtils queryListPage(TGmGame tGmGame, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(gameMapper.listTGmGame(tGmGame));
    }

    public TGmGame queryInfo(Integer id) {
        TGmGame tGmGame = tGmGameMapper.selectByPrimaryKey(id);
        List<Integer> listLabelId = gameMapper.findTLabelGameDepotGameId(tGmGame.getId()).stream().filter(e ->
                e.getGameId().equals(tGmGame.getId())).map(TLabelGameDepot::getLabelId).collect(Collectors.toList());
        Integer[] b = listLabelId.toArray(new Integer[listLabelId.size()]);
        tGmGame.setLabels(b);
        return tGmGame;
    }

    public PageUtils queryListGamePage(TGameLogo tGameLogo, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<TGameLogo> list = gameMapper.listtGameLogo(tGameLogo);
        list.stream().forEach(e -> {
            tGameLogo.setDepotId(e.getDepotId());
            Integer nums = gameMapper.findTGmGameNums(tGameLogo.getDepotId());
            Integer openNums = gameMapper.findTGmGameOpenNums(tGameLogo.getDepotId());
            e.setGameCount(openNums + "/" + nums);
        });
        return BeanUtil.toPagedResult(list);
    }

    public PageUtils findGameList(TGmGame tGmGame, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        tGmGame.setId(tGmGame.getGameId());
        return BeanUtil.toPagedResult(gameMapper.findGameList(tGmGame));
    }

    public int save(TGmGame tGmGame) {
        tGmGame.setGoodNum(Constants.EVNumber.zero);
        tGmGame.setClickNum(Constants.EVNumber.zero);
        tGmGame.setCreateUser(getUser().getUsername());
        tGmGame.setTopLink((byte) Constants.EVNumber.one);
        tGmGame.setCreateTime(getCurrentDate(FORMAT_18_DATE_TIME));
        tGmGame.setDepotName(tGmDepotMapper.selectByPrimaryKey(tGmGame.getDepotId()).getDepotName());
        if (tGmGame.getSortId() < 0) {
            throw new RRException("游戏排序号不能为负数!");
        }
        if (StringUtils.isEmpty(tGmGame.getGameCode())
                || StringUtils.isEmpty(tGmGame.getMbGameCode())) {
            throw new RRException("PC端游戏ID和H5游戏ID只允许一个或者都不为空!");
        }
        tGmGameMapper.selectAll().stream().forEach(ls -> {
            if (ls.getDepotId().equals(tGmGame.getDepotId())
                    && ls.getGameCode().equals(tGmGame.getGameCode())
                    && ls.getGameName().equals(tGmGame.getGameName())) {
                throw new RRException("此游戏已存在，不能添加相同的游戏!");
            }
        });

        //TODO 先注释判断名字是否重复
        /*List<String> gameNameList = tGmGameMapper.selectAll().stream().filter(e ->
                e.getGameName().equals(tGmGame.getGameName())).map(TGmGame::getGameName).collect(Collectors.toList());
        if (gameNameList.size() > 0) {
            throw new RRException("此游戏已存在，不能添加相同的游戏!");
        }*/
        isNullSaveOrUpdate(tGmGame);
        tGmGameMapper.insertSelective(tGmGame);
        List<TLabelGameDepot> tLabelGameDepotList = new ArrayList<>();
        Integer[] array = tGmGame.getLabels();
        if (array != null && array.length > 0) {
            Arrays.asList(array).stream().forEach(x -> {
                TLabelGameDepot tLabelGameDepot = new TLabelGameDepot();
                tLabelGameDepot.setGameId(tGmGame.getId());
                tLabelGameDepot.setLabelId(x);
                tLabelGameDepotList.add(tLabelGameDepot);
            });
        } else {
            TLabelGameDepot tLabelGameDepot = new TLabelGameDepot();
            tLabelGameDepot.setGameId(tGmGame.getId());
            tLabelGameDepotList.add(tLabelGameDepot);
        }
        return tLabelGameDepotMapper.insertList(tLabelGameDepotList);
    }

    public int update(TGmGame tGmGame) {
        tGmGame.setModifyUser(getUser().getUsername());
        tGmGame.setModifyTime(getCurrentDate(FORMAT_18_DATE_TIME));
        if (tGmGame.getSortId() < 0)
            throw new RRException("游戏排序号不能为负数!");
        tGmGameMapper.selectAll().stream().forEach(ls -> {
            if (ls.getDepotId().equals(tGmGame.getDepotId())
                    && ls.getGameCode().equals(tGmGame.getGameCode())
                    && ls.getGameName().equals(tGmGame.getGameName())) {
                throw new RRException("此游戏已存在，不能添加相同的游戏!");
            }
        });
        isNullSaveOrUpdate(tGmGame);
        tGmGameMapper.updateByPrimaryKeySelective(tGmGame);
        Integer[] array = tGmGame.getLabels();
        List<Integer> listId = gameMapper.findTLabelGameDepotGameId(tGmGame.getId()).stream().filter(e ->
                e.getGameId().equals(tGmGame.getId())).map(TLabelGameDepot::getId).collect(Collectors.toList());
        if (Collections3.isNotEmpty(listId)) {
            tLabelGameDepotMapper.deleteByIds(String.join(",", Lists.transform(listId, Functions.toStringFunction())));
        }
        if (array != null && array.length > 0) {
            Arrays.asList(array).stream().forEach(x -> {
                TLabelGameDepot tLabelGameDepot = new TLabelGameDepot();
                List<TLabelGameDepot> tLabelGameDepotList = new ArrayList<>();
                tLabelGameDepot.setLabelId(x);
                tLabelGameDepot.setGameId(tGmGame.getId());
                tLabelGameDepotList.add(tLabelGameDepot);
                tLabelGameDepotMapper.insertList(tLabelGameDepotList);
            });
        }
        return 1;
    }

    public int updateAvailable(TGmGame tGmGame) {
        tGmGame.setModifyUser(getUser().getUsername());
        tGmGame.setModifyTime(getCurrentDate(FORMAT_18_DATE_TIME));
        return tGmGameMapper.updateByPrimaryKeySelective(tGmGame);
    }

    public void deleteBatch(TGmGame tGmGame) {
        String idStr = Arrays.stream(tGmGame.getIds()).boxed().map(i -> i.toString())
                .collect(Collectors.joining(","));
        tGmGameMapper.deleteByIds(idStr);
    }

    private void isNullSaveOrUpdate(@RequestBody TGmGame tGmGame) {
        Assert.isNull(tGmGame.getCatId(), "游戏类型不能为空");
        Assert.isNull(tGmGame.getDepotId(), "游戏平台不能为空");
        Assert.isBlank(tGmGame.getGameName(), "游戏名称不能为空");
        Assert.isNull(tGmGame.getSubCatId(), "游戏子类别不能为空");
    }

}
