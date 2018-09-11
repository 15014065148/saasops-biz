package com.eveb.saasops.modules.operate.service;

import com.eveb.saasops.common.utils.BeanUtil;
import com.eveb.saasops.common.utils.Collections3;
import com.eveb.saasops.common.utils.PageUtils;
import com.eveb.saasops.common.validator.Assert;
import com.eveb.saasops.modules.base.service.BaseService;
import com.eveb.saasops.modules.operate.dao.TOpPayMapper;
import com.eveb.saasops.modules.operate.dao.TOpPaybankrelationMapper;
import com.eveb.saasops.modules.operate.dao.TOpPaywebsiterelationMapper;
import com.eveb.saasops.modules.operate.entity.TOpPay;
import com.eveb.saasops.modules.operate.entity.TOpPaybankrelation;
import com.eveb.saasops.modules.operate.entity.TOpPaywebsiterelation;
import com.eveb.saasops.modules.operate.mapper.OperateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.eveb.saasops.common.utils.DateUtil.FORMAT_18_DATE_TIME;
import static com.eveb.saasops.common.utils.DateUtil.getCurrentDate;


@Service
public class TOpPayService extends BaseService<TOpPayMapper, TOpPay> {
    @Autowired
    private OperateMapper operateMapper;
    @Autowired
    private TOpPayMapper tOpPayMapper;
    @Autowired
    private TOpPaybankrelationMapper tOpPaybankrelationMapper;
    @Autowired
    private TOpPaywebsiterelationMapper tOpPaywebsiterelationMapper;

    public PageUtils queryListPage(TOpPay tOpPay, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(operateMapper.listTOpPay(tOpPay));
    }

    @Transactional
    public int save(TOpPay tOpPay) {
        tOpPay.setCreateUser(getUser().getUsername());
        tOpPay.setCreateTime(getCurrentDate(FORMAT_18_DATE_TIME));
        isNullSaveOrUpdate(tOpPay);
        tOpPayMapper.insertSelective(tOpPay);
        insertBank(tOpPay);
        insertSite(tOpPay);
        return 1;
    }

    private void insertSite(TOpPay tOpPay) {
        if (Collections3.isNotEmpty(tOpPay.getTOpPaybankrelationList())) {
            tOpPay.getTOpPaywebsiterelationList().stream().forEach(e -> {
                e.setPaymentId(tOpPay.getId());
            });
            tOpPaywebsiterelationMapper.insertList(tOpPay.getTOpPaywebsiterelationList());
        }
    }

    private void insertBank(TOpPay tOpPay) {
        if (Collections3.isNotEmpty(tOpPay.getTOpPaybankrelationList())) {
            tOpPay.getTOpPaybankrelationList().stream().forEach(e -> {
                e.setPaymentId(tOpPay.getId());
            });
            tOpPaybankrelationMapper.insertList(tOpPay.getTOpPaybankrelationList());
        }
    }

    @Transactional
    public int update(TOpPay tOpPay) {
        tOpPay.setModifyUser(getUser().getUsername());
        tOpPay.setModifyTime(getCurrentDate(FORMAT_18_DATE_TIME));
        TOpPaybankrelation tOpPaybankrelation = new TOpPaybankrelation();
        TOpPaywebsiterelation tOpPaywebsiterelation = new TOpPaywebsiterelation();
        tOpPaybankrelation.setBankId(tOpPay.getId());
        tOpPaywebsiterelation.setCpSiteId(tOpPay.getId());
        tOpPaybankrelationMapper.delete(tOpPaybankrelation);
        tOpPaywebsiterelationMapper.delete(tOpPaywebsiterelation);
        insertBank(tOpPay);
        insertSite(tOpPay);
        isNullSaveOrUpdate(tOpPay);
        return tOpPayMapper.updateByPrimaryKeySelective(tOpPay);
    }

    public int updateAvailable(TOpPay tOpPay) {
        tOpPay.setModifyUser(getUser().getUsername());
        tOpPay.setModifyTime(getCurrentDate(FORMAT_18_DATE_TIME));
        return tOpPayMapper.updateByPrimaryKeySelective(tOpPay);
    }

    public void deleteBatch(TOpPay tOpPay) {
        Assert.isNull(tOpPay, "不能为空");
        String idStr = Arrays.stream(tOpPay.getIds()).boxed().map(i -> i.toString())
                .collect(Collectors.joining(","));
        tOpPayMapper.deleteByIds(idStr);
    }

    private void isNullSaveOrUpdate(@RequestBody TOpPay tOpPay) {
        Assert.isNull(tOpPay.getIsEnable(), "状态不能为空");
        Assert.isBlank(tOpPay.getPayment(), "支付机构不能为空");
        Assert.isNull(tOpPay.getPayType(), "支付方式不能为空");
    }

}
