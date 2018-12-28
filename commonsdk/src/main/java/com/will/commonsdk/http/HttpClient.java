package com.will.commonsdk.http;

import com.will.commonsdk.http.listener.HttpResponseListener;
import com.will.commonsdk.http.response.HttpJsonCallback;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Crate Time:  2018/12/25
 * Author:      LiuHanwei
 * Email:       liuhanwei@xhg.com
 * Description:
 */
public class HttpClient {
    private static final int TIME_OUT = 30;
    private static OkHttpClient mHttpClient;

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.followRedirects(true);

        mHttpClient = builder.build();
    }

    public static Call get(Request request, HttpResponseListener listener) {
        Call call = mHttpClient.newCall(request);
        call.enqueue(new HttpJsonCallback(listener));
        return call;
    }
}
