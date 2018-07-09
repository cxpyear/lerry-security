package com.lerry.lerrysecurity.auth.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author LErry.li
 * Date: 2018/7/8
 * Time: 下午3:58
 */
@Setter
@Getter
public class AppUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String password;

    /**
     * 帐户是否过期
     */
    private boolean isAccountNonExpired = true;

    /**
     * 帐户是否被冻结
     */
    private boolean isAccountNonLocked = true;

    /**
     * 帐户密码是否过期，一般有的密码要求性高的系统会使用到，每隔一段时间就要求用户重置密码
     */
    private boolean isCredentialsNonExpired = true;

    /**
     *  帐号是否可用
     */
    private boolean isEnabled = true;

    private SysUser user;

    /**
     * 权限信息
     */
    private Set<GrantedAuthority> authorities = new HashSet<>();

}
