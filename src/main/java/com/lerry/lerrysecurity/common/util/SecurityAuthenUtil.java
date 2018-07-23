package com.lerry.lerrysecurity.common.util;

import com.lerry.lerrysecurity.auth.model.AppUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *  获取登录用户信息
 * @author LErry.li
 * Date: 2018-07-21
 * Time: 15:19
 */
public class SecurityAuthenUtil {
	
	/**
	 * 直接获取当前用户的登录账号
	 * @return
	 */
	public static String getLoginName() {
		Authentication authenObj = SecurityContextHolder.getContext().getAuthentication();
		AppUserDetails authenUser = (AppUserDetails)authenObj.getPrincipal();
		return authenUser.getUsername();
	}
	
	/**
	 * 直接获取当前用户的认证用户信息
	 * @return
	 */
	public static AppUserDetails getAuthenticationUser() {
		Authentication authenObj = SecurityContextHolder.getContext().getAuthentication();
		return (AppUserDetails)authenObj.getPrincipal();
	}
	

}
