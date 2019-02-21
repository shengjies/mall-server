package com.code.mallservice.mall.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TimeUtils {


    public static String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }

    public static String getYesterdayDate() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));

        calendar.add(Calendar.DATE, -1);

        return simpleDateFormat.format(calendar.getTime());

    }

    public static String getCurrentDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    public static int getCurrentHour() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        calendar.add(Calendar.DATE, 0);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getCurrentMin() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        calendar.add(Calendar.DATE, 0);
        return calendar.get(Calendar.MINUTE);
    }

    public static Long fomatTimeStamp(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return simpleDateFormat.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1L;
    }


    public static Long getBeginTimeSTAMP(int day_off) {
        try {
            return getBaseDateFormat(-1).parse(getDate(day_off, 1)).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1L;
    }

    public static Long getEndTimeSTAMP(int day_off) {
        try {
            return getBaseDateFormat(-1).parse(getDate(day_off, 2)).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1L;
    }

    private static SimpleDateFormat getBaseDateFormat(int i) {
        switch (i) {
            case 1:
                return new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            case 2:
                return new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }


    /**
     * @param day_off
     * @param type    1:开始时间  2:结束时间
     * @return
     */
    public static String getDate(int day_off, int type) {

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));

        calendar.add(Calendar.DATE, day_off);

        return getBaseDateFormat(type).format(calendar.getTime());
    }

    public static int getYesToDay() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        calendar.add(Calendar.DATE, -1);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getToDay() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        calendar.add(Calendar.DATE, 0);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getClearDay() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        calendar.add(Calendar.DATE, -2);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static Date getDate(String time) {
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(time);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getDateTime(String time) {
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static  String getDateByCode(int code){
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
            calendar.add(Calendar.DATE,code);
            return format.format(calendar.getTime());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String strDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date).toString();
    }


    /**
     * 获取分析的日期
     *
     * @param bDate
     * @param eDate
     * @return
     */
    public static List<String> getAnalysisDate(String bDate, String eDate) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> list = new ArrayList<>();

        try {
            Date bDateTime = sdf.parse(bDate);
            Date eDateTime = sdf.parse(eDate);
            Calendar c = Calendar.getInstance();
            while (bDateTime.getTime() <= eDateTime.getTime()) {
                list.add(sdf.format(bDateTime));
                c.setTime(bDateTime);
                c.add(Calendar.DATE, 1);
                bDateTime = c.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return list;
    }

}
