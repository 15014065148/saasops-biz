package com.eveb.saasops.modules.game.entity;

import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Setter
@Getter
@ApiModel(value = "TGmCode", description = "")
@Table(name = "t_gm_code")
public class TGmCode implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "平台")
    private String platform;

    @ApiModelProperty(value = "类型")
    private String codetype;

    @ApiModelProperty(value = "分组")
    private String codegroup;

    @ApiModelProperty(value = "ID")
    private String codeid;

    @ApiModelProperty(value = "名称")
    private String codename;

}