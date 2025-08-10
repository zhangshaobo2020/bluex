package com.zsb.bluex.defaults.libs;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@BluexFunctionLib(category = "时间|LocalDateTime")
public class LocalDateTimeLib {

    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @BluexFunction(displayName = "当前时间", executable = false)
    public static void Now(
            OUTPUT<LocalDateTime> Out
    ) {
        Out.value = LocalDateTime.now();
    }

    @BluexFunction(displayName = "创建时间", executable = false)
    public static void CreateLocalDateTime(
            INPUT<Integer> Year,
            INPUT<Integer> Month, // 1-12
            INPUT<Integer> Day,
            INPUT<Integer> Hour,
            INPUT<Integer> Minute,
            INPUT<Integer> Second,
            OUTPUT<LocalDateTime> Out
    ) {
        Out.value = LocalDateTime.of(
                Year.value,
                Month.value,
                Day.value,
                Hour.value,
                Minute.value,
                Second.value
        );
    }

    @BluexFunction(displayName = "时间加年数", executable = false)
    public static void AddYears(
            INPUT<LocalDateTime> Time,
            INPUT<Long> Years,
            OUTPUT<LocalDateTime> Out
    ) {
        Out.value = Time.value.plusYears(Years.value);
    }

    @BluexFunction(displayName = "时间加月数", executable = false)
    public static void AddMonths(
            INPUT<LocalDateTime> Time,
            INPUT<Long> Months,
            OUTPUT<LocalDateTime> Out
    ) {
        Out.value = Time.value.plusMonths(Months.value);
    }

    @BluexFunction(displayName = "时间加天数", executable = false)
    public static void AddDays(
            INPUT<LocalDateTime> Time,
            INPUT<Long> Days,
            OUTPUT<LocalDateTime> Out
    ) {
        Out.value = Time.value.plusDays(Days.value);
    }

    @BluexFunction(displayName = "时间加小时数", executable = false)
    public static void AddHours(
            INPUT<LocalDateTime> Time,
            INPUT<Long> Hours,
            OUTPUT<LocalDateTime> Out
    ) {
        Out.value = Time.value.plusHours(Hours.value);
    }

    @BluexFunction(displayName = "时间加分钟数", executable = false)
    public static void AddMinutes(
            INPUT<LocalDateTime> Time,
            INPUT<Long> Minutes,
            OUTPUT<LocalDateTime> Out
    ) {
        Out.value = Time.value.plusMinutes(Minutes.value);
    }

    @BluexFunction(displayName = "时间加秒数", executable = false)
    public static void AddSeconds(
            INPUT<LocalDateTime> Time,
            INPUT<Long> Seconds,
            OUTPUT<LocalDateTime> Out
    ) {
        Out.value = Time.value.plusSeconds(Seconds.value);
    }

    @BluexFunction(displayName = "时间差(天)", executable = false)
    public static void DiffDays(
            INPUT<LocalDateTime> Time1,
            INPUT<LocalDateTime> Time2,
            OUTPUT<Long> Out
    ) {
        Out.value = ChronoUnit.DAYS.between(Time2.value, Time1.value);
    }

    @BluexFunction(displayName = "时间差(毫秒)", executable = false)
    public static void DiffMillis(
            INPUT<LocalDateTime> Time1,
            INPUT<LocalDateTime> Time2,
            OUTPUT<Long> Out
    ) {
        Out.value = ChronoUnit.MILLIS.between(Time2.value, Time1.value);
    }

    @BluexFunction(displayName = "时间格式化为字符串", executable = false)
    public static void FormatLocalDateTime(
            INPUT<LocalDateTime> Time,
            INPUT<String> Pattern,
            OUTPUT<String> Out
    ) {
        String pattern = (Pattern.value == null || Pattern.value.isEmpty()) ? DEFAULT_FORMAT : Pattern.value;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        Out.value = Time.value.format(formatter);
    }

    @BluexFunction(displayName = "字符串解析为时间", executable = false)
    public static void ParseLocalDateTime(
            INPUT<String> TimeStr,
            INPUT<String> Pattern,
            OUTPUT<LocalDateTime> Out
    ) {
        String pattern = (Pattern.value == null || Pattern.value.isEmpty()) ? DEFAULT_FORMAT : Pattern.value;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        Out.value = LocalDateTime.parse(TimeStr.value, formatter);
    }

    @BluexFunction(displayName = "时间等于", executable = false)
    public static void EqualLocalDateTime(
            INPUT<LocalDateTime> Time1,
            INPUT<LocalDateTime> Time2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Time1.value.isEqual(Time2.value);
    }

    @BluexFunction(displayName = "时间早于", executable = false)
    public static void BeforeLocalDateTime(
            INPUT<LocalDateTime> Time1,
            INPUT<LocalDateTime> Time2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Time1.value.isBefore(Time2.value);
    }

    @BluexFunction(displayName = "时间晚于", executable = false)
    public static void AfterLocalDateTime(
            INPUT<LocalDateTime> Time1,
            INPUT<LocalDateTime> Time2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Time1.value.isAfter(Time2.value);
    }
}
