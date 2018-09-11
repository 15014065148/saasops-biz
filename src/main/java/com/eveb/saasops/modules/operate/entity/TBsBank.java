package com.eveb.saasops.modules.operate.entity;

import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import javax.persistence.*;


@Setter
@Getter
@ApiModel(value = "TBsBank", description = "银行管理")
@Table(name = "t_bs_bank")
public class TBsBank implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "银行名称")
    private String bankName;

    @ApiModelProperty(value = "银行名称简称")
    private String bankNameAbbreviation;

    @ApiModelProperty(value = "银行编码")
    private String bankCode;

    @ApiModelProperty(value = "logo")
    private String bankLog;

    @ApiModelProperty(value = "备注")
    private String memo;

    @ApiModelProperty(value = "是否支持取款(1,是，0否)")
    private Byte wDEnable;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "修改人")
    private String modifyUser;

    @ApiModelProperty(value = "修改时间")
    private String modifyTime;

    @ApiModelProperty(value = "公司收款银行(1,是，0否)")
    private Byte comePanyBankEnable;

    @ApiModelProperty(value = "会员提款银行(1,是，0否)")
    private Byte memberBankEnable;

    @Transient
    @ApiModelProperty(value = "支付平台数")
    private Integer nums;

    @ApiModelProperty(value = "银行状态　1开启，0禁用")
    private Integer available;

    @Transient
    @ApiModelProperty(value = "批量删除ids")
    private int[] ids;
}