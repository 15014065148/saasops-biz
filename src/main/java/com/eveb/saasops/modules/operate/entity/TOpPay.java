package com.eveb.saasops.modules.operate.entity;

import com.eveb.saasops.modules.game.entity.TGmCaseapiDel;
import com.eveb.saasops.modules.merchant.entity.TCpSite;
import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Setter
@Getter
@ApiModel(value = "TOpPay", description = "")
@Table(name = "t_op_pay")
public class TOpPay implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "支付机构")
    private String payment;

    @ApiModelProperty(value = "")
    private String payClass;

    @ApiModelProperty(value = "")
    private String payWay;

    @ApiModelProperty(value = "支付方式 1：pc ，2：移动，3：pc+移动")
    private Integer payType;

    @ApiModelProperty(value = "是否启用 1启用 0 不启用")
    private Integer isEnable;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "修改人")
    private String modifyUser;

    @ApiModelProperty(value = "最后一次修改时间")
    private String modifyTime;

    @ApiModelProperty(value = "移动端支付渠道LOGO")
    private String mBankLog;

    @ApiModelProperty(value = "支付域名")
    private String tPayUrl;

    @ApiModelProperty(value = "回调域名")
    private String callbackUrl;

    @Transient
    @ApiModelProperty(value = "支持银行")
    private String bankNames;

    @Transient
    @ApiModelProperty(value = "站点名称")
    private String siteNames;

    @Transient
    @ApiModelProperty(value = "批量删除ids")
    private int[] ids;

    @Transient
    @ApiModelProperty(value = "支持银行")
    private List<TOpPaybankrelation> tOpPaybankrelationList;

    @Transient
    @ApiModelProperty(value = "商户站点")
    private List<TOpPaywebsiterelation> tOpPaywebsiterelationList;

}