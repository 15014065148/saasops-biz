package com.eveb.saasops.modules.operate.entity;

import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Setter
@Getter
@ApiModel(value = "TOpPaywebsiterelation", description = "支付平台和站点")
@Table(name = "t_op_payWebSiteRelation")
public class TOpPayConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "支付平台Id")
    private Integer paymentId;

    @ApiModelProperty(value = "站点Id")
    private Integer siteId;

    @Transient
    @ApiModelProperty(value = "站点code")
    private String siteCode;

    @Transient
    @ApiModelProperty(value = "站点名称")
    private String siteName;

    @Transient
    @ApiModelProperty(value = "商户名称")
    private String companyName;

    @Transient
    @ApiModelProperty(value = "注册前缀")
    private String schemaName;

    @Transient
    @ApiModelProperty(value = "支付方式数量")
    private Integer paymentCount;

    @Transient
    @ApiModelProperty(value = "是否启用 1启用 0 不启用")
    private Integer available;

    @Transient
    @ApiModelProperty(value = "支付机构")
    private String payment;

    @Transient
    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "最后一次修改时间")
    private String modifyTime;
}