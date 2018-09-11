package com.eveb.saasops.modules.merchant.service;

import com.eveb.saasops.common.utils.BeanUtil;
import com.eveb.saasops.common.utils.PageUtils;
import com.eveb.saasops.modules.base.service.BaseService;
import com.eveb.saasops.modules.merchant.dao.TCpSiteMapper;
import com.eveb.saasops.modules.merchant.entity.TCpSite;
import com.eveb.saasops.modules.merchant.entity.TCpSiteUrl;
import com.eveb.saasops.modules.merchant.mapper.MerchantMapper;
import com.eveb.saasops.modules.merchant.mapper.TCpSiteUrlMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.eveb.saasops.common.utils.DateUtil.FORMAT_18_DATE_TIME;
import static com.eveb.saasops.common.utils.DateUtil.getCurrentDate;


@Service
public class TCpSiteUrlService extends BaseService<TCpSiteMapper, TCpSite> {

    @Autowired
    private TCpSiteUrlMapper tCpSiteUrlMapper;

    @Autowired
    private TCpSiteUrlMapper siteUrlMapper;

    public PageUtils queryListPage(TCpSiteUrl siteUrl, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(siteUrlMapper.listSiteUrl(siteUrl));
    }

    public PageUtils findDomainListPage(String siteId, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(siteUrlMapper.findDomainListPage(siteId));
    }

    public int deleteBatch(Integer[] ids){
        List<Integer> list = Arrays.asList(ids);
        return siteUrlMapper.deleteBatch(list);
    }

    public int closeOrOpen(Integer id, Integer available){
        return siteUrlMapper.closeOrOpen(id, available);
    }

    public int addInfo(TCpSiteUrl siteUrl){
        return siteUrlMapper.addInfo(siteUrl);
    }

}
