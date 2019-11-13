package com.yumenghu.common.util;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 *@program: CEM_service
 *@description: 时间工具类
 *@author: yu meng hu
 *@create: 2019-08-28 18:44
 */
@Slf4j
public class DateUtil {

  private static String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

  private static final Pattern PATTERN = Pattern.compile(
      "^[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}$");


  /**
   将日期转为字符串 yyyy-mm-dd HH:mm:ss
   @param date
   @return
   */
  public static String getYYYYMMDDHHMMSS(Date date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYYMMDDHHMMSS);
    LocalDateTime localDateTime = dateToDateTime(date);
    return formatter.format(localDateTime);
  }

  /**
   将字符串转为日期
   @param date
   @return
   */
  public static Date getDate(String date) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(YYYYMMDDHHMMSS);
    LocalDateTime localDateTime = LocalDateTime.parse(date, dateTimeFormatter);
    return dateTimeToDate(localDateTime);
  }

  /**
   将字符串转为日期
   @return
   */
  public static Date getNowDate() {
    LocalDateTime localDateTime = LocalDateTime.now();
    return dateTimeToDate(localDateTime);
  }

  /**

   将字符串转为日期
   @param date
   @return
   */
  public static Date getDate(String date, String pattern) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
    LocalDateTime localDateTime = LocalDateTime.parse(date, dateTimeFormatter);
    return dateTimeToDate(localDateTime);
  }


  /**
   * 获取当前时间
   * @param hour
   * @return
   */
  public static String getBeforeOrLastByHourTime(int hour) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);
    return getYYYYMMDDHHMMSS(calendar.getTime());
  }

  public static String getBeforeOrLastByHourTime(String date,int hour) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(getDate(date));
    calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);
    return getYYYYMMDDHHMMSS(calendar.getTime());
  }

  /**
   LocalDateTime 和DateTime 互转
   @param date
   @return
   */
  public static LocalDateTime dateToDateTime(Date date) {
    Instant instant = date.toInstant();
    return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
  }


  public static LocalDateTime dateToDateTime(String date) {
    Instant instant = getDate(date).toInstant();
    return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
  }


  public static Date dateTimeToDate(LocalDateTime localDateTime) {
    Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    return date;
  }


  /**
   * 获取当前时间戳
   * @return
   */
  public static Timestamp getNowTimestamp() {
    return Timestamp.valueOf(LocalDateTime.now());
  }


  /**
   * 获取当前时间戳:/秒
   * @return
   */
  public static Long getTimestampNow() {
    return LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
  }


  /**
   * 获取当前时间戳:/秒
   * @return
   */
  public static Long getTimestamp(String dateTime) {
    return dateToDateTime(dateTime).toEpochSecond(ZoneOffset.of("+8"));
  }

  /**
   * 获取当前的小时
   * @return
   */
  public static int getNowHour() {

    LocalDateTime localDateTime = LocalDateTime.now();
    return localDateTime.getHour();
  }

  /**
   * 获取小时
   * @return
   */
  public static int getHour(String dateTime) {
    getDate(dateTime);
    LocalDateTime localDateTime = LocalDateTime.now();
    return localDateTime.getHour();
  }


  /**
   * 获取时间字符串小时
   * 时间格式 yyyy-MM-dd HH:mm:ss
   * @param dateTime
   * @return
   */
  public static Integer getDateTimeHour(String dateTime) {
    Integer hour = null;
    if (StringUtils.isEmpty(dateTime)) {
      return hour;
    }
    LocalDateTime time = LocalDateTime.parse(dateTime,DateTimeFormatter.ofPattern(YYYYMMDDHHMMSS));
    return time.getHour();
  }


  /**
   * 正则检验时间格式
   * @param datetime
   * @return
   */
  public static boolean isDateTime(String datetime) {
    return PATTERN.matcher(datetime).matches();
  }
}