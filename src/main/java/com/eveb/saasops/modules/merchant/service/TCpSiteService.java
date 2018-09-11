package com.eveb.saasops.modules.merchant.service;

import com.eveb.saasops.common.exception.RRException;
import com.eveb.saasops.common.utils.BeanUtil;
import com.eveb.saasops.common.utils.PageUtils;
import com.eveb.saasops.modules.base.service.BaseService;
import com.eveb.saasops.modules.merchant.dao.TCpSiteMapper;
import com.eveb.saasops.modules.merchant.entity.TCpSite;
import com.eveb.saasops.modules.merchant.mapper.MerchantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.eveb.saasops.common.utils.DateUtil.FORMAT_18_DATE_TIME;
import static com.eveb.saasops.common.utils.DateUtil.getCurrentDate;


@Service
public class TCpSiteService extends BaseService<TCpSiteMapper, TCpSite> {

    public static Map<String, String> schemaName;

    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private TCpSiteMapper tCpSiteMapper;

    public PageUtils queryListPage(TCpSite tCpSite, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(merchantMapper.listTCpSite(tCpSite));
    }

    public int save(TCpSite tCpSite) {
        tCpSite.setCreateUser(getUser().getUsername());
        tCpSite.setCreateTime(getCurrentDate(FORMAT_18_DATE_TIME));
        List<String> siteNameList = tCpSiteMapper.selectAll().stream().filter(e ->
                e.getSiteName().equals(tCpSite.getSiteName())).map(TCpSite::getSiteName).collect(Collectors.toList());
        if (siteNameList.size() > 0) {
            throw new RRException("此站点已存在，不能添加相同的站点!");
        }
        return tCpSiteMapper.insertSelective(tCpSite);
    }

    public int update(TCpSite tCpSite) {
        tCpSite.setModifyUser(getUser().getUsername());
        tCpSite.setModifyTime(getCurrentDate(FORMAT_18_DATE_TIME));
        return tCpSiteMapper.updateByPrimaryKeySelective(tCpSite);
    }

    public void deleteBatch(TCpSite tCpSite) {
        String idStr = Arrays.stream(tCpSite.getIds()).boxed().map(i -> i.toString())
                .collect(Collectors.joining(","));
        tCpSiteMapper.deleteByIds(idStr);
    }

    public PageUtils paymentList(String siteCode, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(merchantMapper.paymentList(siteCode));
    }

    public PageUtils reversePaymentList(String siteCode, String payCode, String payClass, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(merchantMapper.reversePaymentList(siteCode, payCode, payClass));
    }

    public PageUtils domainList(String siteCode, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return BeanUtil.toPagedResult(merchantMapper.domainList(siteCode));
    }

}
