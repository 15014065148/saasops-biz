package com.eveb.saasops.modules.game.service;

import com.eveb.saasops.common.exception.RRException;
import com.eveb.saasops.common.utils.BeanUtil;
import com.eveb.saasops.common.utils.PageUtils;
import com.eveb.saasops.common.validator.Assert;
import com.eveb.saasops.modules.base.service.BaseService;
import com.eveb.saasops.modules.game.dao.TGmDepotMapper;
import com.eveb.saasops.modules.game.entity.TGmDepot;
import com.eveb.saasops.modules.game.mapper.GameMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.eveb.saasops.common.utils.DateUtil.FORMAT_18_DATE_TIME;
import static com.eveb.saasops.common.utils.DateUtil.getCurrentDate;

@Service
@Transactional
public class TGmDepotService extends BaseService<TGmDepotMapper, TGmDepot> {
    @Autowired
    private GameMapper gameMapper;

    @Autowired
    private TGmDepotMapper tGmDepotMapper;

    public PageUtils listTGmDepot(TGmDepot tGmDepot, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(gameMapper.listTGmDepot(tGmDepot));
    }

    public int save(TGmDepot tGmDepot) {
        tGmDepot.setCreateUser(getUser().getUsername());
        tGmDepot.setCreateTime(getCurrentDate(FORMAT_18_DATE_TIME));
        if (tGmDepot.getSortId() < 0)
            throw new RRException("平台排序号不能为负数!");
        List<String> depotNameList = tGmDepotMapper.selectAll().stream().filter(e ->
                e.getDepotName().equals(tGmDepot.getDepotName())).map(TGmDepot::getDepotName).collect(Collectors.toList());
        if (depotNameList.size() > 0)
            throw new RRException("此平台已存在，不能添加相同的平台!");
        isNullSaveOrUpdate(tGmDepot);
        return tGmDepotMapper.insertSelective(tGmDepot);
    }

    public int update(TGmDepot tGmDepot) {
        tGmDepot.setModifyUser(getUser().getUsername());
        tGmDepot.setModifyTime(getCurrentDate(FORMAT_18_DATE_TIME));
        if (tGmDepot.getSortId() < 0)
            throw new RRException("平台排序号不能为负数!");
        List<String> depotNameList = tGmDepotMapper.selectAll().stream().filter(e ->
                e.getDepotName().equals(tGmDepot.getDepotName())).map(TGmDepot::getDepotName).collect(Collectors.toList());
        if (depotNameList.size() > 1)
            throw new RRException("此平台已存在，不能添加相同的平台!");
        isNullSaveOrUpdate(tGmDepot);
        return tGmDepotMapper.updateByPrimaryKeySelective(tGmDepot);
    }

    public int updateAvailable(TGmDepot tGmDepot) {
        tGmDepot.setModifyUser(getUser().getUsername());
        tGmDepot.setModifyTime(getCurrentDate(FORMAT_18_DATE_TIME));
        return tGmDepotMapper.updateByPrimaryKeySelective(tGmDepot);
    }

    public void deleteBatch(TGmDepot tGmDepot) {
        Assert.isNull(tGmDepot, "不能为空");
        String idStr = Arrays.stream(tGmDepot.getIds()).boxed().map(i -> i.toString())
                .collect(Collectors.joining(","));
        tGmDepotMapper.deleteByIds(idStr);
    }

    public List<TGmDepot> findGameDepot() {
        return gameMapper.findGameDepot();
    }

    private void isNullSaveOrUpdate(@RequestBody TGmDepot tGmDepot) {
        Assert.isBlank(tGmDepot.getDepotCode(), "游戏平台编码不能为空");
        Assert.isBlank(tGmDepot.getDepotName(), "游戏平台名称不能为空");
        Assert.isNull(tGmDepot.getAvailable(), "状态不能为空");
        Assert.isNull(tGmDepot.getSortId(), "排序不能为空");
    }
}
