package com.eveb.saasops.modules.game.service;

import com.eveb.saasops.common.constants.Constants;
import com.eveb.saasops.common.exception.RRException;
import com.eveb.saasops.common.utils.BeanUtil;
import com.eveb.saasops.common.utils.PageUtils;
import com.eveb.saasops.common.validator.Assert;
import com.eveb.saasops.modules.base.service.BaseService;
import com.eveb.saasops.modules.game.dao.TGmCatMapper;
import com.eveb.saasops.modules.game.entity.TGmCat;
import com.eveb.saasops.modules.game.mapper.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.eveb.saasops.common.utils.DateUtil.FORMAT_18_DATE_TIME;
import static com.eveb.saasops.common.utils.DateUtil.getCurrentDate;


@Service
public class TGmCatService extends BaseService<TGmCatMapper, TGmCat> {

    @Autowired
    private GameMapper gameMapper;
    @Autowired
    private TGmCatMapper tGmCatMapper;

    public PageUtils queryListPage(TGmCat tGmCat, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(gameMapper.listTGmCat(tGmCat));
    }

    public List<TGmCat> findGameType() {
        return gameMapper.findGameType();
    }

    public List<TGmCat> findSubCat() {
        return gameMapper.findSubCat();
    }

    public int save(TGmCat tGmCat) {
        setAllEnable(tGmCat);
        tGmCat.setCreateUser(getUser().getUsername());
        tGmCat.setCreateTime(getCurrentDate(FORMAT_18_DATE_TIME));
        //TODO 如果没有选子分类就默认设置为0
        if (Objects.isNull(tGmCat.getParentId())) {
            tGmCat.setParentId(0);
        }
        if (tGmCat.getSortId() < Constants.EVNumber.zero) {
            throw new RRException("分类排序号不能为负数!");
        }
        List<String> catNameList = tGmCatMapper.selectAll().stream().filter(e ->
                e.getCatName().equals(tGmCat.getCatName())).map(TGmCat::getCatName).collect(Collectors.toList());
        if (catNameList.size() > Constants.EVNumber.zero) {
            throw new RRException("分类已存在，不能添加相同的分类!");
        }
        isNullSaveOrUpdate(tGmCat);
        return tGmCatMapper.insertSelective(tGmCat);
    }

    public void setAllEnable(TGmCat tGmCat) {
        //TODO 如果状态为开启，那么默认三端都开启。反之！
        if (tGmCat.getAvailable() == Constants.EVNumber.one) {
            tGmCat.setEnablePc(Constants.EVNumber.one);
            tGmCat.setEnableMb(Constants.EVNumber.one);
            tGmCat.setEnableApp(Constants.EVNumber.one);
        }
        if (tGmCat.getAvailable() == Constants.EVNumber.zero) {
            tGmCat.setEnablePc(Constants.EVNumber.zero);
            tGmCat.setEnableMb(Constants.EVNumber.zero);
            tGmCat.setEnableApp(Constants.EVNumber.zero);
        }
    }

    public int update(TGmCat tGmCat) {
        setAllEnable(tGmCat);
        tGmCat.setModifyUser(getUser().getUsername());
        tGmCat.setModifyTime(getCurrentDate(FORMAT_18_DATE_TIME));
        if (tGmCat.getSortId() < Constants.EVNumber.zero) throw new RRException("分类排序号不能为负数!");
        List<String> catNameList = tGmCatMapper.selectAll().stream().filter(e ->
                e.getCatName().equals(tGmCat.getCatName())).map(TGmCat::getCatName).collect(Collectors.toList());
        if (catNameList.size() > Constants.EVNumber.one)
            throw new RRException("分类已存在，不能添加相同的分类!");
        isNullSaveOrUpdate(tGmCat);
        return tGmCatMapper.updateByPrimaryKeySelective(tGmCat);
    }

    public int updateAvailable(TGmCat tGmCat) {
        tGmCat.setModifyUser(getUser().getUsername());
        tGmCat.setModifyTime(getCurrentDate(FORMAT_18_DATE_TIME));
        return tGmCatMapper.updateByPrimaryKeySelective(tGmCat);
    }

    public void deleteBatch(TGmCat tGmCat) {
        Assert.isNull(tGmCat, "不能为空");
        String idStr = Arrays.stream(tGmCat.getIds()).boxed().map(i -> i.toString())
                .collect(Collectors.joining(","));
        tGmCatMapper.deleteByIds(idStr);
    }

    private void isNullSaveOrUpdate(@RequestBody TGmCat tGmCat) {
        Assert.isBlank(tGmCat.getCatName(), "游戏分类不能为空");
        Assert.isNull(tGmCat.getAvailable(), "状态不能为空");
        Assert.isNull(tGmCat.getSortId(), "排序号不能为空");
    }

}
