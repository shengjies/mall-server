package com.code.mallservice.mall.utils;

public class IpUtils {


//    public static void main(String[] args) {
//
//        Long ip = inet_aton("34.45.67.89");
//
//        System.out.println("IP->Long:"+ip);
//
//        System.out.println("Long->IP:"+inet_ntoa(ip));
//    }

    /**
     * Ip转Long
     *
     * @param add
     * @return
     */
    public static Long inet_aton(String add) {

        if (add == null) {
            return 0L;
        }

        long result = 0;
        // number between a dot
        long section = 0;
        // which digit in a number
        int times = 1;
        // which section
        int dots = 0;
        for (int i = add.length() - 1; i >= 0; --i) {
            if (add.charAt(i) == '.') {
                times = 1;
                section <<= dots * 8;
                result += section;
                section = 0;
                ++dots;
            } else {
                section += (add.charAt(i) - '0') * times;
                times *= 10;
            }
        }
        section <<= dots * 8;
        result += section;
        return result;
    }

    /**
     * Long 转IP
     *
     * @param longIp
     * @return
     */
    public static String inet_ntoa(long longIp) {
        StringBuffer sb = new StringBuffer("");
        // 直接右移24位
        sb.append(String.valueOf((longIp >>> 24)));
        sb.append(".");
        // 将高8位置0，然后右移16位
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
        sb.append(".");
        // 将高16位置0，然后右移8位
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
        sb.append(".");
        // 将高24位置0
        sb.append(String.valueOf((longIp & 0x000000FF)));
        return sb.toString();
    }

}
