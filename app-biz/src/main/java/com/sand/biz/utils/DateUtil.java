package com.sand.biz.utils;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {
    /**
     * date 增加days天
     *
     * @param date
     * @return
     */
    public static Date plusDays(Date date, long days) {
        LocalDateTime dueDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(dueDateTime
                .plusDays(days)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    /**
     * date 减少days天
     *
     * @param date
     * @return
     */
    public static Date minusDays(Date date, long days) {
        LocalDateTime dueDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(dueDateTime
                .minusDays(days)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    /**
     * date 增加hours小时
     *
     * @param date
     * @return
     */
    public static Date plusHours(Date date, long hours) {
        LocalDateTime dueDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(dueDateTime
                .plusHours(hours)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    /**
     * date 减少hours小时
     *
     * @param date
     * @return
     */
    public static Date minusHours(Date date, long hours) {
        LocalDateTime dueDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(dueDateTime
                .minusHours(hours)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    /**
     * date 增加minutes分钟
     *
     * @param date
     * @return
     */
    public static Date plusMinutes(Date date, long minutes) {
        LocalDateTime dueDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(dueDateTime
                .plusMinutes(minutes)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    /**
     * date 减少minutes分钟
     *
     * @param date
     * @return
     */
    public static Date minusMinutes(Date date, long minutes) {
        LocalDateTime dueDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(dueDateTime
                .minusMinutes(minutes)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    /**
     * 格式转换
     *
     * @param date    日期变量
     * @param pattern 匹配模式
     * @return 日期字符号串
     */
    public static String getFormatDate(Date date, String pattern) {
        if (pattern == null || pattern.length() < 1) {
            pattern = "yyyy-MM-dd";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static String fromDate(Date date, String pattern) {
        return getFormatDate(date, pattern);
    }

    public static Date toDate(String dateStr, String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException("parse error");
        }
    }

    /**
     * 格式化为当天00:00:00
     *
     * @param date
     * @return
     */
    public static Date trimDate(Date date) {
        if (date == null) {
            return null;
        }
        LocalDateTime current = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(current
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    /**
     * 格式化为当天23:59:59
     *
     * @param date
     * @return
     */
    public static Date trimToEndOfDate(Date date) {
        LocalDateTime current = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(current
                .withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(0)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static int getMonthOfYear(Date date) {
        LocalDateTime current = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return current.getMonthValue();
    }

    /**
     * 获取所在年的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfYear(Date date) {
        LocalDateTime current = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(current
                .withMonth(1)
                .withDayOfMonth(1)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    /**
     * 获取所在月的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        LocalDateTime current = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(current
                .withDayOfMonth(1)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    /**
     * 获取所在月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        LocalDateTime current = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDate currentDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Date.from(current
                .withDayOfMonth(currentDate.lengthOfMonth())
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    /**
     * 获取所在月的总天数
     *
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        LocalDate currentDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return currentDate.lengthOfMonth();
    }

    /**
     * 获取下个月的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfNextMonth(Date date) {
        LocalDateTime current = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(current
                .plusMonths(1L)
                .withDayOfMonth(1)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    /**
     * date 减少weeks周
     *
     * @param date
     * @return
     */
    public static Date minusWeeks(Date date, long weeks) {
        LocalDateTime dueDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(dueDateTime
                .minusWeeks(weeks)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    /**
     * date 减少months月
     *
     * @param date
     * @return
     */
    public static Date minusMonths(Date date, long months) {
        LocalDateTime dueDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(dueDateTime
                .minusMonths(months)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    /**
     * date 减少years年
     *
     * @param date
     * @return
     */
    public static Date minusYears(Date date, long years) {
        LocalDateTime dueDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(dueDateTime
                .minusYears(years)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    /**
     * date 增加months月
     *
     * @param date
     * @return
     */
    public static Date plusMonths(Date date, long months) {
        LocalDateTime dueDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(dueDateTime
                .plusMonths(months)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    /**
     * date 增加years年
     *
     * @param date
     * @return
     */
    public static Date plusYears(Date date, long years) {
        LocalDateTime dueDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return Date.from(dueDateTime
                .plusYears(years)
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    /**
     * 获取星期几
     *
     * @param date
     * @return
     */
    public static DayOfWeek getDayOfWeek(Date date) {
        LocalDateTime current = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return current.getDayOfWeek();
    }

    /**
     * 获取星期几中文描述
     *
     * @param date
     * @return
     */
    public static String getDayOfWeekChineseDesc(Date date) {
        DayOfWeek dayOfWeek = getDayOfWeek(date);
        String desc = "";
        switch (dayOfWeek) {
            case MONDAY:
                desc = "星期一";
                break;
            case TUESDAY:
                desc = "星期二";
                break;
            case WEDNESDAY:
                desc = "星期三";
                break;
            case THURSDAY:
                desc = "星期四";
                break;
            case FRIDAY:
                desc = "星期五";
                break;
            case SATURDAY:
                desc = "星期六";
                break;
            case SUNDAY:
                desc = "星期天";
        }
        return desc;
    }
}
