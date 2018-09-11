package com.eveb.saasops.modules.game.entity;

import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import javax.persistence.*;


@Setter
@Getter
@ApiModel(value = "TGmCaseapiDel", description = "")
@Table(name = "t_gm_caseapi_del")
public class TGmCaseapiDel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "方案号ID")
    private Integer caseId;

    @ApiModelProperty(value = "方案名称")
    private String caseName;

    @ApiModelProperty(value = "API ID")
    private Integer apiId;

    @ApiModelProperty(value = "API名称")
    private String apiName;

    @Transient
    @ApiModelProperty(value = "批量删除ids")
    private int[] ids;
}