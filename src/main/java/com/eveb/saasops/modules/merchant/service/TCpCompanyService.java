package com.eveb.saasops.modules.merchant.service;

import com.eveb.saasops.common.exception.RRException;
import com.eveb.saasops.common.utils.BeanUtil;
import com.eveb.saasops.common.utils.PageUtils;
import com.eveb.saasops.common.validator.Assert;
import com.eveb.saasops.modules.base.service.BaseService;
import com.eveb.saasops.modules.merchant.dao.TCpCompanyMapper;
import com.eveb.saasops.modules.merchant.entity.TCpCompany;
import com.eveb.saasops.modules.merchant.mapper.MerchantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.eveb.saasops.common.utils.DateUtil.FORMAT_18_DATE_TIME;
import static com.eveb.saasops.common.utils.DateUtil.getCurrentDate;

@Service
public class TCpCompanyService extends BaseService<TCpCompanyMapper, TCpCompany> {

    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private TCpCompanyMapper tCpCompanyMapper;

    public PageUtils queryListPage(TCpCompany tCpCompany, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(merchantMapper.listTCpCompany(tCpCompany));
    }

    public int save(TCpCompany tCpCompany) {
        tCpCompany.setCreateUser(getUser().getUsername());
        tCpCompany.setCreateTime(getCurrentDate(FORMAT_18_DATE_TIME));
        isNullSaveOrUpdate(tCpCompany);
        return tCpCompanyMapper.insertSelective(tCpCompany);
    }

    public int update(TCpCompany tCpCompany) {
        tCpCompany.setModifyUser(getUser().getUsername());
        tCpCompany.setModifyTime(getCurrentDate(FORMAT_18_DATE_TIME));
        isNullSaveOrUpdate(tCpCompany);
        return tCpCompanyMapper.updateByPrimaryKeySelective(tCpCompany);
    }

    public int updateAvailable(TCpCompany tCpCompany) {
        tCpCompany.setModifyUser(getUser().getUsername());
        tCpCompany.setModifyTime(getCurrentDate(FORMAT_18_DATE_TIME));
        int[] ids = tCpCompany.getIds();
        if (ids == null || ids.length <= 0)
            throw new RRException("请选择需要删除的数据！");
        return merchantMapper.updateAvailable(tCpCompany);
    }

    public void deleteBatch(TCpCompany tCpCompany) {
        String idStr = Arrays.stream(tCpCompany.getIds()).boxed().map(i -> i.toString())
                .collect(Collectors.joining(","));
        tCpCompanyMapper.deleteByIds(idStr);
    }

    private void isNullSaveOrUpdate(@RequestBody TCpCompany tCpCompany) {
        Assert.isBlank(tCpCompany.getCompanySname(), "商户简称不能为空");
        Assert.isBlank(tCpCompany.getCompanyFname(), "商户全称不能为空");
        Assert.isBlank(tCpCompany.getStartDate(), "开始时间不能为空");
        Assert.isBlank(tCpCompany.getEndDate(), "结束时间不能为空");
        Assert.isBlank(tCpCompany.getCountryCode(), "国家代码不能为空");
        Assert.isBlank(tCpCompany.getPhoneNumber(), "电话号码不能为空");
        Assert.isNull(tCpCompany.getAvailable(), "状态不能为空");
        Assert.isNull(tCpCompany.getSortId(), "排序号不能为空");
    }

}
