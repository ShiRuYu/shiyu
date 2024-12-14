package com.shiyu.commons.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateUtil {

    /**
     * 仅显示年月日，例如 2015-08-11.
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 显示年月日时分秒，例如 2015-08-11 09:51:53.
     */
    public static final String DATETIME_FORMAT_ONE = "yyyy-MM-dd HH:mm:ss";
    /**
     * 显示年月日时分秒，例如 2015-08-11 09:51:53.
     */
    public static final String DATETIME_FORMAT_TWO = "yyyy.MM.dd HH:mm:ss";

    /**
     * 仅显示时分秒，例如 09:51:53.
     */
    public static final String TIME_FORMAT = "HH:mm:ss";

    /**
     * 仅显示年月，例如 2015-08
     */
    public static final String MONTH_TIME_PATTERN = "yyyy-MM";

    /**
     * 仅显示年，例如 2015
     */
    public static final String YEAR_TIME_PATTERN = "yyyy";

    /**
     * 每天的毫秒数 8640000.
     */
    public static final long MILLISECONDS_PER_DAY = 86400000L;

    /**
     * 每周的天数.
     */
    public static final long DAYS_PER_WEEK = 7L;

    /**
     * 每小时毫秒数.
     */
    public static final long MILLISECONDS_PER_HOUR = 3600000L;

    /**
     * 每分钟秒数.
     */
    public static final long SECONDS_PER_MINUTE = 60L;

    /**
     * 每小时秒数.
     */
    public static final long SECONDS_PER_HOUR = 3600L;

    /**
     * 每天秒数.
     */
    public static final long SECONDS_PER_DAY = 86400L;

    /**
     * 每个月秒数，默认每月30天.
     */
    public static final long SECONDS_PER_MONTH = 2592000L;

    /**
     * 每年秒数，默认每年365天.
     */
    public static final long SECONDS_PER_YEAR = 31536000L;


    public static String dateToStr(Date time) {
        return dateToLocalDateTime(time).format(DateTimeFormatter.ofPattern(DateUtil.DATETIME_FORMAT_ONE));
    }

    public static Date strToDate(String time) {
        LocalDateTime localDateTime = strToLocalDateTime(time, DateUtil.DATETIME_FORMAT_ONE);
        return localDateTimeToDate(localDateTime);
    }

    /**
     * 字符串转时间
     * @param text
     * @param format
     * @return
     */
    public static LocalDateTime strToLocalDateTime(String text, String format) {
        return LocalDateTime.parse(text, DateTimeFormatter.ofPattern(format)); // text和format格式须一致不然报错
    }

    public static String localDateTimeToStr(LocalDateTime time, String format) {
        return time.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * LocalDate转Date
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate) {
        if (null == localDate) {
            return null;
        }
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * Date转LocalDate
     * @param date
     */
    public static LocalDate dateToLocalDate(Date date) {
        if(null == date) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * LocalDateTime转Date
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return null;
        }
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * Date转LocalDateTime
     * @param date
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        if(null == date) {
            return null;
        }
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 相差的天数(忽略时分秒)
     *
     * 类似的还有相差时分秒年月日等
     * 时间小的在前，时间大的在后 返回正数，反之负数
     * @param beg
     * @param end
     * @return
     */
    public static long betweenDay(LocalDateTime beg, LocalDateTime end) {
        /*Duration duration = Duration.between(beg,end);
        return duration.toDays();*/
        return beg.until(end, ChronoUnit.DAYS);
    }

    /**
     * 日期加N天 类似的还有时分秒年月日星期等
     * @param time
     * @param day
     * @return
     */
    public static LocalDateTime plusDays(LocalDateTime time, int day) {
        return time.plusDays(day);
    }

    /**
     * 日期减N天 类似的还有时分秒年月日星期等
     * @param time
     * @param day
     * @return
     */
    public static LocalDateTime minusDays(LocalDateTime time, int day) {
        return time.minusDays(day);
    }

    /**
     * 两个日期是否相同
     * @param paramA
     * @param paramB
     * @return
     */
    public static boolean isEqual(LocalDateTime paramA, LocalDateTime paramB) {
        if (paramA == null || paramB == null) {
            return false;
        }
        return paramA.isEqual(paramB);
    }

    /**
     * 前一个日期是否在后一个日期 之前
     * @param paramA
     * @param paramB
     * @return
     */
    public static boolean isBefore(LocalDateTime paramA, LocalDateTime paramB) {
        return paramA.isBefore(paramB);
    }

    /**
     * 前一个日期是否在后一个日期 之后
     * @param paramA
     * @param paramB
     * @return
     */
    public static boolean isAfter(LocalDateTime paramA, LocalDateTime paramB) {
        return paramA.isAfter(paramB);
    }

    /**
     * 前一个日期是否 大于等于 后一个日期
     * @param paramA
     * @param paramB
     * @return
     */
    public static boolean gt(LocalDateTime paramA, LocalDateTime paramB) {
        if (paramA == null || paramB == null) {
            return false;
        }
        long a = Instant.from(paramA.atZone(ZoneId.systemDefault())).toEpochMilli();
        long b = Instant.from(paramB.atZone(ZoneId.systemDefault())).toEpochMilli();
        return a >= b;
    }

    /**
     * 前一个日期是否 小于等于 后一个日期
     * @param paramA
     * @param paramB
     * @return
     */
    public static boolean lt(LocalDateTime paramA, LocalDateTime paramB) {
        if (paramA == null || paramB == null) {
            return false;
        }
        long a = Instant.from(paramA.atZone(ZoneId.systemDefault())).toEpochMilli();
        long b = Instant.from(paramB.atZone(ZoneId.systemDefault())).toEpochMilli();
        return a <= b;
    }

    /**
     * 获取年月日时分秒星期等
     * @param time
     * @return
     */
    public static int getYear(LocalDateTime time) {
        return time.getYear();
    }

    /**
     * 自定义时间
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static LocalDateTime getCustom(int year, int month, int day, int hour, int minute, int second) {
        return LocalDateTime.of(year, month, day, hour, minute, second);
    }

    /**
     * 获取当前时间
     * @return
     */
    public static LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }
}


