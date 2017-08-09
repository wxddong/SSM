package com.atwhere.p2p.myutil;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.MutableDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
public class DateTimeUtil {


    public static String DateTimeFormatter = "yyyyMMdd";
    public static String Formatter1 = "yyyy-MM-dd";
    public static String Formatter2 = "yyyy-MM-dd";
    public static String Formatter19 = "yyyy-MM-dd HH:mm:ss";
    public static String Formater7 = "yyyy-MM";
    public final static String SIMPLE_DATE_PATTERN = "yyyyMMdd";
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    public final static String TIME_PATTERN = "HH:mm:ss";
    public final static String DATE_TIME_PATTERN = DATE_PATTERN + " "
            + TIME_PATTERN;
    public final static String Formater14 = "yyyyMMddHHmmss";


    private DateTimeUtil() {
    }

    public static DateTime getDate(String sDate, String sFormat) {
        DateTime dValue;

        if (sFormat == null || "".equals(sFormat)) {
            sFormat = "yyyy-MM-dd HH:mm:ss";
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormat
                .forPattern(sFormat);

        dValue = dateTimeFormatter.parseDateTime(sDate);

        return dValue;

    }

    public static String formatDate(long sDate, String sFormat) {
        return formatDate(new DateTime(sDate), sFormat);
    }


    public static String formatDate(Date lDate) {
        return formatDate(new DateTime(lDate), "");
    }

    public static String formatDate(long lDate) {
        return formatDate(new DateTime(lDate), "");
    }

    public static String formatJustDate(long lDate) {
        return formatDate(new DateTime(lDate), "yyyy-MM-dd");
    }

    public static String formatDate(DateTime date) {
        return formatDate(date, DateTimeZone.getDefault());
    }

    public static String formatDate(DateTime date, String sFormat) {
        if (StringUtils.isBlank(sFormat))
            sFormat = "yyyy-MM-dd HH:mm:ss";
        return formatDate(date, DateTimeZone.getDefault(), sFormat);
    }

    public static String formatDate(DateTime date, DateTimeZone DateTimeZone) {
        return formatDate(date, DateTimeZone, "yyyy-MM-dd");
    }

    public static String formatDate(DateTime date, DateTimeZone dateTimeZone,
                                    String sFormat) {

        DateTimeFormatter dateFormat = DateTimeFormat.forPattern(sFormat);
        return dateFormat.print(date);

    }

    public static String simplifyDate(String date) {

        DateTime d = getDate(date, "yyyy-MM-dd HH:mm:ss");
        return formatDate(d, "yy-MM-dd HH:mm");

    }

    public static String getTodayStr() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat
                .forPattern("yyyy-MM-dd");

        DateTime todate = new DateTime(System.currentTimeMillis());
        String today = dateTimeFormatter.print(todate);
        return today;
    }

    public static final int TIMESTAMPTYPE_UNIX = 2;

    /**
     * 将日期对象转为指定格式日期字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String format(DateTime date, String format) {
        DateTimeFormatter sdf = DateTimeFormat.forPattern(format);
        return sdf.print(date);
    }


    public static String format(Date date, String format) {
        return format(new DateTime( date),   format);
    }

    /**
     * 将日期对象转为指定格式日期字符串
     *
     * @param date
     * @param intFormat
     * @return
     */
    public static String format(DateTime date, int intFormat) {
        DateTimeFormatter sdf = findFormat(intFormat);
        return sdf.print(date);
    }

