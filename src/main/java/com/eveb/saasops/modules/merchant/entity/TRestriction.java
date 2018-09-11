package com.eveb.saasops.modules.merchant.entity;

import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import javax.persistence.*;


@Setter
@Getter
@ApiModel(value = "TRestriction", description = "访问控制")
@Table(name = "t_restriction")
public class TRestriction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "站点id")
    private Integer siteId;

    @ApiModelProperty(value = "限制区域")
    private String limitArea;

    @ApiModelProperty(value = "生效起始时间")
    private String startDate;

    @ApiModelProperty(value = "生效结束时间")
    private String endDate;

    @ApiModelProperty(value = "状态（1，启用  0，禁用）")
    private Integer available;

    @ApiModelProperty(value = "修改人")
    private String modifyUser;

    @ApiModelProperty(value = "修改时间")
    private String modifyTime;

    @ApiModelProperty(value = "备注")
    private String memo;

    @ApiModelProperty(value = "开始Ip")
    private String startIp;

    @ApiModelProperty(value = "结束IP")
    private String endIp;

    @Transient
    @ApiModelProperty(value = "站点名称")
    private String siteName;

    @Transient
    @ApiModelProperty(value = "批量删除ids")
    private int[] ids;

}