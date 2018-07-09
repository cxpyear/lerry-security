package com.lerry.lerrysecurity.configurer;

import com.lerry.lerrysecurity.auth.model.AppUserDetails;
import com.lerry.lerrysecurity.common.util.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *  登录成功后可以在这里处理一些东西
 * @author LErry.li
 * Date: 2018/7/9
 * Time: 下午8:30
 */

public class LoginSuccessHandler extends
        SavedRequestAwareAuthenticationSuccessHandler {

    private final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException,ServletException {
        //获得授权后可得到用户信息   可使用SUserService进行数据库操作
        AppUserDetails userDetails = (AppUserDetails)authentication.getPrincipal();
        //输出登录提示信息
        String info = String.format("用户：[%s] 成功登录；IP：%s",userDetails.getUsername(),IpUtil.getRealIp(request));
        logger.info(info);

        super.onAuthenticationSuccess(request, response, authentication);
    }

}
