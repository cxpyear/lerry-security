package com.lerry.lerrysecurity.auth.dao;

import com.lerry.lerrysecurity.auth.model.SysRole;
import com.lerry.lerrysecurity.auth.model.SysUserRole;
import com.lerry.lerrysecurity.common.mapper.CrudMapper;

import java.util.List;

public interface SysUserRoleMapper extends CrudMapper<SysUserRole> {

    /**
     * 根据用户ID 查询用户的角色
     * @param userId 用户ID
     * @return 该用户所拥有的角色
     */
    List<SysRole> selectRoleByUserId(Long userId);
}