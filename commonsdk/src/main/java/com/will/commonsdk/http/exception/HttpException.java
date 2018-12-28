package com.will.commonsdk.http.exception;

/**
 * Crate Time:  2018/12/27
 * Author:      LiuHanwei
 * Email:       liuhanwei@xhg.com
 * Description:
 */
public class HttpException extends Exception {
    /**
     * HTTP响应码
     */
    private int mHttpCode;
    /**
     * 服务器返回的错误信息
     */
    private String mErrorMsg;

    public HttpException(int httpCode, String errorMsg) {
        super("[" + httpCode + "]" + errorMsg);
        mHttpCode = httpCode;
        mErrorMsg = errorMsg;
    }

    public Object getmErrorMsg() {
        return mErrorMsg;
    }

    public int getHttpCode() {
        return mHttpCode;
    }
}
