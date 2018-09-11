package com.eveb.saasops.modules.sys.entity;

import com.eveb.saasops.common.validator.group.AddGroup;
import com.eveb.saasops.common.validator.group.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统用户
 */
@Setter
@Getter
public class SysUserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = AddGroup.class)
    //@JsonIgnore
    private String password;

    /**
     * 安全密码
     */
    @NotBlank(message = "安全密码不能为空", groups = AddGroup.class)
    //@JsonIgnore
    private String securepwd;

    @JsonIgnore
    private String salt;

    /**
     * 邮箱
     */
    //@NotBlank(message = "邮箱不能为空", groups = { AddGroup.class, UpdateGroup.class })
    @Email(message = "邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态 0：禁用 1：正常
     */
    private Integer status;

    /**
     * 角色ID列表
     */
    private List<Long> roleIdList;
    /**
     * 角色ID
     */
    private Integer roleId;

    private Integer deptId;

    /**
     * 创建者ID
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    private String expireTime;
    /**
     * 真实姓名
     */
    private String realName;
    // 1: 未删除 2:已删除
    private Integer isDelete;

    @Transient
    private String roleName;
    @Transient
    private String token;
    @Transient
    private String domainUrl;
    @Transient
    private String merchantId;

    @ApiModelProperty(value = "最后一次修改人的账号")
    private String modifyUser;

    @ApiModelProperty(value = "最后一次修改时间")
    private String modifyTime;

    @ApiModelProperty(value = "创建人")
    private String createUser;

}
