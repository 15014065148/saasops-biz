package com.eveb.saasops.modules.merchant.entity;

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
@ApiModel(value = "TCpCompany", description = "商户列表")
@Table(name = "t_cp_company")
public class TCpCompany implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "")
    private String companyCode;

    @ApiModelProperty(value = "商户简称")
    private String companySname;

    @ApiModelProperty(value = "商户全称")
    private String companyFname;

    @ApiModelProperty(value = "开始时间")
    private String startDate;

    @ApiModelProperty(value = "结束时间")
    private String endDate;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "联系人")
    private String contact;

    @ApiModelProperty(value = "邮箱地址")
    private String email;

    @ApiModelProperty(value = "国家代码")
    private String countryCode;

    @ApiModelProperty(value = "电话号码")
    private String phoneNumber;

    @ApiModelProperty(value = "商户LOGO url")
    private String cpLogo;

    @ApiModelProperty(value = "上级组织")
    private String organize;

    @ApiModelProperty(value = "状态　1开启，0禁用")
    private Byte available;

    @ApiModelProperty(value = "排序号")
    private Integer sortId;

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
    @ApiModelProperty(value = "站点数")
    private Integer siteNum;

    @Transient
    @ApiModelProperty(value = "批量删除ids")
    private int[] ids;

}