package com.eveb.saasops.modules.game.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Setter
@Getter
@ApiModel(value = "TGmDepot", description = "平台类别名称")
@Table(name = "t_gm_depot")
public class TGmDepot implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "自增长字段")
    private String depotCode;

    @ApiModelProperty(value = "平台名称")
    private String depotName;

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

    @ApiModelProperty(value = "游戏开始时间")
    private String startDate;

    @ApiModelProperty(value = "游戏结束时间")
    private String endDate;

    @ApiModelProperty(value = "排序号")
    private Integer sortId;

    @Transient
    private String logo;

    @Transient
    private String mbPicUrl;
    @ApiModelProperty(value = "是否有第三方账号")
    @Transient
    private Byte hasAccount;

    @Transient
    @ApiModelProperty(value = "API 名称")
    private String apiName;

    @Transient
    @ApiModelProperty(value = "开通API数量")
    private String apiCount;

    @Transient
    @ApiModelProperty(value = "批量删除ids")
    private int[] ids;
}