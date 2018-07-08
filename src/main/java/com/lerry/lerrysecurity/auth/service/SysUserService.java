package com.lerry.lerrysecurity.auth.service;
import com.lerry.lerrysecurity.auth.model.LoginForm;
import com.lerry.lerrysecurity.auth.model.SysUser;
import com.lerry.lerrysecurity.common.service.Service;


/**
 * Created with CodeGenerator
 * Description:
 * @author  LErry.li
 * Date: 2018年7月8日
 * Time: 下午3:50:28
 */
public interface SysUserService extends Service<SysUser> {

    /**
     * 登陆用户
     * @param form
     * @return
     */
    SysUser login(LoginForm form);

}
