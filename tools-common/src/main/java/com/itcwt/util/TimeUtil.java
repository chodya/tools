package com.itcwt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期时间操作工具类
 *
 * @author cwt
 * @create by cwt on 2017-08-11 17:30
 */
public class TimeUtil {

    public static final String PATTERN_yyMMdd = "yyMMdd";
    public static final String PATTERN_yyyyMMdd = "yyyyMMdd";
    public static final String PATTERN_yy_MM_dd_HHmmss = "yy-MM-dd HH:mm:ss";
    public static final String PATTERN_yyyy_MM_dd_HHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String PATTERN_yy_MM_dd = "yy-MM-dd";
    public static final String PATTERN_yyyy_MM_dd_ = "yyyy_MM_dd";
    public static final String PATTERN_yyyy_MM_dd = "yyyy-MM-dd";
    public static final String PATTERN_yyyy_MM_dd_ZN = "yyyy年MM月dd日";
    public static final String PATTERN_yyyy_MM_dd_HHmmss_ZN = "yyyy年MM月dd日 HH时mm分ss秒";

    /** 一分钟秒数 */
    private static final int SECONDS_PER_MINUTE = 60;
    /** 一小时的分钟数 */
    private static final int MINUTES_PER_HOUR = 60;
    /** 一天的小时数 */
    private static final int HOURS_PER_DAY = 24;
    /** 一天的秒数 */
    private static final int SECONDS_PER_DAY = (HOURS_PER_DAY * MINUTES_PER_HOUR * SECONDS_PER_MINUTE);
    /** 一天的毫秒数 */
    public static final long DAY_MILLISECONDS = SECONDS_PER_DAY * 1000L;


    /**
     * 得到当前时间戳
     *
     * @return
     */
    public static long getCurrentTimeStamp() {
        return System.currentTimeMillis();
    }

    /**
     * 字符串转时间
     * @param date
     * @param pattern
     * @return
     */
    public static Date parseStringToDate(String date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat _df = new SimpleDateFormat(pattern);
        try {
            return _df.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String parseDateToString(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat _df = new SimpleDateFormat(pattern);
        return _df.format(date);
    }

    /**
     * 将时间格式转换为另一种格式
     *
     * @param date 需要转换的时间
     * @param pattern 转换之前的时间格式
     * @param parsePattern 转换后的时间格式
     * @return [String] 转换后的时间
     */
    public static String parseStringToString(String date, String pattern, String parsePattern) {
        if (date == null) {
            return "";
        }
        return parseDateToString(parseStringToDate(date, pattern), parsePattern);
    }

}
