package com.lerry.lerrysecurity.auth.service.impl;

import com.lerry.lerrysecurity.auth.dao.SysUserMapper;
import com.lerry.lerrysecurity.auth.model.AppUserDetails;
import com.lerry.lerrysecurity.auth.model.SysUser;
import com.lerry.lerrysecurity.auth.service.AppUserDetailService;
import com.lerry.lerrysecurity.auth.service.SysUserService;
import com.lerry.lerrysecurity.common.exception.BusinessException;
import com.lerry.lerrysecurity.common.exception.DataNotFoundException;
import com.lerry.lerrysecurity.common.result.ResultCode;
import com.lerry.lerrysecurity.common.service.AbstractService;
import com.lerry.lerrysecurity.common.util.EncryptProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created with CodeGenerator
 * Description:
 * @author  LErry.li
 * Date: 2018年7月8日
 * Time: 下午3:50:28
 */
@Service
@Transactional
public class SysUserServiceImpl extends AbstractService<SysUser> implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private AppUserDetailService userDetailService;



    /**
     * 登陆用户
     *
     * @param userName
     * @param passWord
     * @return
     */
    @Override
    public SysUser login(String userName, String passWord) {
        AppUserDetails userDetails = (AppUserDetails) userDetailService.loadUserByUsername(userName);
        if(userDetails == null){
            throw new DataNotFoundException(ResultCode.USER_NOT_EXIST);
        }
        //如果不是第三方登录则校验密码
        if (!EncryptProvider.match(passWord,userDetails.getPassword())) {
            throw new BusinessException(ResultCode.USER_LOGIN_ERROR);
        }
        return userDetails.getUser();
    }
}
