package com.eveb.saasops.modules.game.service;

import com.eveb.saasops.common.utils.BeanUtil;
import com.eveb.saasops.common.utils.PageUtils;
import com.eveb.saasops.modules.base.service.BaseService;
import com.eveb.saasops.modules.game.dao.TGmCodeMapper;
import com.eveb.saasops.modules.game.entity.TGmCode;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

@Service
public class TGmCodeService extends BaseService<TGmCodeMapper, TGmCode>{

    @Autowired
    private TGmCodeMapper tGmCodeMapper;
    public PageUtils queryListPage(TGmCode tGmCode, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(tGmCodeMapper.selectAll());
    }

    public int save(TGmCode tGmCode) {
        return tGmCodeMapper.insertSelective(tGmCode);
    }

}
