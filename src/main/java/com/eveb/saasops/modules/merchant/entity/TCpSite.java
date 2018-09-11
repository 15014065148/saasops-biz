package com.eveb.saasops.modules.merchant.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Setter
@Getter
@ApiModel(value = "TCpSite", description = "站点表")
@Table(name = "t_cp_site")
public class TCpSite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "")
    private String siteCode;

    @ApiModelProperty(value = "")
    private String siteName;

    @ApiModelProperty(value = "")
    private String schemaName;

    @ApiModelProperty(value = "包网还是API客户，0：包网，1：API")
    private Byte isapi;

    @ApiModelProperty(value = "货币代码，使用的货币类型")
    private String currency;

    @ApiModelProperty(value = "商户名称 ID")
    private Integer companyId;

    @ApiModelProperty(value = "开始时间")
    private String startDate;

    @ApiModelProperty(value = "结束时间")
    private String endDate;

    @ApiModelProperty(value = "状态　1开启，0禁用")
    private Byte available;

    @ApiModelProperty(value = "交站时间")
    private String useTime;

    @ApiModelProperty(value = "备注")
    private String memo;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "最后一次修改人的账号")
    private String modifyUser;

    @ApiModelProperty(value = "最后一次修改时间")
    private String modifyTime;

    @ApiModelProperty(value = "")
    private String companyUser;

    @Transient
    @ApiModelProperty(value = "商户全称")
    private String companyFname;

    @Transient
    @ApiModelProperty(value = "批量删除ids")
    private int[] ids;

    @Transient
    @ApiModelProperty(value = "批量删除ids")
    private List<Integer> availableList;

    @Transient
    @ApiModelProperty(value = "api数量")
    private Integer apiCount;

    @Transient
    @ApiModelProperty(value = "域名数量")
    private Integer domainCount;

    @Transient
    @ApiModelProperty(value = "客户端类型 1App 0PC")
    private Integer clientType;

    @Transient
    @ApiModelProperty(value = "域名")
    private String siteUrl;

    @Transient
    @ApiModelProperty(value = "支付编码")
    private String payCode;

    @Transient
    @ApiModelProperty(value = "支付类型")
    private String payClass;

    @Transient
    @ApiModelProperty(value = "支付url")
    private String payUrl;

    @Transient
    @ApiModelProperty(value = "支付方式id")
    private Integer paymentId;

    @Transient
    @ApiModelProperty(value = "支付方式")
    private String payment;

    @Transient
    @ApiModelProperty(value = "关联id")
    private Integer relateId;
}