package com.lerry.lerrysecurity.configurer;

import com.lerry.lerrysecurity.auth.dto.RoleMenuDTO;
import com.lerry.lerrysecurity.auth.model.SysMenu;
import com.lerry.lerrysecurity.auth.service.SysRoleMenuService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.lerry.lerrysecurity.common.ProjectConstant.LOGIN_URL;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * 通过当前的请求地址，获取该地址需要的角色
 *
 * @author LErry.li
 * Date: 2018-07-10
 * Time: 10:37
 */
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Resource
    private SysRoleMenuService roleMenuService;

    /**
     * 获取访问路径所需要的角色
     *
     * @param object
     * @return
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) {
        FilterInvocation fi = (FilterInvocation) object;
        String requestUrl = fi.getRequestUrl();
        String httpMethod = fi.getRequest().getMethod();
        if (LOGIN_URL.equals(requestUrl) || "/".equals(requestUrl)) {
            return Collections.emptyList();
        }
        SysMenu menu = new SysMenu();
        menu.setType(2);
        String[] roleCodes = null;
        List<RoleMenuDTO> roleMenuDTOList = roleMenuService.getRoleMenuList(menu);
        if (roleMenuDTOList != null && !roleMenuDTOList.isEmpty()) {
            int firstQuestionMarkIndex = requestUrl.indexOf("?");
            if (firstQuestionMarkIndex != -1) {
                requestUrl = requestUrl.substring(0, firstQuestionMarkIndex);
            }
            String request = requestUrl;
            AntPathMatcher antPathMatcher = new AntPathMatcher();
            roleCodes = roleMenuDTOList.stream()
                    .filter(roleMenu -> httpMethod.equals(roleMenu.getMethod()) && antPathMatcher.match(roleMenu.getUrl(), request))
                    .map(RoleMenuDTO::getRoleCode)
                    .distinct()
                    .toArray(String[]::new);
        }

        //没有匹配上的资源，都是登录访问
        if (roleCodes == null || roleCodes.length <= 0) {
            roleCodes = new String[]{"ROLE_LOGIN"};
        }
        return SecurityConfig.createList(roleCodes);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return Collections.emptyList();
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
