package com.eveb.saasops.modules.operate.mapper;

import com.eveb.saasops.modules.operate.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OperateMapper {
    /**
     * 根据条件查询活动分类列表
     *
     * @return
     */
    List<TOpActtmpl> listTOpActtmpl(TOpActtmpl tOpActtmpl);

    /**
     * 根据条件查询活银行管理列表
     *
     * @return
     */
    List<TBsBank> listTBsBank(TBsBank tBsBank);

    /**
     * 根据条件查询支付平台列表
     *
     * @return
     */
    List<TOpPay> listTOpPay(TOpPay tOpPay);

    /**
     * 根据条件查询公告列表
     *
     * @return
     */
    List<TOprNotice> listTOprNotice(TOprNotice tOprNotice);

    /**
     * 根据支付平台ID查询商户站点
     *
     * @return
     */
    List<TOpPaywebsiterelation> getTOpPaywebsiterelation(@Param("paymentId") Integer paymentId);
}
