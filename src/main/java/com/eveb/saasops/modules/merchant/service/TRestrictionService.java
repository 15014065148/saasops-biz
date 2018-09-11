package com.eveb.saasops.modules.merchant.service;

import com.eveb.saasops.common.utils.BeanUtil;
import com.eveb.saasops.common.utils.PageUtils;
import com.eveb.saasops.common.validator.Assert;
import com.eveb.saasops.modules.base.service.BaseService;
import com.eveb.saasops.modules.merchant.dao.TRestrictionMapper;
import com.eveb.saasops.modules.merchant.entity.TRestriction;
import com.eveb.saasops.modules.merchant.mapper.MerchantMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.eveb.saasops.common.utils.DateUtil.FORMAT_18_DATE_TIME;
import static com.eveb.saasops.common.utils.DateUtil.getCurrentDate;


@Service
public class TRestrictionService extends BaseService<TRestrictionMapper, TRestriction> {
    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private TRestrictionMapper tRestrictionMapper;

    public PageUtils queryListPage(TRestriction tRestriction, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(merchantMapper.listTRestriction(tRestriction));
    }

    public int save(TRestriction tRestriction) {
        isNullSaveOrUpdate(tRestriction);
        return tRestrictionMapper.insertSelective(tRestriction);
    }

    public int update(TRestriction tRestriction) {
        tRestriction.setModifyUser(getUser().getUsername());
        tRestriction.setModifyTime(getCurrentDate(FORMAT_18_DATE_TIME));
        isNullSaveOrUpdate(tRestriction);
        return tRestrictionMapper.updateByPrimaryKeySelective(tRestriction);
    }

    public int updateAvailable(TRestriction tRestriction) {
        tRestriction.setModifyUser(getUser().getUsername());
        tRestriction.setModifyTime(getCurrentDate(FORMAT_18_DATE_TIME));
        return tRestrictionMapper.updateByPrimaryKeySelective(tRestriction);
    }

    public void deleteBatch(TRestriction tRestriction) {
        String idStr = Arrays.stream(tRestriction.getIds()).boxed().map(i -> i.toString())
                .collect(Collectors.joining(","));
        tRestrictionMapper.deleteByIds(idStr);
    }

    private void isNullSaveOrUpdate(@RequestBody TRestriction tRestriction) {
        Assert.isNull(tRestriction.getAvailable(), "状态不能为空");
        Assert.isBlank(tRestriction.getSiteName(), "站点名称不能为空");
        Assert.isBlank(tRestriction.getLimitArea(), "限制区域不能为空");
        Assert.isBlank(tRestriction.getEndDate(), "生效结束时间不能为空");
        Assert.isBlank(tRestriction.getStartDate(), "生效起始时间不能为空");
    }

}
