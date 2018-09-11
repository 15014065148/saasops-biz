package com.eveb.saasops.modules.game.service;

import com.eveb.saasops.common.utils.BeanUtil;
import com.eveb.saasops.common.utils.PageUtils;
import com.eveb.saasops.modules.base.service.BaseService;
import com.eveb.saasops.modules.game.dao.TGmCaseapiDelMapper;
import com.eveb.saasops.modules.game.entity.TGmCaseapiDel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TGmCaseapiDelService extends BaseService<TGmCaseapiDelMapper, TGmCaseapiDel> {
    @Autowired
    private TGmCaseapiDelMapper tGmCaseapiDelMapper;

    public PageUtils queryListPage(TGmCaseapiDel tGmCaseapiDel, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(tGmCaseapiDelMapper.selectAll());
    }

    public int save(TGmCaseapiDel tGmCaseapiDel) {
        return super.insertSelective(tGmCaseapiDel);
    }

    public List<TGmCaseapiDel> listTGmCaseapiDel(Integer caseId) {
        TGmCaseapiDel tGmCaseapiDel = new TGmCaseapiDel();
        tGmCaseapiDel.setCaseId(caseId);
        return tGmCaseapiDelMapper.select(tGmCaseapiDel);
    }

    public void deleteBatch(TGmCaseapiDel tGmCaseapiDel) {
        Assert.isNull(tGmCaseapiDel, "不能为空");
        String idStr = Arrays.stream(tGmCaseapiDel.getIds()).boxed().map(i -> i.toString())
                .collect(Collectors.joining(","));
        tGmCaseapiDelMapper.deleteByIds(idStr);
    }

}
