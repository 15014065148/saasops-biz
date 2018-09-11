package com.eveb.saasops.modules.game.service;

import com.eveb.saasops.common.utils.BeanUtil;
import com.eveb.saasops.common.utils.Collections3;
import com.eveb.saasops.common.utils.PageUtils;
import com.eveb.saasops.common.validator.Assert;
import com.eveb.saasops.modules.base.service.BaseService;
import com.eveb.saasops.modules.game.dao.TGmCaseDelMapper;
import com.eveb.saasops.modules.game.dao.TGmCaseapiDelMapper;
import com.eveb.saasops.modules.game.entity.TGmCaseDel;
import com.eveb.saasops.modules.game.entity.TGmCaseapiDel;
import com.eveb.saasops.modules.game.mapper.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.eveb.saasops.common.utils.DateUtil.FORMAT_18_DATE_TIME;
import static com.eveb.saasops.common.utils.DateUtil.getCurrentDate;


@Service
public class TGmCaseDelService extends BaseService<TGmCaseDelMapper, TGmCaseDel> {

    @Autowired
    private GameMapper gameMapper;
    @Autowired
    private TGmCaseapiDelMapper tGmCaseapiDelMapper;
    @Autowired
    private TGmCaseDelMapper tGmCaseDelMapper;

    public PageUtils queryListPage(TGmCaseDel tGmCaseDel, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(gameMapper.listTGmCaseDel(tGmCaseDel));
    }

    @Transactional
    public int save(TGmCaseDel tGmCaseDel) {
        tGmCaseDel.setCreateUser(getUser().getUsername());
        tGmCaseDel.setCreateTime(getCurrentDate(FORMAT_18_DATE_TIME));
        isNullSaveOrUpdate(tGmCaseDel);
        //添加API组
        super.insertSelective(tGmCaseDel);
        //添加API关系表
        tGmCaseDel.getTGmCaseapiDelList().stream().forEach(e -> {
            e.setCaseName(tGmCaseDel.getCaseName());
        });
        return tGmCaseapiDelMapper.insertList(tGmCaseDel.getTGmCaseapiDelList());

    }

    @Transactional
    public int update(TGmCaseDel tGmCaseDel) {
        tGmCaseDel.setModifyUser(getUser().getUsername());
        tGmCaseDel.setModifyTime(getCurrentDate(FORMAT_18_DATE_TIME));
        TGmCaseapiDel caseapiDel = new TGmCaseapiDel();
        caseapiDel.setCaseId(tGmCaseDel.getId());
        tGmCaseapiDelMapper.delete(caseapiDel);
        if (Collections3.isNotEmpty(tGmCaseDel.getTGmCaseapiDelList())) {
            tGmCaseDel.getTGmCaseapiDelList().stream().forEach(e -> {
                e.setCaseName(tGmCaseDel.getCaseName());
                e.setCaseId(tGmCaseDel.getId());
            });
            tGmCaseapiDelMapper.insertList(tGmCaseDel.getTGmCaseapiDelList());
        }
        isNullSaveOrUpdate(tGmCaseDel);
        //修改API组
        return super.update(tGmCaseDel);
    }

    public int updateAvailable(TGmCaseDel tGmCaseDel) {
        tGmCaseDel.setModifyUser(getUser().getUsername());
        tGmCaseDel.setModifyTime(getCurrentDate(FORMAT_18_DATE_TIME));
        //修改API组
        return tGmCaseDelMapper.updateByPrimaryKeySelective(tGmCaseDel);
    }

    public void deleteBatch(TGmCaseDel tGmCaseDel) {
        Assert.isNull(tGmCaseDel, "不能为空");
        String idStr = Arrays.stream(tGmCaseDel.getIds()).boxed().map(i -> i.toString())
                .collect(Collectors.joining(","));
        tGmCaseDelMapper.deleteByIds(idStr);
    }

    private void isNullSaveOrUpdate(@RequestBody TGmCaseDel tGmCaseDel) {
        Assert.isBlank(tGmCaseDel.getCaseName(), "方案名称不能为空");
        Assert.isNull(tGmCaseDel.getAvailable(), "状态不能为空");
    }
}
