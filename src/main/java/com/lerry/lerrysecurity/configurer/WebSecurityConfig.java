package com.lerry.lerrysecurity.configurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.annotation.Resource;

import static com.lerry.lerrysecurity.common.ProjectConstant.LOGIN_URL;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *  spring security 配置
 *  prePostEnabled = true 属性设置后控制器层的方法前的@PreAuthorize("hasRole('admin')") 注解才能起效。
 * @author LErry.li
 * Date: 2018/7/8
 * Time: 下午6:33
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 登录成功后的处理类
     */
    @Resource
    private LoginSuccessHandler loginSuccessHandler;

    /**
     * 获取登录路径需要的权限
     */
    @Autowired
    private UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;

    /**
     * 预调用处理
     */
    @Autowired
    UrlAccessDecisionManager urlAccessDecisionManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource);
                        o.setAccessDecisionManager(urlAccessDecisionManager);
                        return o;
                    }
                })
                //登陆接口不需要登陆也可访问
                .antMatchers(LOGIN_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                // 自定义的登录接口
                .loginProcessingUrl("/login")
                //登录成功后可使用loginSuccessHandler存储用户信息，可选。
                .successHandler(loginSuccessHandler)
                .and()
                .csrf()
                .disable();
    }

}
