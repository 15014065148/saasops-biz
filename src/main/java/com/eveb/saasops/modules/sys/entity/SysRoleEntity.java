package com.eveb.saasops.modules.sys.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class SysRoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建者ID
     */
    private Long createUserId;

    private List<Long> menuIdList;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 显示名称
     */
    private String roleNickName;
    /**
     * 用户数量
     */
    private Integer userNum;

    private Integer deptId;

    private String createUser;

    private Integer isEnable;

    private Long[] roleIds;

    @ApiModelProperty(value = "最后一次修改人的账号")
    private String modifyUser;

    @ApiModelProperty(value = "最后一次修改时间")
    private String modifyTime;

}
