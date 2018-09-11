package com.eveb.saasops.modules.operate.service;

import com.eveb.saasops.common.utils.BeanUtil;
import com.eveb.saasops.common.utils.PageUtils;
import com.eveb.saasops.modules.base.service.BaseService;
import com.eveb.saasops.modules.operate.dao.TOpPaybankrelationMapper;
import com.eveb.saasops.modules.operate.entity.TOpPaybankrelation;
import org.springframework.util.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TOpPaybankrelationService extends BaseService<TOpPaybankrelationMapper, TOpPaybankrelation> {

    public PageUtils queryListPage(TOpPaybankrelation tOpPaybankrelation, Integer pageNo, Integer pageSize, String orderBy) {
        PageHelper.startPage(pageNo, pageSize);
        if (!StringUtils.isEmpty(orderBy))
            PageHelper.orderBy(orderBy);
        List<TOpPaybankrelation> list = super.queryList();
        return BeanUtil.toPagedResult(list);
    }

    public void deleteBatch(Integer[] ids) {
    }

    public List<TOpPaybankrelation> listBankName(Integer paymentId) {
        TOpPaybankrelation tOpPaybankrelation = new TOpPaybankrelation();
        tOpPaybankrelation.setPaymentId(paymentId);
        return super.queryListCond(tOpPaybankrelation);
    }
}
