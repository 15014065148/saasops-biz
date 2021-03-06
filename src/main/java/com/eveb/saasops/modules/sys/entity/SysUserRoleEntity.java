package com.eveb.saasops.modules.sys.entity;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class SysUserRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 角色ID
	 */
	private Long roleId;

}
