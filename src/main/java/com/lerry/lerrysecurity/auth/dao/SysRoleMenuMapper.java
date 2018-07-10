package com.lerry.lerrysecurity.auth.dao;

import com.lerry.lerrysecurity.auth.dto.RoleMenuDTO;
import com.lerry.lerrysecurity.auth.model.SysMenu;
import com.lerry.lerrysecurity.auth.model.SysRoleMenu;
import com.lerry.lerrysecurity.common.mapper.CrudMapper;

import java.util.List;

public interface SysRoleMenuMapper extends CrudMapper<SysRoleMenu> {

    /**
     * 根据组件获取组件需要的角色
     * @param menu 组件
     * @return
     */
    List<RoleMenuDTO> getRoleMenuList(SysMenu menu);
}