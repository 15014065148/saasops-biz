package com.eveb.saasops.modules.merchant.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Setter
@Getter
@ApiModel(value = "TCpSiteUrl", description = "域名")
@Table(name = "t_cp_siteurl")
public class TCpSiteUrl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "站点id")
    private Integer siteId;

    @ApiModelProperty(value = "站点编码")
    private String siteCode;

    @ApiModelProperty(value = "站点名称")
    private String siteName;

    @ApiModelProperty(value = "域名")
    private String siteUrl;

    @ApiModelProperty(value = "商户名称")
    private String companyName;

    @ApiModelProperty(value = "商户名称")
    private Integer domainCount;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "最后更新时间")
    private String modifyTime;

    @ApiModelProperty(value = "是否开启 1开启 0关闭")
    private Integer available;

    @ApiModelProperty(value = "客户端类型 1PC前台 2PC后台 3PC代理 4APP前台 5H5前台")
    private Integer clientType;

}