    /**
     * 根据参数获得简单日期格式对象
     *
     * @param intFormat
     * @return
     */
    public static DateTimeFormatter findFormat(int intFormat) {
        String strFormat = "yyyy'??'MM'??'dd'??' H:mm:ss.S";
        switch (intFormat) {
            case 0: // '\0'
                strFormat = "yyyy'??'MM'??'dd'??' H:mm:ss.S";
                break;
            case 1: // '\001'
                strFormat = "yyyy'-'MM'-'dd H:mm:ss.S";
                break;
            case 2: // '\002'
                strFormat = "yyyy'??'MM'??'dd'??'";
                break;
            case 3: // '\003'
                strFormat = "yyyy'-'MM'-'dd";
                break;
            case 4: // '\004'
                strFormat = "H:mm:ss";
                break;
            case 5: // '\005'
                strFormat = "K:mm:ss a";
                break;
            case 6: // '\006'
                strFormat = "yyyy'??'MM'??'dd'??' H:mm:ss";
                break;
            case 7: // '\007'
                strFormat = "yyyy'??'MM'??'dd'??' K:mm:ss a";
                break;
            case 8: // '\b'
                strFormat = "yyyy-MM-dd H:mm:ss";
                break;
            case 9: // '\t'
                strFormat = "yyyy-MM-dd K:mm:ss a";
                break;
            case 10: // '\n'
                strFormat = "H:mm:ss.S";
                break;
            case 11: // '\013'
                strFormat = "K:mm:ss.S a";
                break;
            case 12: // '\f'
                strFormat = "H:mm";
                break;
            case 13: // '\r'
                strFormat = "K:mm a";
                break;
            case 14: // '\r'
                strFormat = "yyyy-MM-dd H:mm";
                break;
            case 15: // '\r'
                strFormat = "yyyyMMddHHmmssS";
                break;
            case 16: // '\r'
                strFormat = "yyyyMMdd";
                break;
            default:
                strFormat = "yyyy'??'MM'??'dd'??' H:mm:ss.S";
                break;
        }
        return DateTimeFormat.forPattern(strFormat);
    }

    public static String formatDateStr(String dateStr, int newtimestampType,
                                       int oldtimestampType) {
        DateTime oldDate = findFormat(oldtimestampType).parseDateTime(dateStr);
        return format(oldDate, newtimestampType);
    }

