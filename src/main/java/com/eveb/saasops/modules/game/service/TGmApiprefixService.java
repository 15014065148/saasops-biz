package com.eveb.saasops.modules.game.service;

import com.eveb.saasops.common.utils.BeanUtil;
import com.eveb.saasops.common.utils.DateUtil;
import com.eveb.saasops.common.utils.PageUtils;
import com.eveb.saasops.common.utils.R;
import com.eveb.saasops.modules.base.service.BaseService;
import com.eveb.saasops.modules.game.dao.TGmApiprefixMapper;
import com.eveb.saasops.modules.game.entity.TGmApiprefix;
import com.eveb.saasops.modules.game.mapper.GamePrefixMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class TGmApiprefixService extends BaseService<TGmApiprefixMapper, TGmApiprefix> {

    @Autowired
    private GamePrefixMapper gamePrefixMapper;

    @Autowired
    private TGmApiprefixMapper tGmApiprefixMapper;

    public PageUtils queryListPage(TGmApiprefix tGmApiprefix, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(gamePrefixMapper.queryListPage(tGmApiprefix));
    }

    public PageUtils getApiBySiteCode(String siteCode, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(gamePrefixMapper.getApiBySiteCode(siteCode));
    }

    public TGmApiprefix querySiteInfo(String siteId) {
        return gamePrefixMapper.querySiteInfo(siteId);
    }

    public int save(TGmApiprefix[] arr) {
        List<TGmApiprefix> list = Arrays.asList(arr);
        list.stream().forEach(ls -> {
            ls.setCreateUser(getUser().getUsername());
        });
        return tGmApiprefixMapper.insertList(list);
    }

    public int deleteInfoById(Integer[] ids) {
        List<Integer> list = Arrays.asList(ids);
        return gamePrefixMapper.deleteInfoById(list);
    }

    public int closeApi(Integer id, Integer available) {
        return gamePrefixMapper.closeApi(id, available);
    }
}
