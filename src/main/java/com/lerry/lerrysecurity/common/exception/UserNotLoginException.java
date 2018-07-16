package com.lerry.lerrysecurity.common.exception;

import com.lerry.lerrysecurity.common.result.ResultCode;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *  用户未登录
 * @author LErry.li
 * Date: 2018-06-15
 * Time: 14:41
 */
public class UserNotLoginException extends BusinessException {
    private static final long serialVersionUID = -1879503946782379204L;

    public UserNotLoginException() {
        super();
    }

    public UserNotLoginException(Object data) {
        super();
        super.data = data;
    }

    public UserNotLoginException(ResultCode resultCode) {
        super(resultCode);
    }

    public UserNotLoginException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public UserNotLoginException(String msg) {
        super(msg);
    }

    public UserNotLoginException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }

}
