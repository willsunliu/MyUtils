package com.will.commonsdk.http.request;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author:      Will
 */
public class RequestParams {
    public ConcurrentHashMap<String, String> urlParams = new ConcurrentHashMap<>();

    /**
     * 构造一个空的｛@code RequestParams｝实例
     */
    public RequestParams() {
        this(null);
    }

    /**
     * 构造一个包含传入的map内容的RequestParams实例
     * @param source 待加入到实例的key/value map
     */
    public RequestParams(Map<String, String> source) {
        if (source != null) {
            for (Map.Entry<String, String> entry : source.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 构造一个新的RequestParams实例，并通过key/value进行初始化
     * @param key 用于初始化的key名字
     * @param value 用于初始化的value内容
     */
    public RequestParams(final String key, final String value) {
        this(new HashMap<String, String>() {
            {
                put(key, value);
            }
        });
    }

    /**
     * 往RequestParams添加key/value键值对
     * @param key
     * @param value
     */
    public void put(String key, String value) {
        if (key != null && value != null) {
            urlParams.put(key, value);
        }
    }

    public boolean hasParams() {
        if (urlParams.size() > 0) {
            return true;
        }
        return false;
    }
}
