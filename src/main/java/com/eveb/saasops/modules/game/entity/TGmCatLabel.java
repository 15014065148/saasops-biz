package com.eveb.saasops.modules.game.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "游戏类别", description = "中间表")
public class TGmCatLabel {
    @ApiModelProperty(value = "标签id 为 游戏类别的名称或标签的id")
    private Integer id;
    @JsonIgnore
    private Integer depotId;
    @ApiModelProperty(value = "标签名称  为 游戏类别的名称或标签的名称")
    private String lableName;
    @ApiModelProperty(value = "标识这个是标签还是类别")
    private String showType;
}
