package com.eveb.saasops.modules.game.service;

import com.eveb.saasops.common.utils.BeanUtil;
import com.eveb.saasops.common.utils.PageUtils;
import com.eveb.saasops.modules.base.service.BaseService;
import com.eveb.saasops.modules.game.dao.TGmDepotcatMapper;
import com.eveb.saasops.modules.game.entity.TGmDepotcat;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

@Service
public class TGmDepotcatService extends BaseService<TGmDepotcatMapper, TGmDepotcat> {
    @Autowired
    private TGmDepotcatMapper tGmDepotcatMapper;

    public PageUtils queryListPage(TGmDepotcat tGmDepotcat, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(tGmDepotcatMapper.selectAll());
    }

    public int save(TGmDepotcat tGmDepotcat) {
        return tGmDepotcatMapper.insertSelective(tGmDepotcat);
    }
}
