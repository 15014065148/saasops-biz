package com.eveb.saasops.modules.sys.mapper;

import com.eveb.saasops.common.utils.R;
import com.eveb.saasops.modules.sys.entity.SysRoleEntity;
import com.eveb.saasops.modules.sys.entity.SysUserEntity;
import com.eveb.saasops.modules.sys.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysMapper {
    /**
     * 根据条件查询用户列表
     *
     * @param sysUserEntity
     * @return
     */
    List<SysUserEntity> listSysUserEntity(SysUserEntity sysUserEntity);

    /**
     * 根据条件查询角色列表
     *
     * @param sysRoleEntity
     * @return
     */
    List<SysRoleEntity> listSysRoleEntity(SysRoleEntity sysRoleEntity);

}
