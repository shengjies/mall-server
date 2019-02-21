package com.code.mallservice.mall.utils;


public class Test {

    public static final String API = "https://www.ipcloakads.com/api/";

    public static void main(String[] args) {

//        String paramsStr="name=zfq&qq=1212";
//        MediaType MEDIA_TYPE_NORAML_FORM = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
//        RequestBody requestBody=RequestBody.create(MEDIA_TYPE_NORAML_FORM,paramsStr);
//        Request requestPost=new Request.Builder().url(url).post(requestBody).build();

        try {



            String result = HttpUtils.post(API, new CloakRequest().toRequestBody("127.0.0.1", "127.0.0.1:8080", "-"));

          //  {"status":0,"reason":"74.ban_computer","ip_data":{"ip":"39.12.167.48","country_code":"TW","country":"Taiwan, Province of China","isp":"Far Eastone Telecommunication Co. Ltd.","domain":"fetnet.net","type":"ISP\/MOB"},"jump_fp_url":"","domain_added":0}


            //"status":0 代表需要屏蔽，走安全链接  //"status":1  走原始链接

            // Respone.sendRidect

            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
