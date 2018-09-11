package com.eveb.saasops.modules.operate.service;

import com.eveb.saasops.common.utils.BeanUtil;
import com.eveb.saasops.common.utils.PageUtils;
import com.eveb.saasops.modules.base.service.BaseService;
import com.eveb.saasops.modules.operate.dao.TOpPaywebsiterelationMapper;
import com.eveb.saasops.modules.operate.entity.TOpPaywebsiterelation;
import com.eveb.saasops.modules.operate.mapper.OperateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TOpPaywebsiterelationService extends BaseService<TOpPaywebsiterelationMapper, TOpPaywebsiterelation> {
    @Autowired
    private OperateMapper operateMapper;
    @Autowired
    private TOpPaywebsiterelationMapper tOpPaywebsiterelationMapper;

    public PageUtils queryListPage(TOpPaywebsiterelation tOpPaywebsiterelation, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<TOpPaywebsiterelation> list = tOpPaywebsiterelationMapper.selectAll();
        return BeanUtil.toPagedResult(list);
    }

    public List<TOpPaywebsiterelation> listTOpPaywebsiterelation(Integer paymentId) {
        return operateMapper.getTOpPaywebsiterelation(paymentId);
    }

}
