package com.code.mallservice.mall.utils;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {


    public static String toNewHtml(String html) {

        if (StringUtils.isEmpty(html)) {
            return "";
        }

        StringBuilder new_html = new StringBuilder();
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        String regEx_video = "<video.*src\\s*=\\s*(.*?)[^>]*?.>";

        Pattern p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        Matcher m_image = p_image.matcher(html);

        while (m_image.find()) {
            String img = m_image.group();
            if (!img.contains("data-src")) {
                String img_ = img.replace("src", "data-src");
                StringBuilder stringBuilder = new StringBuilder(img_);
                stringBuilder.insert(img_.indexOf(" ") + 1, "src=\"/res/loading.png\" ");
                stringBuilder.insert(img_.indexOf(" ") + 1, "class=\"b-lazy\" ");
                html = html.replace(img, stringBuilder.toString());
            }
        }

        Pattern p_video = Pattern.compile(regEx_video, Pattern.CASE_INSENSITIVE);
        Matcher m_video = p_video.matcher(html);
        while (m_video.find()) {
            String video = m_video.group();
            if (!video.contains("data-src")) {
                String video_ = video.replace("src", "data-src");
                StringBuilder stringBuilder = new StringBuilder(video_);
                stringBuilder.insert(video_.indexOf(" ") + 1, "src=\"/res/loading.png\" ");
                stringBuilder.insert(video_.indexOf(" ") + 1, "class=\"b-lazy\" ");
                html = html.replace(video, stringBuilder.toString());
            }
        }
        return html;
    }

    public static String getAddressJs(String country){
        String address_js ="";
        if(StringUtils.isEmpty(country)) return address_js;
        if(country.equals("TH")){
            address_js="https://dsaf465859jzs.cloudfront.net/c1/area_th.js";
        }else if(country.equals("MY")){
            address_js="https://dsaf465859jzs.cloudfront.net/c1/area_mlxy.js";
        }else if(country.equals("TW")){
            address_js="https://dsaf465859jzs.cloudfront.net/c1/area_tw.js";
        }else if(country.equals("HK")){
            address_js="https://dsaf465859jzs.cloudfront.net/c1/area_hk.js";
        }else if(country.equals("VN")){
            address_js="https://dsaf465859jzs.cloudfront.net/c1/area_vn.js";
        }else if(country.equals("ID")){
            address_js="https://dsaf465859jzs.cloudfront.net/c1/area_id.js";
        }
        return address_js;
    }

}
