package com.eveb.saasops.modules.game.service;

import com.eveb.saasops.common.utils.BeanUtil;
import com.eveb.saasops.common.utils.PageUtils;
import com.eveb.saasops.modules.base.service.BaseService;
import com.eveb.saasops.modules.game.dao.TGmLabelMapper;
import com.eveb.saasops.modules.game.entity.TGmLabel;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.eveb.saasops.common.utils.DateUtil.FORMAT_18_DATE_TIME;
import static com.eveb.saasops.common.utils.DateUtil.getCurrentDate;

@Service
public class TGmLabelService extends BaseService<TGmLabelMapper, TGmLabel> {
    @Autowired
    private TGmLabelMapper tGmLabelMapper;

    public PageUtils queryListPage(TGmLabel tGmLabel, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(tGmLabelMapper.selectAll());
    }

    public int save(TGmLabel tGmLabel) {
        tGmLabel.setCreateUser(getUser().getUsername());
        tGmLabel.setCreateTime(getCurrentDate(FORMAT_18_DATE_TIME));
        return tGmLabelMapper.insert(tGmLabel);
    }

    public void deleteBatch(TGmLabel tGmLabel) {
        String idStr = Arrays.stream(tGmLabel.getIds()).boxed().map(i -> i.toString())
                .collect(Collectors.joining(","));
        tGmLabelMapper.deleteByIds(idStr);
    }

}
