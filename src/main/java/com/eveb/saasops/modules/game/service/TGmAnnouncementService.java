package com.eveb.saasops.modules.game.service;

import com.eveb.saasops.common.utils.BeanUtil;
import com.eveb.saasops.common.utils.PageUtils;
import com.eveb.saasops.common.validator.Assert;
import com.eveb.saasops.modules.base.service.BaseService;
import com.eveb.saasops.modules.game.dao.TGmAnnouncementMapper;
import com.eveb.saasops.modules.game.entity.TGmAnnouncement;
import com.eveb.saasops.modules.game.mapper.GameMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.eveb.saasops.common.utils.DateUtil.FORMAT_18_DATE_TIME;
import static com.eveb.saasops.common.utils.DateUtil.getCurrentDate;

@Service
@Transactional
public class TGmAnnouncementService extends BaseService<TGmAnnouncementMapper, TGmAnnouncement> {
    @Autowired
    private GameMapper gameMapper;
    @Autowired
    private TGmAnnouncementMapper gmAnnouncementMapper;

    public int saveAnnouncement(TGmAnnouncement tGmAnnouncement) {
        isAllNull(tGmAnnouncement);
        tGmAnnouncement.setSigned(getUser().getUsername());
        tGmAnnouncement.setModifyTime(getCurrentDate(FORMAT_18_DATE_TIME));
        return gameMapper.saveAnnouncement(tGmAnnouncement);
    }

    public PageUtils queryListPage(TGmAnnouncement tGmAnnouncement, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(gameMapper.selectAnnouncementList(tGmAnnouncement));
    }

    /**
     * 验证必填参数
     *
     * @param tGmAnnouncement
     */
    private void isAllNull(TGmAnnouncement tGmAnnouncement) {
        Assert.isNull(tGmAnnouncement.getDepotId(), "平台ID不能为空！");
        Assert.isNull(tGmAnnouncement.getAvailable(), "显示效果不能为空！");
        Assert.isNull(tGmAnnouncement.getAnnouncementMemo(), "公告内容不能为空！");
        Assert.isNull(tGmAnnouncement.getMaintainEndTime(), "维护结束时间不能为空！");
        Assert.isNull(tGmAnnouncement.getMaintainStartTime(), "维护开始时间不能为空！");
        Assert.isNull(tGmAnnouncement.getAnnouncementEndTime(), "公告结束时间不能为空！");
        Assert.isNull(tGmAnnouncement.getAnnouncementStartTime(), "公告开始时间不能为空！");
    }
}
