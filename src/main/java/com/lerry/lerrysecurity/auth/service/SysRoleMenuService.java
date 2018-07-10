package com.lerry.lerrysecurity.auth.service;
import com.lerry.lerrysecurity.auth.dto.RoleMenuDTO;
import com.lerry.lerrysecurity.auth.model.SysMenu;
import com.lerry.lerrysecurity.auth.model.SysRoleMenu;
import com.lerry.lerrysecurity.common.service.Service;

import java.util.List;


/**
 * Created with CodeGenerator
 * Description:
 * @author  LErry.li
 * Date: 2018年7月8日
 * Time: 下午3:50:28
 */
public interface SysRoleMenuService extends Service<SysRoleMenu> {

    /**
     * 根据组件获取组件需要的角色
     * @param menu 组件
     * @return
     */
   List<RoleMenuDTO> getRoleMenuList(SysMenu menu);

}
