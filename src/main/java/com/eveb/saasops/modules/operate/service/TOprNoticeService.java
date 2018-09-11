package com.eveb.saasops.modules.operate.service;

import com.eveb.saasops.common.utils.BeanUtil;
import com.eveb.saasops.common.utils.PageUtils;
import com.eveb.saasops.common.validator.Assert;
import com.eveb.saasops.modules.base.service.BaseService;
import com.eveb.saasops.modules.operate.dao.TOprNoticeMapper;
import com.eveb.saasops.modules.operate.entity.TOprNotice;
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
public class TOprNoticeService extends BaseService<TOprNoticeMapper, TOprNotice> {
    @Autowired
    private OperateMapper operateMapper;
    @Autowired
    private TOprNoticeMapper tOprNoticeMapper;

    public PageUtils queryListPage(TOprNotice tOprNotice, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(operateMapper.listTOprNotice(tOprNotice));
    }

    public int save(TOprNotice tOprNotice) {
        tOprNotice.setCreateUser(getUser().getUsername());
        tOprNotice.setCreateTime(getCurrentDate(FORMAT_18_DATE_TIME));
        isNullSaveOrUpdate(tOprNotice);
        return tOprNoticeMapper.insertSelective(tOprNotice);
    }

    public int update(TOprNotice tOprNotice) {
        tOprNotice.setUpdateUser(getUser().getUsername());
        tOprNotice.setUpdateTime(getCurrentDate(FORMAT_18_DATE_TIME));
        isNullSaveOrUpdate(tOprNotice);
        return tOprNoticeMapper.updateByPrimaryKeySelective(tOprNotice);
    }

    public int updateAvailable(TOprNotice tOprNotice) {
        tOprNotice.setUpdateUser(getUser().getUsername());
        tOprNotice.setUpdateTime(getCurrentDate(FORMAT_18_DATE_TIME));
        return tOprNoticeMapper.updateByPrimaryKeySelective(tOprNotice);
    }

    public void deleteBatch(TOprNotice tOprNotice) {
        Assert.isNull(tOprNotice, "不能为空");
        String idStr = Arrays.stream(tOprNotice.getIds()).boxed().map(i -> i.toString())
                .collect(Collectors.joining(","));
        tOprNoticeMapper.deleteByIds(idStr);
    }

    private void isNullSaveOrUpdate(@RequestBody TOprNotice tOprNotice) {
        Assert.isBlank(tOprNotice.getShowType(), "公告不能为空");
        Assert.isBlank(tOprNotice.getEndTime(), "结束时间不能为空");
        Assert.isBlank(tOprNotice.getStartTime(), "开始时间不能为空");
        Assert.isBlank(tOprNotice.getNoticeContent(), "公告内容不能为空");
    }

}
