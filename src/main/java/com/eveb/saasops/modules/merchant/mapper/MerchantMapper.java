package com.eveb.saasops.modules.merchant.mapper;

import com.eveb.saasops.modules.merchant.entity.TCpCompany;
import com.eveb.saasops.modules.merchant.entity.TCpSite;
import com.eveb.saasops.modules.merchant.entity.TRestriction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MerchantMapper {
    /**
     * 根据条件查询商户列表
     *
     * @param tCpCompany
     * @return
     */
    List<TCpCompany> listTCpCompany(TCpCompany tCpCompany);

    /**
     * 批量停用商户
     *
     * @param tCpCompany
     * @return
     */
    int updateAvailable(TCpCompany tCpCompany);

    /**
     * 根据条件查询站点列表
     *
     * @param tCpSite
     * @return
     */
    List<TCpSite> listTCpSite(TCpSite tCpSite);

    /**
     * 根据条件查询访问控制列表
     *
     * @param tRestriction
     * @return
     */
    List<TRestriction> listTRestriction(TRestriction tRestriction);

    /**
     * 查询支付平台配置列表siteCode
     * @param siteCode
     * @return
     */
    List<TCpSite> paymentList(@Param("siteCode") String siteCode);

    /**
     * 查询支付平台配置列表 不在站点下
     * @param siteCode
     * @return
     */
    List<TCpSite> reversePaymentList(@Param("siteCode") String siteCode, @Param("payCode") String payCode, @Param("payClass") String payClass);

    /**
     * 查询域名列表
     * @param siteCode
     * @return
     */
    List<TCpSite> domainList(@Param("siteCode") String siteCode);
}
