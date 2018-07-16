package com.lerry.lerrysecurity.configurer;

import com.lerry.lerrysecurity.common.exception.PermissionForbiddenException;
import com.lerry.lerrysecurity.common.exception.UserNotLoginException;
import com.lerry.lerrysecurity.common.result.ResultCode;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *  预调用处理
 *  Spring Security提供了拦截器来控制对安全对象的访问，例如方法调用或Web请求。
 *  关于是否允许调用进行的预调用决定是由AccessDecisionManager。
 * @author LErry.li
 * Date: 2018-07-10
 * Time: 11:59
 */
@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {

    /**
     *
     * @param authentication 当前登录用户的角色信息
     * @param o
     * @param collection UrlFilterInvocationSecurityMetadataSource中的getAttributes方法传来的，表示当前请求需要的角色
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection){
        Iterator<ConfigAttribute> iterator = collection.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute ca = iterator.next();
            //当前请求需要的权限
            String needRole = ca.getAttribute();
            if ("ROLE_LOGIN".equals(needRole)) {
                if (authentication instanceof AnonymousAuthenticationToken) {
                    //用户未登录
                    throw new UserNotLoginException(ResultCode.USER_NOT_LOGGED_IN);
                } else{
                    return;
                }
            }
            //当前用户所具有的权限
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            //如果当前用户所拥有的权限中包含一个访问该方法所需要的权限，则可以访问该方法
            boolean result = authorities.stream()
                    .anyMatch(authority -> authority.getAuthority().equals(needRole));
            if (result) {
                return;
            }
        }
        //无访问权限
        throw new PermissionForbiddenException(ResultCode.PERMISSION_NO_ACCESS);
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
