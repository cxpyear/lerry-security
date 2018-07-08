package com.lerry.lerrysecurity.common.util;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *  Spring security 提供的加密算法
 * @author LErry.li
 * Date: 2018/7/8
 * Time: 下午4:50
 */
public class EncryptProvider {

    /**
     * spring security 5.x默认使用bcrypt加密
     */
    private static final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();


    /**
     * 字符串加密
     * @param str
     * @return
     */
    public static String encode(String str){
        return encoder.encode(str);

    }

    /**
     * 校验原字符串和加密后的字符串是否一致
     * @param str
     *          原字符串
     * @param encodedStr
     *          加密后的字符串
     * @return
     */
    public static boolean match(String str, String encodedStr) {
        return encoder.matches(str, encodedStr);
    }

    public static void main(String[] args) {
        System.out.println(encode("admin"));
    }

}
