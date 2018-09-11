package com.eveb.saasops.modules.merchant.mapper;

import com.eveb.saasops.modules.merchant.entity.TCpCompany;
import com.eveb.saasops.modules.merchant.entity.TCpSite;
import com.eveb.saasops.modules.merchant.entity.TCpSiteUrl;
import com.eveb.saasops.modules.merchant.entity.TRestriction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TCpSiteUrlMapper {

    /**
     * 查询站点信息及其域名数量
     * @param siteUrl
     * @return
     */
    List<TCpSiteUrl> listSiteUrl(TCpSiteUrl siteUrl);

    /**
     * 查询站点下域名列表
     * @param siteId
     * @return
     */
    List<TCpSiteUrl> findDomainListPage(@Param("siteId") String siteId);

    /**
     * 批量删除站点下域名
     * @param list
     * @return
     */
    int deleteBatch(@Param("list") List<Integer> list);

    /**
     * 关闭或开启域名
     * @param id
     * @param available
     * @return
     */
    int closeOrOpen(@Param("id") Integer id, @Param("available") Integer available);

    /**
     * 新增域名
     * @param siteUrl
     * @return
     */
    int addInfo(TCpSiteUrl siteUrl);
}
