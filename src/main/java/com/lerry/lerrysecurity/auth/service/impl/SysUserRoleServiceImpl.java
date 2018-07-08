package com.lerry.lerrysecurity.auth.service.impl;

import com.lerry.lerrysecurity.auth.dao.SysUserRoleMapper;
import com.lerry.lerrysecurity.auth.model.SysRole;
import com.lerry.lerrysecurity.auth.model.SysUserRole;
import com.lerry.lerrysecurity.auth.service.SysUserRoleService;
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
public class SysUserRoleServiceImpl extends AbstractService<SysUserRole> implements SysUserRoleService {

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 根据用户ID 查询用户的角色
     *
     * @param userId 用户ID
     * @return 该用户所拥有的角色
     */
    @Override
    public List<SysRole> selectRoleByUserId(Long userId) {
        return sysUserRoleMapper.selectRoleByUserId(userId);
    }

}
