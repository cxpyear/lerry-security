package com.lerry.lerrysecurity.auth.service;
import com.lerry.lerrysecurity.auth.model.SysRole;
import com.lerry.lerrysecurity.auth.model.SysUserRole;
import com.lerry.lerrysecurity.common.service.Service;

import java.util.List;


/**
 * Created with CodeGenerator
 * Description:
 * @author  LErry.li
 * Date: 2018年7月8日
 * Time: 下午3:50:28
 */
public interface SysUserRoleService extends Service<SysUserRole> {

    /**
     * 根据用户ID 查询用户的角色
     * @param userId 用户ID
     * @return 该用户所拥有的角色
     */
    List<SysRole> selectRoleByUserId(Long userId);


}
