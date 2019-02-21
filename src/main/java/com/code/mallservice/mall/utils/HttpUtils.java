package com.code.mallservice.mall.utils;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HttpUtils {

    public static final String API = "https://www.ipcloakads.com/api/";

    /**
     * 执行GET请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String get(String url) throws IOException {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response;
        ResponseBody responseBody;
        response = okHttpClient.newCall(request).execute();
        responseBody = response.body();
        if (responseBody != null) {
            return responseBody.string();
        }
        return null;
    }



    /**
     * POST 请求
     *
     * @param url
     * @param requestBody
     * @return
     */
    public static String post(String url, RequestBody requestBody) throws Exception {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                connectTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url(url).post(requestBody)
                .build();

        Response response = okHttpClient.newCall(request).execute();

        //System.out.println("返回的状态码为：" + response.code());

        ResponseBody responseBody = response.body();
        if (responseBody != null) {
            return responseBody.string();
        }
        return null;
    }
}
