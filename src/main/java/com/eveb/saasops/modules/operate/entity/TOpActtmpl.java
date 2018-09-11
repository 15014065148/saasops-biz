package com.eveb.saasops.modules.operate.entity;

import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import javax.persistence.*;


@Setter
@Getter
@ApiModel(value = "TOpActtmpl", description = "活动分类")
@Table(name = "t_op_acttmpl")
public class TOpActtmpl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "活动分类编号")
    private String tmplCode;

    @ApiModelProperty(value = "活动分类名称")
    private String tmplName;

    @ApiModelProperty(value = "活动分类描述")
    private String tmplNameTag;

    @ApiModelProperty(value = "活动分类状态　1开启，0禁用")
    private Integer available;

    @ApiModelProperty(value = "活动分类排序号")
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
    @ApiModelProperty(value = "批量删除ids")
    private int[] ids;

}