package com.will.commonsdk.http.response;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.will.commonsdk.http.exception.HttpException;
import com.will.commonsdk.http.listener.HttpResponseListener;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Crate Time:  2018/12/27
 * Author:      LiuHanwei
 * Email:       liuhanwei@xhg.com
 * Description:
 */
public class HttpJsonCallback implements Callback {
    /**
     * the java layer exception, not the same as the logic error
     */
    protected final int NETWORK_ERROR = -1; // the network relative error
    /**
     * 将其他线程的数据转发到UI线程
     */
    private Handler mDeliveryHandler;
    private HttpResponseListener mListener;

    public HttpJsonCallback(HttpResponseListener listener) {
        mListener = listener;
        mDeliveryHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new HttpException(NETWORK_ERROR, e.getMessage()));
            }
        });
    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull final Response response) throws IOException {
        // HTTP返回状态码不在[200,300)之间
        if (!response.isSuccessful()) {
            mListener.onFailure(new HttpException(response.code(), response.message()));
            return;
        }

        ResponseBody body = response.body();
        final String result = body != null ? body.string() : "";
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                if (result.trim().equals("")) {
                    // body数据为空
                    mListener.onFailure(new HttpException(response.code(), "接收到的数据为空"));
                } else {
                    mListener.onSuccess(result);
                }
            }
        });
    }
}
