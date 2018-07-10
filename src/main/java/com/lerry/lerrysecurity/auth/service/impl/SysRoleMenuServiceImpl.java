package com.lerry.lerrysecurity.auth.service.impl;

import com.lerry.lerrysecurity.auth.dao.SysRoleMenuMapper;
import com.lerry.lerrysecurity.auth.dto.RoleMenuDTO;
import com.lerry.lerrysecurity.auth.model.SysMenu;
import com.lerry.lerrysecurity.auth.model.SysRoleMenu;
import com.lerry.lerrysecurity.auth.service.SysRoleMenuService;
import com.lerry.lerrysecurity.common.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created with CodeGenerator
 * Description:
 * @author  LErry.li
 * Date: 2018年7月8日
 * Time: 下午3:50:28
 */
@Service
@Transactional
public class SysRoleMenuServiceImpl extends AbstractService<SysRoleMenu> implements SysRoleMenuService {

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 根据类型获取组件需要的角色
     *
     * @param menu 组件
     * @return
     */
    @Override
    public List<RoleMenuDTO> getRoleMenuList(SysMenu menu) {
        return sysRoleMenuMapper.getRoleMenuList(menu);
    }
}
