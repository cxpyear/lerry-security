package com.lerry.lerrysecurity.common.exception;

import com.lerry.lerrysecurity.common.result.ResultCode;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *  远程访问时错误
 * @author LErry.li
 * Date: 2018-06-15
 * Time: 14:41
 */
public class RemoteAccessException extends BusinessException {
    private static final long serialVersionUID = -832464574076215195L;

    public RemoteAccessException(Object data) {
        super.data = data;
    }

    public RemoteAccessException(ResultCode resultCode) {
        super(resultCode);
    }

    public RemoteAccessException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public RemoteAccessException(String msg) {
        super(msg);
    }

    public RemoteAccessException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }

}
