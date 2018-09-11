package com.eveb.saasops.modules.game.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "UploadFileDto", description = "上传文件")
public class UploadFileDto {

    @ApiModelProperty(value = "上传文件名")
    private String fileName;
    @ApiModelProperty(value = "上传用户")
    private String username;
}
