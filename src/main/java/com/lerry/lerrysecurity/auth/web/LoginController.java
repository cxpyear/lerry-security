package com.lerry.lerrysecurity.auth.web;

import com.lerry.lerrysecurity.auth.model.SysUser;
import com.lerry.lerrysecurity.auth.service.SysUserService;
import com.lerry.lerrysecurity.common.result.ResponseResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author LErry.li
 * Date: 2018/7/8
 * Time: 下午2:09
 */
@ResponseResult
@RestController
@RequestMapping("/user")
public class LoginController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 登入
     * @param userName 用户名
     * @param passWord 密码
     * @return
     */
    @PostMapping("/login")
    public SysUser login(@RequestParam(name = "username") String userName,
                         @RequestParam(name = "password") String passWord){
        return sysUserService.login(userName, passWord);
    }

    /**
     * 登出
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/logOut")
    public void logOut(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

    }

}
