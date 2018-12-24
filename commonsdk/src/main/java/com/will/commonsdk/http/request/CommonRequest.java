package com.will.commonsdk.http.request;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * Crate Time:  2018/12/24
 * Author:      LiuHanwei
 * Email:       liuhanwei@xhg.com
 * Description:
 */
public class CommonRequest {
    /**
     * 创建Post请求{@code okhttp3.Request}Request实例
     *
     * @param url
     * @param params
     * @return
     */
    public static Request createPostRequest(String url, RequestParams params) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        FormBody formBody = builder.build();

        Request request = new Request.Builder().url(url).post(formBody).build();
        return request;
    }

    /**
     * 创建Get请求{@code okhttp3.Request}Request实例
     * @param url
     * @param params
     * @return
     */
    public static Request createGetRequest(String url, RequestParams params) {
        StringBuilder builder = new StringBuilder(url).append("?");
        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                builder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        return new Request.Builder().url(builder.substring(0, builder.length() - 1)).get().build();
    }
}
