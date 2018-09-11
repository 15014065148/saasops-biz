package com.eveb.saasops.modules.game.entity;

import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Setter
@Getter
@ApiModel(value = "TGmAnnouncement", description = "平台维护")
@Table(name = "t_gm_announcement")
public class TGmAnnouncement implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "维护开始时间")
    private String maintainStartTime;

    @ApiModelProperty(value = "维护结束时间")
    private String maintainEndTime;

    @ApiModelProperty(value = "公告开始时间")
    private String announcementStartTime;

    @ApiModelProperty(value = "公告结束时间")
    private String announcementEndTime;

    @ApiModelProperty(value = "公告标题")
    private String announcementName;

    @ApiModelProperty(value = "公告内容")
    private String announcementMemo;

    @ApiModelProperty(value = "最后更新时间")
    private String modifyTime;

    @ApiModelProperty(value = "填写人")
    private String signed;

    @ApiModelProperty(value = "显示效果（0：公告(跑马灯)；1：广播(弹窗)）")
    private Integer available;

    @Id
    @ApiModelProperty(value = "平台代码")
    private Integer depotId;

    @Transient
    @ApiModelProperty(value = "平台名字")
    private String depotName;

}