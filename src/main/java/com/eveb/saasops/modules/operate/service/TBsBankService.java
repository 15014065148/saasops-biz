package com.eveb.saasops.modules.operate.service;

import com.eveb.saasops.common.utils.BeanUtil;
import com.eveb.saasops.common.utils.PageUtils;
import com.eveb.saasops.common.validator.Assert;
import com.eveb.saasops.modules.base.service.BaseService;
import com.eveb.saasops.modules.operate.dao.TBsBankMapper;
import com.eveb.saasops.modules.operate.entity.TBsBank;
import com.eveb.saasops.modules.operate.mapper.OperateMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.eveb.saasops.common.utils.DateUtil.FORMAT_18_DATE_TIME;
import static com.eveb.saasops.common.utils.DateUtil.getCurrentDate;


@Service
public class TBsBankService extends BaseService<TBsBankMapper, TBsBank> {
    @Autowired
    private OperateMapper operateMapper;
    @Autowired
    private TBsBankMapper tBsBankMapper;

    public PageUtils queryListPage(TBsBank tBsBank, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(operateMapper.listTBsBank(tBsBank));
    }

    public int save(TBsBank tBsBank) {
        tBsBank.setCreateUser(getUser().getUsername());
        tBsBank.setCreateTime(getCurrentDate(FORMAT_18_DATE_TIME));
        isNullSaveOrUpdate(tBsBank);
        return tBsBankMapper.insertSelective(tBsBank);
    }

    public int update(TBsBank tBsBank) {
        tBsBank.setModifyTime(getCurrentDate(FORMAT_18_DATE_TIME));
        tBsBank.setModifyUser(getUser().getUsername());
        isNullSaveOrUpdate(tBsBank);
        return tBsBankMapper.updateByPrimaryKeySelective(tBsBank);
    }

    public int updateAvailable(TBsBank tBsBank) {
        tBsBank.setModifyTime(getCurrentDate(FORMAT_18_DATE_TIME));
        tBsBank.setModifyUser(getUser().getUsername());
        return tBsBankMapper.updateByPrimaryKeySelective(tBsBank);
    }

    public void deleteBatch(TBsBank tBsBank) {
        Assert.isNull(tBsBank, "不能为空");
        String idStr = Arrays.stream(tBsBank.getIds()).boxed().map(i -> i.toString())
                .collect(Collectors.joining(","));
        tBsBankMapper.deleteByIds(idStr);
    }

    private void isNullSaveOrUpdate(@RequestBody TBsBank tBsBank) {
        Assert.isBlank(tBsBank.getBankName(), "银行名称不能为空");
        Assert.isBlank(tBsBank.getBankCode(), "银行编码不能为空");
        Assert.isNull(tBsBank.getAvailable(), "银行状态不能为空");
        Assert.isNull(tBsBank.getMemberBankEnable(), "会员提款银行不能为空");
        Assert.isNull(tBsBank.getComePanyBankEnable(), "公司收款银行不能为空");
        Assert.isBlank(tBsBank.getBankNameAbbreviation(), "银行名称简称不能为空");
    }
}
