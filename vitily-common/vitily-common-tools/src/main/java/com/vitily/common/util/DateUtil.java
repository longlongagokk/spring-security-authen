package com.vitily.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期转换、比较等相关操作
 * @author lether
 *
 */
public class DateUtil {

    public static final String timeZone = "GMT+8";
    /**
     * 日期格式(yyyy-MM-dd)
     */
    public static final String yyyy_MM_dd_EN = "yyyy-MM-dd";

    /**
     * 日期格式(yyyyMMdd)
     */
    public static final String yyyyMMdd_EN = "yyyyMMdd";

    /**
     * 日期格式(yyyy-MM)
     */
    public static final String yyyy_MM_EN = "yyyy-MM";

    /**
     * 日期格式(yyyyMM)
     */
    public static final String yyyyMM_EN = "yyyyMM";

    /**
     * 日期格式(yyyy-MM-dd HH:mm:ss)
     */
    public static final String yyyy_MM_dd_HH_mm_ss_EN = "yyyy-MM-dd HH:mm:ss";
    public static final String JSONOUTPUT_yyyy_MM_dd_HH_mm_ss_EN = yyyy_MM_dd_HH_mm_ss_EN;
    public static final String JSONINPUT_yyyy_MM_dd_HH_mm_ss_EN = yyyy_MM_dd_HH_mm_ss_EN;
    public static final String JSONOUTPUT_yyyy_MM_dd_EN = yyyy_MM_dd_EN;
    public static final String JSONINPUT_yyyy_MM_dd_EN = JSONOUTPUT_yyyy_MM_dd_EN;

    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";

    /**
     * 日期格式(yyyyMMddHHmmss)
     */
    public static final String yyyyMMddHHmmss_EN = "yyyyMMddHHmmss";

    /**
     * 日期格式(yyyy年MM月dd日)
     */
    public static final String yyyy_MM_dd_CN = "yyyy年MM月dd日";

    /**
     * 日期格式(yyyy年MM月dd日HH时mm分ss秒)
     */
    public static final String yyyy_MM_dd_HH_mm_ss_CN = "yyyy年MM月dd日HH时mm分ss秒";

