package com.code.mallservice.mall.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 短信发送
 */
public class SmsUtils {

    private static String url = "https://api.twilio.com/2010-04-01/Accounts/AC8380780294ec1ed5f339af8c524ff3c9/Messages.json";

    private static String content_type = "application/x-www-form-urlencoded";

    private static String from = "+18502045096";

    private static String user = "AC8380780294ec1ed5f339af8c524ff3c9";

    private static String password = "7dd23d2c16d5c1d57376808974262cec";

    /**
     * 模板参考示例
     */
    private static String sms_template_tw = "親愛的顧客您好！您的快遞包裹已經到達711門市:{address}";

    /**
     * 执行发送短信
     *
     * @param to
     * @param body
     * @return
     */
    public static boolean sendSms(String to, String body) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                connectTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .authenticator((route, response) -> {
                    String credential = Credentials.basic(user, password);
                    return response.request().newBuilder().header("Authorization", credential).build();
                })
                .build();


        FormBody formBody = new FormBody.Builder().add("To", to)
                .add("From", from)
                .add("Body", body)
                .build();

        Request request = new Request.Builder()
                .url(url).post(formBody)
                .build();

        Response response = okHttpClient.newCall(request).execute();

        ResponseBody responseBody = response.body();

        String responseResult = "";

        if (responseBody != null) {
            responseResult = responseBody.string();
        }

        if (!StringUtils.isEmpty(responseResult)) {
            JSONObject jsonObject = JSON.parseObject(responseResult);
            String status = jsonObject.getString("status");
            if ("queued".equals(status)) {
                return true;
            }
        }
        return false;
    }


}
