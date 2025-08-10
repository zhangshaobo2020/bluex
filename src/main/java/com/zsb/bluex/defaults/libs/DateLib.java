package com.zsb.bluex.defaults.libs;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@BluexFunctionLib(category = "时间|Date")
public class DateLib {

    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @BluexFunction(displayName = "当前日期时间", executable = false)
    public static void Now(
            OUTPUT<Date> Out
    ) {
        Out.value = new Date();
    }

    @BluexFunction(displayName = "创建日期", executable = false)
    public static void CreateDate(
            INPUT<Integer> Year,
            INPUT<Integer> Month, // 1-12
            INPUT<Integer> Day,
            INPUT<Integer> Hour,
            INPUT<Integer> Minute,
            INPUT<Integer> Second,
            OUTPUT<Date> Out
    ) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Year.value);
        cal.set(Calendar.MONTH, Month.value - 1); // Java月份从0开始
        cal.set(Calendar.DAY_OF_MONTH, Day.value);
        cal.set(Calendar.HOUR_OF_DAY, Hour.value);
        cal.set(Calendar.MINUTE, Minute.value);
        cal.set(Calendar.SECOND, Second.value);
        cal.set(Calendar.MILLISECOND, 0);
        Out.value = cal.getTime();
    }

    @BluexFunction(displayName = "日期加天数", executable = false)
    public static void AddDays(
            INPUT<Date> Date,
            INPUT<Integer> Days,
            OUTPUT<Date> Out
    ) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.value);
        cal.add(Calendar.DAY_OF_MONTH, Days.value);
        Out.value = cal.getTime();
    }

    @BluexFunction(displayName = "日期加月数", executable = false)
    public static void AddMonths(
            INPUT<Date> Date,
            INPUT<Integer> Months,
            OUTPUT<Date> Out
    ) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.value);
        cal.add(Calendar.MONTH, Months.value);
        Out.value = cal.getTime();
    }

    @BluexFunction(displayName = "日期加年数", executable = false)
    public static void AddYears(
            INPUT<Date> Date,
            INPUT<Integer> Years,
            OUTPUT<Date> Out
    ) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.value);
        cal.add(Calendar.YEAR, Years.value);
        Out.value = cal.getTime();
    }

    @BluexFunction(displayName = "日期相差天数", executable = false)
    public static void DiffDays(
            INPUT<Date> Date1,
            INPUT<Date> Date2,
            OUTPUT<Long> Out
    ) {
        long diffMillis = Date1.value.getTime() - Date2.value.getTime();
        Out.value = diffMillis / (1000 * 60 * 60 * 24);
    }

    @BluexFunction(displayName = "日期相差毫秒", executable = false)
    public static void DiffMillis(
            INPUT<Date> Date1,
            INPUT<Date> Date2,
            OUTPUT<Long> Out
    ) {
        Out.value = Date1.value.getTime() - Date2.value.getTime();
    }

    @BluexFunction(displayName = "日期格式化为字符串", executable = false)
    public static void FormatDate(
            INPUT<Date> Date,
            INPUT<String> Pattern,
            OUTPUT<String> Out
    ) {
        String pattern = (Pattern.value == null || Pattern.value.isEmpty()) ? DEFAULT_FORMAT : Pattern.value;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Out.value = sdf.format(Date.value);
    }

    @BluexFunction(displayName = "字符串解析为日期", executable = false)
    public static void ParseDate(
            INPUT<String> DateStr,
            INPUT<String> Pattern,
            OUTPUT<Date> Out
    ) {
        try {
            String pattern = (Pattern.value == null || Pattern.value.isEmpty()) ? DEFAULT_FORMAT : Pattern.value;
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Out.value = sdf.parse(DateStr.value);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date string or pattern: " + e.getMessage(), e);
        }
    }

    @BluexFunction(displayName = "日期等于", executable = false)
    public static void EqualDate(
            INPUT<Date> Date1,
            INPUT<Date> Date2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Date1.value.equals(Date2.value);
    }

    @BluexFunction(displayName = "日期早于", executable = false)
    public static void BeforeDate(
            INPUT<Date> Date1,
            INPUT<Date> Date2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Date1.value.before(Date2.value);
    }

    @BluexFunction(displayName = "日期晚于", executable = false)
    public static void AfterDate(
            INPUT<Date> Date1,
            INPUT<Date> Date2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Date1.value.after(Date2.value);
    }

    @BluexFunction(displayName = "时间戳转日期", executable = false)
    public static void TimestampToDate(
            INPUT<Long> Timestamp,
            OUTPUT<Date> Out
    ) {
        Out.value = new Date(Timestamp.value);
    }

    @BluexFunction(displayName = "日期转时间戳", executable = false)
    public static void DateToTimestamp(
            INPUT<Date> DateIn,
            OUTPUT<Long> Out
    ) {
        Out.value = DateIn.value.getTime();
    }
}
