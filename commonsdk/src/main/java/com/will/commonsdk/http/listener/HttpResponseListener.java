package com.will.commonsdk.http.listener;

/**
 * Crate Time:  2018/12/27
 * Author:      LiuHanwei
 * Email:       liuhanwei@xhg.com
 * Description:
 */
public interface HttpResponseListener {
    /**
     * 请求成功回调
     * @param respObj
     */
    void onSuccess(Object respObj);

    /**
     * 请求失败回调
     * @param respObj
     */
    void onFailure(Object respObj);
}
