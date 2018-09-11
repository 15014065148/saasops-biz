package com.eveb.saasops.modules.game.service;

import com.eveb.saasops.common.exception.RRException;
import com.eveb.saasops.common.utils.BeanUtil;
import com.eveb.saasops.common.utils.Collections3;
import com.eveb.saasops.common.utils.PageUtils;
import com.eveb.saasops.common.validator.Assert;
import com.eveb.saasops.modules.base.service.BaseService;
import com.eveb.saasops.modules.game.dao.TGameLogoMapper;
import com.eveb.saasops.modules.game.dao.TLabelGameDepotMapper;
import com.eveb.saasops.modules.game.entity.TGameLogo;
import com.eveb.saasops.modules.game.entity.TLabelGameDepot;
import com.eveb.saasops.modules.game.mapper.GameMapper;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TGameLogoService extends BaseService<TGameLogoMapper, TGameLogo> {
    @Autowired
    private TGameLogoMapper tGameLogoMapper;
    @Autowired
    private GameMapper gameMapper;
    @Autowired
    private TLabelGameDepotMapper tLabelGameDepotMapper;

    public PageUtils queryListPage(TGameLogo tGameLogo, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(tGameLogoMapper.selectAll());
    }

    public PageUtils listInfo(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(tGameLogoMapper.selectAll());
    }

    public TGameLogo queryInfo(Integer id) {
        TGameLogo tGameLogo = tGameLogoMapper.selectByPrimaryKey(id);
        List<Integer> listLabelId = gameMapper.findTLabelGameDepotId(tGameLogo.getId()).stream().filter(e ->
                e.getDepotLogoId().equals(tGameLogo.getId())).map(TLabelGameDepot::getLabelId).collect(Collectors.toList());
        tGameLogo.setLabels(listLabelId.toArray(new Integer[listLabelId.size()]));
        return tGameLogo;
    }

    public int save(TGameLogo tGameLogo) {
        isNullSaveOrUpdate(tGameLogo);
        if (tGameLogo.getSortId() < 0)
            throw new RRException("分类->平台排序号不能为负数!");
        tGameLogoMapper.insertSelective(tGameLogo);
        Integer[] array = tGameLogo.getLabels();
        List<TLabelGameDepot> tLabelGameDepotList = new ArrayList<>();
        if (array != null && array.length > 0) {
            Arrays.asList(array).stream().forEach(x -> {
                TLabelGameDepot tLabelGameDepot = new TLabelGameDepot();
                tLabelGameDepot.setDepotLogoId(tGameLogo.getId());
                tLabelGameDepot.setLabelId(x);
                tLabelGameDepotList.add(tLabelGameDepot);
            });
        } else {
            TLabelGameDepot tLabelGameDepot = new TLabelGameDepot();
            tLabelGameDepot.setDepotLogoId(tGameLogo.getId());
            tLabelGameDepotList.add(tLabelGameDepot);
        }
        return tLabelGameDepotMapper.insertList(tLabelGameDepotList);
    }

    public int update(TGameLogo tGameLogo) {
        isNullSaveOrUpdate(tGameLogo);
        if (tGameLogo.getSortId() < 0)
            throw new RRException("分类->平台排序号不能为负数!");
        tGameLogoMapper.updateByPrimaryKeySelective(tGameLogo);
        Integer[] array = tGameLogo.getLabels();
        List<Integer> listId = gameMapper.findTLabelGameDepotId(tGameLogo.getId()).stream().filter(e ->
                e.getDepotLogoId().equals(tGameLogo.getId())).map(TLabelGameDepot::getId).collect(Collectors.toList());
        if (Collections3.isNotEmpty(listId)) {
            List<String> listIdLiStr = Lists.transform(listId, Functions.toStringFunction());
            String listIdStr = String.join(",", listIdLiStr);
            tLabelGameDepotMapper.deleteByIds(listIdStr);
        }
        if (array != null && array.length > 0) {
            Arrays.asList(array).stream().forEach(x -> {
                TLabelGameDepot tLabelGameDepot = new TLabelGameDepot();
                List<TLabelGameDepot> tLabelGameDepotList = new ArrayList<>();
                tLabelGameDepot.setLabelId(x);
                tLabelGameDepot.setDepotLogoId(tGameLogo.getId());
                tLabelGameDepotList.add(tLabelGameDepot);
                tLabelGameDepotMapper.insertList(tLabelGameDepotList);
            });
        }
        return 1;
    }

    public int updateAvailable(TGameLogo tGameLogo) {
        return tGameLogoMapper.updateByPrimaryKeySelective(tGameLogo);
    }

    public void deleteBatch(TGameLogo tGameLogo) {
        String idStr = Arrays.stream(tGameLogo.getIds()).boxed().map(i -> i.toString())
                .collect(Collectors.joining(","));
        tGameLogoMapper.deleteByIds(idStr);
    }

    private void isNullSaveOrUpdate(@RequestBody TGameLogo tGameLogo) {
        Assert.isNull(tGameLogo.getCatId(), "游戏类型不能为空");
        Assert.isNull(tGameLogo.getDepotId(), "游戏平台不能为空");
        Assert.isNull(tGameLogo.getSortId(), "排序号不能为空");
    }

}
