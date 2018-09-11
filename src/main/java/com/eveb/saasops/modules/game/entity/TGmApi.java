package com.eveb.saasops.modules.game.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import javax.persistence.*;


@Setter
@Getter
@Data
@ApiModel(value = "TGmApi", description = "API 配置")
@Table(name = "t_gm_api")
public class TGmApi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "平台代码")
    private Integer depotId;

    @ApiModelProperty(value = "分类ID")
    private Integer catId;

    @ApiModelProperty(value = "api线路编码")
    private String circuitCode;

    @ApiModelProperty(value = "API接口名称")
    private String apiName;

    @ApiModelProperty(value = "PC端口接口地址1")
    private String pcUrl;

    @ApiModelProperty(value = "PC端接口地址2")
    private String pcUrl2;

    @ApiModelProperty(value = "移动接口地址1")
    private String mbUrl;

    @ApiModelProperty(value = "移动接口地址2")
    private String mbUrl2;

    @ApiModelProperty(value = "代理号")
    private String agyAcc;

    @ApiModelProperty(value = "密钥")
    private String md5Key;

    @ApiModelProperty(value = "安全码")
    private String secureCode;

    @ApiModelProperty(value = "网站名称(BBIN)")
    private String webName;

    @ApiModelProperty(value = "代理前缀代码")
    private String proxyFore;

    @ApiModelProperty(value = "排序号")
    private Integer sortId;

    @ApiModelProperty(value = "详细备注")
    private String memo;

    @ApiModelProperty(value = "1开启，0禁用")
    private Integer available;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "最后一次修改人的账号")
    private String modifyUser;

    @ApiModelProperty(value = "最后一次修改时间")
    private String modifyTime;

    @ApiModelProperty(value = "defApi")
    private Byte defApi;

    @Transient
    @ApiModelProperty(value = "APIid")
    private Integer apiId;

    @Transient
    @ApiModelProperty(value = "平台代码")
    private String depotName;

    @Transient
    @ApiModelProperty(value = "使用站点数")
    private String siteNumber;

    @Transient
    @ApiModelProperty(value = "序号")
    private Integer rowNo;

    @Transient
    @ApiModelProperty(value = "批量删除ids")
    private int[] ids;

    @Transient
    @ApiModelProperty(value = "游戏分类名称")
    private String catName;

    @Transient
    @ApiModelProperty(value = "配置id")
    private Integer prefixId;

    @Transient
    @ApiModelProperty(value = "站点缩写")
    private String schemaName;

    @Transient
    @ApiModelProperty(value = "站点id")
    private Integer siteId;

}