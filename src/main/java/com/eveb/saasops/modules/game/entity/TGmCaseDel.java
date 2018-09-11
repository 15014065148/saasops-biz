package com.eveb.saasops.modules.game.entity;

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
@ApiModel(value = "TGmCaseDel", description = "")
@Table(name = "t_gm_case_del")
public class TGmCaseDel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "方案CODE")
    private String caseCode;

    @ApiModelProperty(value = "方案名称")
    private String caseName;

    @ApiModelProperty(value = "1开启，0禁用")
    private Integer available;

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

    @Transient
    @ApiModelProperty(value = "平台代码")
    private Integer depotId;

    @Transient
    @ApiModelProperty(value = "API数")
    private Integer apiNum;

    @Transient
    private List<TGmCaseapiDel> tGmCaseapiDelList;

    @Transient
    @ApiModelProperty(value = "批量删除ids")
    private int[] ids;


}