    /**
     * 判断字符日期是否合法，根据给定时间戳格式
     *
     * @param strDate
     * @param timestampType
     * @return
     */
    public static boolean isValdateDate(String strDate, int timestampType) {

        DateTimeFormatter sdf = findFormat(timestampType);

        try {
            sdf.parseDateTime(strDate);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     *  开始日期(include)和结束日期(include)多少分钟
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static long findDateSpaceInMinuties(String startTime, String endTime)
    {

        DateTimeFormatter sdf = DateTimeFormat.
                forPattern("yyyy-MM-dd H:mm:ss");

        DateTime startDate = sdf.parseDateTime(startTime + " 00:00:00");

        DateTime endDate = sdf.parseDateTime(endTime + " 23:59:59");

        double minutes = (endDate.getMillis() - startDate.getMillis()) / (1000 * 60);

        return (long)Math.ceil(minutes);
    }

    /**
     * 从日期对象得到该日期零点的时间戳
     *
     * @param date
     * @param timestampType
     * @return
     */
    public static long getZeroTimeStampOfDay(DateTime date, int timestampType) {

        MutableDateTime zerotimeOfDay = date.toMutableDateTime();
        zerotimeOfDay.setTime(0, 0, 0, 0);
        return zerotimeOfDay.getMillis();


    }

    /**
     * 获取日期对应的时间
     * @param date
     * @param timestampType

     * @return
     */
    public static long getDateTimestamp(DateTime date, int timestampType) {
        long returnValue = -1;
        try {
            if (date == null) {
                return -1;
            }
            returnValue = date.getMillis();

            if (timestampType == TIMESTAMPTYPE_UNIX) {
                returnValue = returnValue / 1000;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnValue;
    }



    public static final DateTime convertStringToDate(String pattern, Locale locale,
                                                     DateTimeZone zone, String strDate)  {
        if (locale == null)
            locale = Locale.getDefault();
        if (zone == null)
            zone = DateTimeZone.getDefault();

        DateTimeFormatter df = DateTimeFormat.
                forPattern(pattern).withLocale(locale).withZone(zone);

        return df.parseDateTime(strDate);

    }


    public static final DateTime convertStringToDate(String strDate) {
        Locale locale = Locale.CHINESE;
        try {
            return convertStringToDate(DATE_PATTERN, locale, null, strDate);
        } catch (Exception e) {
            return null;
        }
    }

    public static final DateTime convertStringToDate(String strDate, String sytle) {
        Locale locale = Locale.CHINESE;
        try {
            return convertStringToDate(sytle, locale, null, strDate);
        } catch (Exception e) {
            return null;
        }
    }

    public static final String convertDateToString(String pattern,
                                                   Locale locale, DateTimeZone zone, DateTime aDate) {
        if (locale == null)
            locale = Locale.getDefault();
        if (zone == null)
            zone = DateTimeZone.getDefault();


        DateTimeFormatter df = DateTimeFormat.
                forPattern(pattern).withLocale(locale).withZone(zone);

        return df.print(aDate);
    }

    public static final String convertDateToString(String pattern, DateTime aDate) {
        Locale locale = Locale.CHINESE;
        return convertDateToString(pattern, locale, null, aDate);
    }

    public static  DateTime getBeginDateTime(Date date){
        DateTime datetime = new DateTime(date);
        MutableDateTime mutabelDatetime = datetime.toMutableDateTime();
        mutabelDatetime.setMillisOfDay(0);
        return mutabelDatetime.toDateTime();
    }
    public static  DateTime getBeginDateTime(DateTime date){
        return getBeginDateTime(date.toDate());
    }

    /**

     * 提供yyyy-MM-dd类型的日期字符串转化

     */
    public static final DateTime getBeginDate(String beginDate) {
        Locale locale = Locale.CHINESE;
        try {
            return convertStringToDate("yyyy-MM-dd HH:mm:ss", locale, null,
                    beginDate + " 00:00:00");
        } catch (Exception e) {
            return null;
        }
    }

    /**

     * 提供yyyy-MM-dd类型的日期字符串转化 专门提供Web页面结束日期转化 如输入2006-07-27，则转化为2006-07-28

     * 00:00:00

     */
    public static final DateTime getEndDate(String endDate) {
        Locale locale = Locale.CHINESE;
        try {
            DateTime date = convertStringToDate("yyyy-MM-dd HH:mm:ss", locale,
                    null, endDate + " 00:00:00");
            return new DateTime(date.getMillis() + 24 * 3600 * 1000);
        } catch (Exception e) {
            return null;
        }
    }

    /**

     * yyyy年mm月dd日 星期w

     */
    public static String getFullDateStr() {

        DateTime now = new DateTime() ;

        return DateTimeFormat.fullDateTime().withLocale(Locale.CHINESE).print(now);
    }

    /**

     * 计算两个日期之间相差的天数

     *

     * @param date1

     * @param date2

     * @return

     */

    public static int diffdates(DateTime date1, DateTime date2) {


        int days = Days.daysBetween(new DateTime(date1), new DateTime(date2)).getDays();

        return days;
    }

    /**

     *  前一天的24：00 往前1秒

     * @return

     */
    @Deprecated
    public static DateTime getYestardayEnd() {
        MutableDateTime dt = new MutableDateTime();
        dt.setMillisOfDay(0);
        dt.addMillis(-1000);
        return dt.toDateTime();
    }

    @Deprecated
    public static DateTime getYestardayStart() {
        MutableDateTime dt = new MutableDateTime();
        dt.addDays(-1);
        dt.setMillisOfDay(0);
        return dt.toDateTime();
    }

    public static DateTime getYesterdayEnd() {
        MutableDateTime dt = new MutableDateTime();
        dt.setMillisOfDay(0);
        dt.addMillis(-1000);
        return dt.toDateTime();
    }

    public static DateTime getYesterdayStart() {
        MutableDateTime dt = new MutableDateTime();
        dt.addDays(-1);
        dt.setMillisOfDay(0);
        return dt.toDateTime();
    }

    /**

     * 获取Next天

     */
    public static String addDays(String date, int amount) {

        DateTimeFormatter frm = DateTimeFormat
                .forPattern("yyyy-MM-dd HH:mm:ss");

        DateTime dt = frm.parseDateTime(date).plusDays(amount);

        return frm.print(dt);
    }

    /**

     * 获取Next MINUTE

     */
    public static String addMinutes(String date, int amount) {

        DateTimeFormatter frm = DateTimeFormat
                .forPattern("yyyy-MM-dd HH:mm:ss");

        DateTime dt = frm.parseDateTime(date).plusMinutes(amount);

        return frm.print(dt);
    }

    /**

     * 将字符串20080808 转换成 2008-08-08

     */
    public static String getDateForm(String date) {
        if (StringUtils.isBlank(date) || date.length() != 8) {
            return "0000-00-00";
        }
        return date.substring(0, 4) + "-" + date.substring(4, 6) + "-"
                + date.substring(6, 8);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        DateTime start = new DateTime(2004,1,1,0,0);
        DateTime end = new DateTime(2015,2,2,0,0);
        DateTime now = new DateTime();
    }

    /***
     * 判断指定时间(now)是否在指定的日期区间
     *
     * @param start
     * @param end
     * @param now
     * @return
     */
    public static boolean isBetween(DateTime start,DateTime end,DateTime now){
        if(start.compareTo(now) == -1 && end.compareTo(now) == 1){
            return true;
        }
        return false;
    }

    /***

     * deduce is the same day

     *

     * @return

     */
    public static boolean isSameDay(DateTime atime, DateTime nowDate) {

        return atime.getDayOfYear() == nowDate.getDayOfYear()
                && atime.getYear() == nowDate.getYear();
    }

    public static long getTimeMillisToAfterDaysHour(int days, int hourOfTomorrow) {

        DateTime d = new DateTime();
        d.plusDays(days);
        d.plusHours(hourOfTomorrow);

        return d.getMillis() - (new DateTime()).getMillis();
    }

    public static void sleep(long millisecond) {
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException localInterruptedException) {
        }
    }

    public static void SleepSec(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getGMTTimeString(long milliSeconds) {
        DateTimeFormatter sdf = DateTimeFormat
                .forPattern("E, d MMM yyyy HH:mm:ss 'GMT'");
        return sdf.print(milliSeconds);
    }

    public static String date(String fmt) {
        return DateTimeFormat.forPattern(fmt).print(new DateTime());
    }

    public static String date(String fmt, long t) {
        return DateTimeFormat.forPattern(fmt).print(t);
    }

    public static String date(String fmt, DateTime date) {
        return DateTimeFormat.forPattern(fmt).print(date);
    }

    public static String date8() {
        return DateTimeFormat.forPattern("yyyyMMdd").print(new DateTime());
    }

    public static String date8(DateTime date) {
        return DateTimeFormat.forPattern("yyyyMMdd").print(date);

    }

    public static String date10(DateTime date) {
        return DateTimeFormat.forPattern("yyyy-MM-dd").print(date);

    }

    public static String date10() {

        return DateTimeFormat.forPattern("yyyy-MM-dd").print(new DateTime());
    }

    public static String date10slash(DateTime date) {
        return DateTimeFormat.forPattern("MM/dd/yyyy").print(new DateTime());

    }

    public static String time6() {

        return DateTimeFormat.forPattern("HHmmss").print(new DateTime());
    }

    public static String time8(DateTime date) {
        return DateTimeFormat.forPattern("HH:mm:ss").print(date);

    }

    public static String time6(DateTime date) {

        return DateTimeFormat.forPattern("HHmmss").print(date);

    }

    public static String datetime14() {

        return DateTimeFormat.forPattern(Formater14)
                .print(new DateTime());

    }

    public static String datetime14Readable() {
        return DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").print(
                new DateTime());
    }

    public static String datetime14(DateTime date) {

        return DateTimeFormat.forPattern("yyyyMMddHHmmss").print(date);

    }

    public static String datetime14(long t) {

        return DateTimeFormat.forPattern("yyyyMMddHHmmss").print(t);

    }

    public static DateTime addMin(DateTime now, int amount) {

        return now.plusMinutes(amount);

    }

    public static DateTime addSec(DateTime now, int amount) {
        return now.plusSeconds(amount);
    }

    public static DateTime addDay(DateTime now, int amount) {
        return now.plusDays(amount);
    }

    public static DateTime addMonth(DateTime now, int amount) {
        return now.plusMonths(amount);
    }

    public static DateTime addYear(DateTime now, int amount) {
        return now.plusYears(amount);
    }

    public static Date parseDate19(String date) {
        return parse(Formatter19, date).toDate();
    }

    public static DateTime parseDate8(String date) {
        return parse("yyyyMMdd", date);
    }

    public static DateTime parseDate10(String date) {
        return parse("yyyy-MM-dd", date);
    }

    public static boolean validateDate8(String date) {
        DateTime d = parseDate8(date);
        return (d != null) && (date8(d).equals(date));
    }

    public static boolean validateDate10(String date) {
        DateTime d = parseDate10(date);
        return (d != null) && (date10(d).equals(date));
    }

    public static DateTime parseDatetime14(String datetime) {
        return parse("yyyyMMddHHmmss", datetime);
    }

    public static DateTime parseTime8(String datetime) {
        return parse("HH:mm:ss", datetime);
    }

    public static DateTime parseDatetime6(String datetime) {
        return parse("HHmmss", datetime);
    }

    public static DateTime parseTime6(String time) {
        return parse("HHmmss", time);
    }

    public static DateTime parse(String pattern, String dateTimeStr) {
        return DateTimeFormat.forPattern(pattern).parseDateTime(dateTimeStr);
    }

    public static DateTime parse(String format, String date,  Locale locale){
        return DateTimeFormat.forPattern(format).withLocale(locale)
                .parseDateTime(date);

    }

    public static boolean validateTime6(String time) {
        DateTime d = parseTime6(time);
        return (d != null) && (time6(d).equals(time));
    }

    public static int getDayOfWeek(String date) {
        DateTime day = parseDate8(date);

        return day.getDayOfWeek();

    }

    public static int diffInMin(DateTime d1, DateTime d2) {
        long t1 = d1.getMillis();
        long t2 = d2.getMillis();
        double unit = 60000.0D;
        int absDiff = (int) Math.ceil(Math.abs(t1 - t2) / unit);
        if (t1 > t2) {
            return absDiff;
        }
        return -absDiff;
    }

    public static int diffInSec(DateTime d1, DateTime d2) {

        long t1 = d1.getMillis();
        long t2 = d2.getMillis();
        double unit = 1000.0D;
        int absDiff = (int) Math.ceil(Math.abs(t1 - t2) / unit);
        if (t1 > t2) {
            return absDiff;
        }
        return -absDiff;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List<DateTime[]> slice(DateTime beginDate, DateTime endDate,
                                         int interval_sec) {
        List pieces = new ArrayList();
        while (beginDate.compareTo(endDate) <= 0) {
            DateTime nextEndDate = addSec(beginDate, interval_sec);
            if (nextEndDate.isAfter(endDate))
                nextEndDate = endDate;
            DateTime[] piece = new DateTime[2];
            piece[0] = beginDate;
            piece[1] = nextEndDate;
            pieces.add(piece);
            beginDate = addSec(nextEndDate, 1);
        }
        return pieces;
    }
}