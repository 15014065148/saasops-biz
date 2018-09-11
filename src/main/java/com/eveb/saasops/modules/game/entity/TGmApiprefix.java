package com.eveb.saasops.modules.game.entity;

import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import javax.persistence.*;


@Setter
@Getter
@ApiModel(value = "TGmApiprefix", description = "线路前缀")
@Table(name = "t_gm_apiprefix")
public class TGmApiprefix implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "线路id")
    private Integer apiId;

    @Transient
    @ApiModelProperty(value = "线路名称")
    private String apiName;

    @ApiModelProperty(value = "线路前缀")
    private String prefix;

    @ApiModelProperty(value = "站点id")
    private Integer siteId;

    @Transient
    @ApiModelProperty(value = "站点Code")
    private String siteCode;

    @Transient
    @ApiModelProperty(value = "站点名称")
    private String siteName;

    @Transient
    @ApiModelProperty(value = "站点前缀")
    private String schemaName;

    @Transient
    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @Transient
    @ApiModelProperty(value = "api数量")
    private String apiCount;

    @ApiModelProperty(value = "1开启，0禁用")
    private Integer available;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "修改人")
    private String modifyUser;

    @ApiModelProperty(value = "修改时间")
    private String modifyTime;

    @Transient
    @ApiModelProperty(value = "游戏分类")
    private String catName;

    @Transient
    @ApiModelProperty(value = "平台名称")
    private String depotName;
}