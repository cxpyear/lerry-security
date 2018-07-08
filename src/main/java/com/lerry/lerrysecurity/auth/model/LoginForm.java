package com.lerry.lerrysecurity.auth.model;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *  登录提交数据
 * @author LErry.li
 * Date: 2018/7/8
 * Time: 下午2:09
 */
@Getter
@Setter
public class LoginForm {

    @NotBlank(message="用户名不能为空")
    private String username;

    @NotBlank(message="密码不能为空")
    private String password;

}
