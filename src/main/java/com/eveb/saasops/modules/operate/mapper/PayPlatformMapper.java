package com.eveb.saasops.modules.operate.mapper;

import com.eveb.saasops.modules.operate.entity.TBsBank;
import com.eveb.saasops.modules.operate.entity.TOpPay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PayPlatformMapper {

    /**
     *  查询api配置列表
     *
     * @param pay
     * @return
     */
    List<TOpPay> queryPayListPage(TOpPay pay);

    /**
     * 站点下关闭或开启支付配置
     * @param relateId
     * @param available
     * @return
     */
    int closeOrOpen(@Param("relateId") Integer relateId, @Param("available") Integer available);

    /**
     * 通过ids删除支付配置
     * @param list
     * @return
     */
    int deleteByIds(@Param("list") List list);

    /**
     * 新增平台基础信息
     * @param pay
     * @return
     */
    int add(TOpPay pay);

    /**
     * 查询支付平台基本信息
     * @param payId
     * @return
     */
    TOpPay queryPayInfo(@Param("payId") Integer payId);

    /**
     *
     * @param payId
     * @return
     */
    List<TBsBank> queryBankList(@Param("payId") Integer payId);
}
