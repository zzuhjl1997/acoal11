package com.plat.acoal.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

public class DateUtil {
    public final static String DATE_FORMAT_DAY = "yyyy-MM-dd";
    public final static String DATE_FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";

    private final static int[] DATE_UNIT_ARR = new int[]{Calendar.MILLISECOND, Calendar.SECOND, Calendar.MINUTE, Calendar.HOUR_OF_DAY,
            Calendar.DATE, Calendar.MONTH, Calendar.YEAR};

    public static DateFormat dateFormat = null;
    public static Date date = null;

    /**
     * 将日期转为 字符串
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        return dateToString(date, DATE_FORMAT_SECOND);
    }

    /**
     * 日期转字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateToString(Date date, String format) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 获取当前登录用户的 日期格式化对象
     *
     * @param timeZone
     * @param format
     * @return
     */
    private static SimpleDateFormat getTimeZoneSimpleDateFormat(String format, String timeZone) {
        //1、获取对应时区的格式化器
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        return simpleDateFormat;
    }

    /**
     * 将字符串转为日期
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date stringToDate(String dateStr, String format) throws ParseException {
        if (dateStr == null || format == null) {
            return null;
        }
        return getTimeZoneSimpleDateFormat(format, "CTT").parse(dateStr);
    }

    /**
     * 将字符串转为日期
     *
     * @param dateStr
     * @return
     */
    public static Date stringToDate(String dateStr) throws ParseException {
        if (dateStr == null) {
            return null;
        }
        return getTimeZoneSimpleDateFormat(DATE_FORMAT_SECOND, "CTT").parse(dateStr);
    }

    /**
     * 字符串转时间戳
     */
    public static Long stringToTimestamp(String dateStr, String format) throws ParseException {
        return stringToDate(dateStr, format).getTime();
    }

    /**
     * 字符串转时间戳
     */
    public static Long stringToTimestamp(String dateStr) throws ParseException {
        return stringToDate(dateStr).getTime();
    }

    /**
     * 日期转时间戳
     */
    public static Long dateTotimestamp(Date date, String format) throws ParseException {
        return stringToTimestamp(dateToString(date,format),format);
    }

    /**
     * 日期转时间戳
     */
    public static Long timestampToString(Date date) throws ParseException {
        return stringToTimestamp(dateToString(date));
    }

    /**
     * 时间戳转字符串
     */
    public static String timestampToString(Long timestamp,String format) {
        if(format != null){
            return new SimpleDateFormat(format).format(timestamp);
        }else{
            return new SimpleDateFormat(DATE_FORMAT_SECOND).format(timestamp);
        }
    }

    /**
     * 时间戳转字符串
     */
    public static String timestampToString(Long timestamp) throws ParseException {
        return timestampToString(timestamp,null);
    }

    /**
     * 时间戳转日期
     */
    public static Date timestampToDate(Long timestamp, String format) throws ParseException {
        if(format != null){
            return stringToDate(timestampToString(timestamp,format),format);
        }else{
            return stringToDate(timestampToString(timestamp));
        }
    }

    /**
     * 时间戳转日期
     */
    public static Date timestampToDate(Long timestamp) throws ParseException {
        return stringToDate(timestampToString(timestamp));
    }

    /**
     * 判断两个日期是否相等
     *
     * @param d1
     * @param d2
     * @param calendarUnit 对比的最小日期单位
     * @return
     */
    public static boolean isEqual(Date d1, Date d2, int calendarUnit) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        for (int i = DATE_UNIT_ARR.length - 1; i >= 0; i--) {
            if (calendarUnit >= DATE_UNIT_ARR[i]) {
                int v1 = c1.get(DATE_UNIT_ARR[i]);
                int v2 = c2.get(DATE_UNIT_ARR[i]);
                if (v1 != v2) {
                    return false;
                }
            } else {
                break;
            }
        }
        return true;
    }

    /**
     * 将毫秒转成时分秒
     * @param time
     * @return
     */

    public static String millisecondToTime(Long time){
        long day=time/(24*60*60*1000);
        long hour=(time/(60*60*1000)-day*24);
        long min=((time/(60*1000))-day*24*60-hour*60);
        long s=(time/1000-day*24*60*60-hour*60*60-min*60);
        String times=""+day+"天"+hour+"小时"+min+"分"+s+"秒";
        System.out.println(times);
        return  times;
    }

}