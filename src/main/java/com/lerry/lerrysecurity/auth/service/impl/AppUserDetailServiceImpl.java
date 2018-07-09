package com.lerry.lerrysecurity.auth.service.impl;

import cn.hutool.core.lang.Assert;
import com.lerry.lerrysecurity.auth.model.AppUserDetails;
import com.lerry.lerrysecurity.auth.model.SysRole;
import com.lerry.lerrysecurity.auth.model.SysUser;
import com.lerry.lerrysecurity.auth.service.AppUserDetailService;
import com.lerry.lerrysecurity.auth.service.SysUserRoleService;
import com.lerry.lerrysecurity.auth.service.SysUserService;
import com.lerry.lerrysecurity.common.ConstantEnum;
import com.lerry.lerrysecurity.common.exception.DataNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author LErry.li
 * Date: 2018/7/8
 * Time: 下午2:45
 */
@Service
@Transactional
public class AppUserDetailServiceImpl implements AppUserDetailService {

    private final Logger logger = LoggerFactory.getLogger(AppUserDetailServiceImpl.class);

    @Resource
    private SysUserService userService;

    @Resource
    private SysUserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws DataNotFoundException {
        Assert.notBlank(username, "用户名不能为空");
        Condition findByUserCondition = new Condition(SysUser.class);
        findByUserCondition.createCriteria()
                .andEqualTo("username",username)
        .andIn("status",Arrays.asList(0,1));
        //根据条件获取用户
        List<SysUser> userList = userService.findByCondition(findByUserCondition);
        SysUser user = null;
        if(userList != null && !userList.isEmpty()){
            user = userList.get(0);
        }
        if(user == null){
            throw new DataNotFoundException(username + " does not exist!");
        }else if(ConstantEnum.UserStatus.LOCKED.getKey().equals(user.getStatus())){
            throw new DataNotFoundException(username + " is locked, please contact the administrator! ");
        }
        //获取用户的角色
        List<SysRole> roleList = userRoleService.selectRoleByUserId(user.getId());
        //将用户信息包装到UserDetails返回
        AppUserDetails userDetails = new AppUserDetails();
        userDetails.setId(user.getId());
        userDetails.setUsername(user.getUsername());
        userDetails.setPassword(user.getPassword());
        Set<GrantedAuthority> authorities=new HashSet<>();
        if(roleList != null && !roleList.isEmpty()){
            for(SysRole role : roleList){
                authorities.add(new SimpleGrantedAuthority(role.getCode()));
            }
        }
        user.setLastLoginTime(new Date());
        userDetails.setUser(user);
        userDetails.setAuthorities(authorities);
        userService.update(user);
        return userDetails;
    }
}
