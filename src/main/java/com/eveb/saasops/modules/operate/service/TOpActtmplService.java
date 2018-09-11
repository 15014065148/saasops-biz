package com.eveb.saasops.modules.operate.service;

import com.eveb.saasops.common.utils.BeanUtil;
import com.eveb.saasops.common.utils.PageUtils;
import com.eveb.saasops.common.validator.Assert;
import com.eveb.saasops.modules.base.service.BaseService;
import com.eveb.saasops.modules.operate.dao.TOpActtmplMapper;
import com.eveb.saasops.modules.operate.entity.TOpActtmpl;
import com.eveb.saasops.modules.operate.mapper.OperateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.eveb.saasops.common.utils.DateUtil.FORMAT_18_DATE_TIME;
import static com.eveb.saasops.common.utils.DateUtil.getCurrentDate;


@Service
public class TOpActtmplService extends BaseService<TOpActtmplMapper, TOpActtmpl> {
    @Autowired
    private OperateMapper operateMapper;
    @Autowired
    private TOpActtmplMapper tOpActtmplMapper;

    public PageUtils queryListPage(TOpActtmpl tOpActtmpl, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(operateMapper.listTOpActtmpl(tOpActtmpl));
    }

    public int save(TOpActtmpl tOpActtmpl) {
        tOpActtmpl.setCreateUser(getUser().getUsername());
        tOpActtmpl.setCreateTime(getCurrentDate(FORMAT_18_DATE_TIME));
        isNullSaveOrUpdate(tOpActtmpl);
        return tOpActtmplMapper.insertSelective(tOpActtmpl);
    }

    public int update(TOpActtmpl tOpActtmpl) {
        tOpActtmpl.setModifyUser(getUser().getUsername());
        tOpActtmpl.setModifyTime(getCurrentDate(FORMAT_18_DATE_TIME));
        isNullSaveOrUpdate(tOpActtmpl);
        return tOpActtmplMapper.updateByPrimaryKeySelective(tOpActtmpl);
    }

    public int updateAvailable(TOpActtmpl tOpActtmpl) {
        tOpActtmpl.setModifyUser(getUser().getUsername());
        tOpActtmpl.setModifyTime(getCurrentDate(FORMAT_18_DATE_TIME));
        return tOpActtmplMapper.updateByPrimaryKeySelective(tOpActtmpl);
    }

    public void deleteBatch(TOpActtmpl tOpActtmpl) {
        Assert.isNull(tOpActtmpl, "不能为空");
        String idStr = Arrays.stream(tOpActtmpl.getIds()).boxed().map(i -> i.toString())
                .collect(Collectors.joining(","));
        tOpActtmplMapper.deleteByIds(idStr);
    }

    private void isNullSaveOrUpdate(@RequestBody TOpActtmpl tOpActtmpl) {
        Assert.isBlank(tOpActtmpl.getTmplName(), "活动分类名称不能为空");
        Assert.isNull(tOpActtmpl.getAvailable(), "状态不能为空");
        Assert.isNull(tOpActtmpl.getSortId(), "排序号不能为空");
    }

}
