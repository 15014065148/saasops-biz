package com.eveb.saasops.modules.operate.service;

import com.eveb.saasops.common.utils.BeanUtil;
import com.eveb.saasops.common.utils.PageUtils;
import com.eveb.saasops.common.validator.Assert;
import com.eveb.saasops.modules.operate.dao.TOpPayConfigMapper;
import com.eveb.saasops.modules.operate.dao.TOpPayMapper;
import com.eveb.saasops.modules.operate.entity.TOpPay;
import com.eveb.saasops.modules.operate.entity.TOpPayConfig;
import com.eveb.saasops.modules.operate.mapper.PayConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class TOpPayConfigService {

    @Autowired
    private PayConfigMapper payConfigMapper;

    @Autowired
    private TOpPayConfigMapper tOpPayConfigMapper;

    public PageUtils queryListPage(TOpPayConfig payConfig, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(payConfigMapper.queryPayListPage(payConfig));
    }

    @Transactional
    public int add(TOpPayConfig payConfig) {
        return tOpPayConfigMapper.insert(payConfig);
    }

    public int deleteBatch(Integer[] ids) {
        Assert.isNull(ids, "不能为空");
        List list = Arrays.asList(ids);
        return payConfigMapper.deleteByIds(list);
    }

    public int closeOrOpen(Integer relateId, Integer available){
        return payConfigMapper.closeOrOpen(relateId, available);
    }

}
