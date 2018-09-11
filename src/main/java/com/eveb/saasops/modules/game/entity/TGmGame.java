package com.eveb.saasops.modules.game.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Setter
@Getter
@ApiModel(value = "TGmGame", description = "平台游戏列表")
@Table(name = "t_gm_game")
public class TGmGame implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "游戏类别代码")
    private Integer catId;

    @ApiModelProperty(value = "游戏子类别代码")
    private Integer subCatId;

    @ApiModelProperty(value = "平台代码")
    private Integer depotId;

    @ApiModelProperty(value = "游戏代码")
    private String gameCode;

    @ApiModelProperty(value = "手机游戏代码")
    private String mbGameCode;

    @ApiModelProperty(value = "安卓游戏代码")
    private String adGameCode;

    @ApiModelProperty(value = "桌面应用游戏代码")
    private String downGameCode;

    @ApiModelProperty(value = "游戏名称")
    private String gameName;

    @ApiModelProperty(value = "定义描述")
    private String gameTag;

    @ApiModelProperty(value = "定义标题")
    private String titleTag;

    @ApiModelProperty(value = "游戏链接URL")
    private String gameParam;

    @ApiModelProperty(value = "1开启，0禁用")
    private Byte available;

    @ApiModelProperty(value = "个性化图片")
    private String logo;

    @ApiModelProperty(value = "开启PC端 1->开启，0－>禁用")
    private Byte enablePc;

    @ApiModelProperty(value = "开启移动端 1->开启，0－>禁用")
    private Byte enableMb;

    @ApiModelProperty(value = "开启测试账号 1->开启，0－>禁用")
    private Byte enableTest;

    @ApiModelProperty(value = "开启App 1->开启，0－>禁用")
    private Byte enableApp;

    @ApiModelProperty(value = "热门游戏")
    private Byte enableHot;

    @ApiModelProperty(value = "最新游戏")
    private Byte ebableNew;

    @ApiModelProperty(value = "开启奖金池 1->开启，0－>禁用")
    private Byte enablePool;

    @ApiModelProperty(value = "详细备注")
    private String memo;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "最后一次修改人的账号")
    private String modifyUser;

    @ApiModelProperty(value = "最后一次修改时间")
    private String modifyTime;

    @ApiModelProperty(value = "赔付线数")
    private Integer compensateNum;

    @ApiModelProperty(value = "游戏名称(英文)")
    private String gameNameEn;

    @ApiModelProperty(value = "游戏ID")
    private Integer gameId;

    @ApiModelProperty(value = "URL")
    private String url;

    @ApiModelProperty(value = "PC端链接标识")
    private String pcUrlTag;

    @ApiModelProperty(value = "HTML5链接标识")
    private String htmlTag;

    @ApiModelProperty(value = "推荐指数")
    private Integer recRating;

    @ApiModelProperty(value = "奖池类型")
    private String poolCat;

    @ApiModelProperty(value = "奖金池参数")
    private String poolParam;

    @ApiModelProperty(value = "非电子 显示标识")
    private Byte topLink;

    @ApiModelProperty(value = "排序")
    private Integer sortId;

    @ApiModelProperty(value = "点击量")
    private Integer clickNum;

    @ApiModelProperty(value = "好评度")
    private Integer goodNum;

    @ApiModelProperty(value = "过去一个月这个游戏玩家数")
    private Integer monthPer;

    @ApiModelProperty(value = "昨天该游戏玩家数")
    private Integer lastdayPer;

    @ApiModelProperty(value = "游戏平台名称")
    private String depotName;

    @Transient
    @ApiModelProperty(value = "游戏类别名称")
    private String catName;

    @Transient
    @ApiModelProperty(value = "分类统计游戏总数")
    private Integer gameCount;

    @Transient
    @ApiModelProperty(value = "分类统计玩家数量（30天）")
    private Integer tMonthPer;

    @Transient
    @ApiModelProperty(value = "分类统计玩家数量（昨天）")
    private Integer tLastDayPer;

    @Transient
    @ApiModelProperty(value = "批量删除ids")
    private int[] ids;

    @Transient
    @ApiModelProperty(value = "PC URL")
    private String picUrl;

    @Transient
    @ApiModelProperty(value = "移动端 URL")
    private String mbPicUrl;

    @Transient
    @ApiModelProperty(value = "APP URL")
    private String appPicUrl;

    @Transient
    @ApiModelProperty(value = "Mb个性化图片")
    private String logoMb;

    @Transient
    @ApiModelProperty(value = "App个性化图片")
    private String logoApp;

    @Transient
    @ApiModelProperty(value = "标签")
    private Integer[] labels;

    @Transient
    @ApiModelProperty(value = "标签 查询使用")
    private String labelStr;

    @Transient
    @ApiModelProperty(value = "线路名称")
    private String apiName;

    @ApiModelProperty(value = "游戏控制状态")
    private Integer status;
}