    /**
     * 日期格式(yyyy年MM月dd日HH时mm分)
     */
    public static final String yyyy_MM_dd_HH_mm_CN = "yyyy年MM月dd日HH时mm分";
    public static SimpleDateFormat simpleDateFormat_Time = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss_EN);

    /**
     * DateFormat缓存
     */
    private static Map<String, DateFormat> dateFormatMap = new HashMap<String, DateFormat>();

    /**
     * 获取DateFormat
     *
     * @param formatStr
     * @return
     */
    public static DateFormat getDateFormat(String formatStr) {
        DateFormat df = dateFormatMap.get(formatStr);
        if (df == null) {
            df = new SimpleDateFormat(formatStr);
            dateFormatMap.put(formatStr, df);
        }
        return df;
    }


    /**
     * 按照默认formatStr的格式，转化dateTimeStr为Date类型 dateTimeStr必须是formatStr的形式
     *
     * @param dateTimeStr
     * @param formatStr
     * @return
     */
    public static Date getDate(String dateTimeStr, String formatStr) {
        try {
            if (StringUtil.isEmpty(dateTimeStr)) {
                return null;
            }
            DateFormat sdf = getDateFormat(formatStr);
            Date d = sdf.parse(dateTimeStr);
            return d;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将YYYYMMDD转换成Date日期
     *
     * @param date
     * @return
     */
    public static Date transferDateFromYYYYMMDD(String date) throws Exception {
        if (date == null || date.length() < 1)
            return null;

        if (date.length() != 8)
            throw new Exception("日期格式错误");
        String con = "-";

        String yyyy = date.substring(0, 4);
        String mm = date.substring(4, 6);
        String dd = date.substring(6, 8);

        int month = Integer.parseInt(mm);
        int day = Integer.parseInt(dd);
        if (month < 1 || month > 12 || day < 1 || day > 31)
            throw new Exception("日期格式错误");

        String str = yyyy + con + mm + con + dd;
        return getDate(str, yyyy_MM_dd_EN);
    }

    /**
     * 将Date转换成formatStr格式的字符串
     *
     * @param date
     * @param formatStr
     * @return
     */
    public static String dateToDateString(Date date, String formatStr) {
    	if(date == null){
    		return "";
    	}
        DateFormat df = getDateFormat(formatStr);
        return df.format(date);
    }

    /**
     * 将String转换成formatStr格式的字符串
     *
     * @param date
     * @param formatStr1
     * @param formatStr2
     * @return
     */
    public static String stringToDateString(String date, String formatStr1, String formatStr2) {
        Date d = getDate(date, formatStr1);
        DateFormat df = getDateFormat(formatStr2);

        return df.format(d);
    }

    public static Date addSeconds(Date date, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND,seconds);
        return calendar.getTime();
    }
    /**
     * add 或者set都可以
     * @param date
     * @param day
     * @return
     */
    public static Date addDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + day);
        return calendar.getTime();
    }
    /**
     * set的话会向后推，可能导致不对，比如3-31 ，set +1不会是4-30 而是 5-1
     * 因此如果需要4-30的话应该add
     * @param date
     * @param month
     * @return
     */
    public static Date addMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,month);
        return calendar.getTime();
    }

    /**
     * 获取当前日期时间yyyy-MM-dd HH:mm:ss的形式
     *
     * @return
     */
    public static String getCurDateTime(String formatStr) {
        return dateToDateString(new Date(), formatStr);
    }
    /**
     * 比较两 日期，之间相差多少毫秒,time2-time1
     *
     * @param time1
     * @param time2
     * @return
     */
    public static long compareDateStr(String time1, String time2) {
        Date d1 = getDate(time1,yyyy_MM_dd_HH_mm_ss_EN);
        Date d2 = getDate(time2,yyyy_MM_dd_HH_mm_ss_EN);
        return d2.getTime() - d1.getTime();
    }

    /**
     * 比较date1 和 date2 的相差天数,date2-date1
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int difference(Date date1, Date date2) throws Exception {
        return difference(date1,date2,"yyyy-MM-dd");
    }
    public static int difference(Date date1, Date date2,String fmt) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        date1 = sdf.parse(sdf.format(date1));
        date2 = sdf.parse(sdf.format(date2));
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));

    }

    /**
     * 比较date1 和 date2 的相差天数,date2-date1
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int difference(String date1, String date2) throws Exception {
        return difference(date1,date2,yyyy_MM_dd_EN);
    }
    public static int difference(String date1, String date2,String fmt) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(date1));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(date2));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));

    }
    /**
     * 获取当前日期years年后的一个(formatStr)的字符串
     *
     * @param years
     * @param formatStr
     * @return
     */
    public static String getDateStringOfYear(int years, String formatStr) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(new Date());
        now.add(Calendar.YEAR, years);
        return dateToDateString(now.getTime(), formatStr);
    }

    /**
     * 获取当前日期mon月后的一个(formatStr)的字符串
     *
     * @param months
     * @param formatStr
     * @return
     */
    public static String getDateStringOfMon(int months, String formatStr) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(new Date());
        now.add(Calendar.MONTH, months);
        return dateToDateString(now.getTime(), formatStr);
    }

    /**
     * 获取当前日期days天后的一个(formatStr)的字符串
     *
     * @param days
     * @param formatStr
     * @return
     */
    public static String getDateStringOfDay(int days, String formatStr) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(new Date());
        now.add(Calendar.DATE, days);
        return dateToDateString(now.getTime(), formatStr);
    }

    /**
     * 获取当前日期hours小时后的一个(formatStr)的字符串
     *
     * @param hours
     * @param formatStr
     * @return
     */
    public static String getDateStringOfHour(int hours, String formatStr) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(new Date());
        now.add(Calendar.HOUR_OF_DAY, hours);
        return dateToDateString(now.getTime(), formatStr);
    }

    /**
     * 获取指定日期mon月后的一个(formatStr)的字符串
     *
     * @param date
     * @param mon
     * @param formatStr
     * @return
     */
    public static String getDateOfMon(String date, int mon, String formatStr) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(DateUtil.getDate(date, formatStr));
        now.add(Calendar.MONTH, mon);
        return dateToDateString(now.getTime(), formatStr);
    }

    /**
     * 获取指定日期day天后的一个(formatStr)的字符串
     *
     * @param date
     * @param day
     * @param formatStr
     * @return
     */
    public static String getDateOfDay(String date, int day, String formatStr) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(DateUtil.getDate(date, formatStr));
        now.add(Calendar.DATE, day);
        return dateToDateString(now.getTime(), formatStr);
    }

    /**
     * 获取指定日期mins分钟后的一个(formatStr)的字符串
     *
     * @param date
     * @param mins
     * @param formatStr
     * @return
     */
    public static String getDateOfMin(String date, int mins, String formatStr) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(DateUtil.getDate(date, formatStr));
        now.add(Calendar.SECOND, mins * 60);
        return dateToDateString(now.getTime(), formatStr);
    }

    /**
     * 获取指定日期mins分钟后的一个日期
     *
     * @param date
     * @param mins
     * @return
     */
    public static Date getDateOfMin(Date date, int mins) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(date);
        now.add(Calendar.SECOND, mins * 60);
        return now.getTime();
    }

    /**
     * 获取当前日期mins分钟后的一个(formatStr)的字符串
     *
     * @param mins
     * @param formatStr
     * @return
     */
    public static String getDateStringOfMin(int mins, String formatStr) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(new Date());
        now.add(Calendar.MINUTE, mins);
        return dateToDateString(now.getTime(), formatStr);
    }

    /**
     * 获取当前日期mins分钟后的一个日期
     *
     * @param mins
     * @return
     */
    public static Date getDateOfMin(int mins) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(new Date());
        now.add(Calendar.MINUTE, mins);
        return now.getTime();
    }

    /**
     * 获取当前日期sec秒后的一个(formatStr)的字符串
     *
     * @param sec
     * @param formatStr
     * @return
     */
    public static String getDateStringOfSec(int sec, String formatStr) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(new Date());
        now.add(Calendar.SECOND, sec);
        return dateToDateString(now.getTime(), formatStr);
    }

    /**
     * 获得指定日期月份的天数
     *
     * @return
     */
    public static int getMonthDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);

    }

    /**
     * 获得系统当前月份的天数
     *
     * @return
     */
    public static int getCurentMonthDay() {
        Date date = Calendar.getInstance().getTime();
        return getMonthDay(date);
    }

    /**
     * 获得指定日期月份的天数 yyyy-mm-dd
     *
     * @return
     */
    public static int getMonthDay(String date) {
        Date strDate = getDate(date, yyyy_MM_dd_EN);
        return getMonthDay(strDate);
    }

    /**
     * 获取19xx,20xx形式的年
     *
     * @param d
     * @return
     */
    public static int getYear(Date d) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(d);
        return now.get(Calendar.YEAR);
    }

    /**
     * 获取月份，1-12月
     *
     * @param d
     * @return
     */
    public static int getMonth(Date d) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(d);
        return now.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取xxxx-xx-xx的日
     *
     * @param d
     * @return
     */
    public static int getDay(Date d) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(d);
        return now.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取Date中的小时(24小时)
     *
     * @param d
     * @return
     */
    public static int getHour(Date d) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(d);
        return now.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取Date中的分钟
     *
     * @param d
     * @return
     */
    public static int getMin(Date d) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(d);
        return now.get(Calendar.MINUTE);
    }

    /**
     * 获取Date中的秒
     *
     * @param d
     * @return
     */
    public static int getSecond(Date d) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(d);
        return now.get(Calendar.SECOND);
    }

    /**
     * 得到本周周一
     *
     * @return yyyy-MM-dd
     */
    public static String getMondayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 1);
        return dateToDateString(c.getTime(), yyyy_MM_dd_EN);
    }

    /**
     * 得到本周周日
     *
     * @return yyyy-MM-dd
     */
    public static String getSundayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 7);
        return dateToDateString(c.getTime(),yyyy_MM_dd_EN);
    }

    /**
     * 得到本周周(*)
     *
     * @return yyyy-MM-dd
     */
    public static String getDayOfThisWeek(int num) {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + num);
        return dateToDateString(c.getTime(), yyyy_MM_dd_EN);
    }

    /**
     * 得到本月指定天
     *
     * @return yyyy-MM-dd
     */
    public static String getDayOfThisMoon(String num) {
        String date = dateToDateString(new Date(), yyyy_MM_EN);
        date = date + "-" + num;
        return date;
    }
    /**
     * 获取剩余时间：最多到秒，最迟到天
     * @param
     * @return
     */
    public static String getRestTime(Long milSeconds){
    	String content = "";
    	Long seconds = milSeconds/1000;
    	if(seconds < 60){
    		content = seconds + "秒";
    		return content;
    	}
    	Long minutes = seconds/60;
    	seconds = seconds%60;//变成剩余时间了
    	if(minutes < 60){
    		content = minutes + "分" + seconds + "秒";
    		return content;
    	}
    	Long hour = minutes/60;
    	minutes = minutes%60;//同上
    	if(hour < 24){
    		content = hour + "小时" + minutes + "分" + seconds + "秒";
    		return content;
    	}
    	Long day = hour/24;
    	hour = hour%24;//直接显示天数了
		content = day + "天" + hour + "小时" + minutes + "分" + seconds + "秒";
		return content;
    }

    /**
     * 得到当前星期数
     */
    public static String getCurWeek() {
        final String dayNames[] = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

        String s = getCurDateTime(yyyy_MM_dd_EN);
        SimpleDateFormat sdfInput = new SimpleDateFormat(yyyy_MM_dd_EN);

        Calendar calendar = Calendar.getInstance();
        Date date = new Date();

        try {
            date = sdfInput.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayNames[dayOfWeek - 1];
    }

    public static final String getDateTimeFileName()
            throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(yyyyMMddHHmmss);

        java.sql.Date tempDate = new java.sql.Date(System.currentTimeMillis());
        String fileName = format.format(tempDate);

        int value = (int) Math.round(Math.random() * 100.0D);
        if (value < 10) {
            value += 10;
        }
        value += 100;
        fileName = fileName + value;

        return fileName;
    }

}  
