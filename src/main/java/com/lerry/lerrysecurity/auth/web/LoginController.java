package com.lerry.lerrysecurity.auth.web;

import com.lerry.lerrysecurity.auth.model.LoginForm;
import com.lerry.lerrysecurity.auth.model.SysUser;
import com.lerry.lerrysecurity.auth.service.SysUserService;
import com.lerry.lerrysecurity.common.ProjectConstant;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author LErry.li
 * Date: 2018/7/8
 * Time: 下午2:09
 */
@RestController
@RequestMapping("/")
public class LoginController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 登入
     * @param form
     * @return
     */
    @PostMapping("login")
    public SysUser login(@Valid @RequestBody LoginForm form){
        return sysUserService.login(form);
    }

    /**
     * 登出
     * @param request
     * @param response
     * @return
     */
    @GetMapping
    public String logOut(HttpServletRequest request, HttpServletResponse response) {
        String result = ProjectConstant.SUCCESS;
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
        }catch (Exception e) {
            result = ProjectConstant.FAILURE;
        }
        return result;
    }

}
