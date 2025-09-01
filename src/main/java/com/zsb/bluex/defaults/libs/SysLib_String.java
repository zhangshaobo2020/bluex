package com.zsb.bluex.defaults.libs;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@BluexFunctionLib(category = "Java常见类型|String")
public class SysLib_String {

    @BluexFunction(category = "打印到控制台")
    public static void Print(
            INPUT<String> Str
    ) {
        log.info(Str.value);
    }

    @BluexFunction(category = "操作|两个字符串拼接", executable = false)
    public static void Concat(
            INPUT<String> Str1,
            INPUT<String> Str2,
            OUTPUT<String> Out
    ) {
        Out.value = (Str1.value == null ? "" : Str1.value) + (Str2.value == null ? "" : Str2.value);
    }

    @BluexFunction(category = "操作|三个字符串拼接", executable = false)
    public static void ConcatThreeParam(
            INPUT<String> Str1,
            INPUT<String> Str2,
            INPUT<String> Str3,
            OUTPUT<String> Out
    ) {
        Out.value = (Str1.value == null ? "" : Str1.value)
                + (Str2.value == null ? "" : Str2.value)
                + (Str3.value == null ? "" : Str3.value);
    }

    @BluexFunction(category = "其他|字符串长度", executable = false)
    public static void Length(
            INPUT<String> Str,
            OUTPUT<Integer> Out
    ) {
        Out.value = Str.value == null ? 0 : Str.value.length();
    }

    @BluexFunction(category = "查找|字符串是否包含", executable = false)
    public static void Contains(
            INPUT<String> Str,
            INPUT<String> SubStr,
            OUTPUT<Boolean> Out
    ) {
        if (Str.value == null || SubStr.value == null) {
            Out.value = false;
        } else {
            Out.value = Str.value.contains(SubStr.value);
        }
    }

    @BluexFunction(category = "查找|字符串索引查找", executable = false)
    public static void IndexOf(
            INPUT<String> Str,
            INPUT<String> SubStr,
            OUTPUT<Integer> Out
    ) {
        if (Str.value == null || SubStr.value == null) {
            Out.value = -1;
        } else {
            Out.value = Str.value.indexOf(SubStr.value);
        }
    }

    @BluexFunction(category = "查找|字符串最后索引查找", executable = false)
    public static void LastIndexOf(
            INPUT<String> Str,
            INPUT<String> SubStr,
            OUTPUT<Integer> Out
    ) {
        if (Str.value == null || SubStr.value == null) {
            Out.value = -1;
        } else {
            Out.value = Str.value.lastIndexOf(SubStr.value);
        }
    }

    @BluexFunction(category = "操作|字符串分割", executable = false)
    public static void Split(
            INPUT<String> Str,
            INPUT<String> Delimiter,
            OUTPUT<List<String>> Out
    ) {
        if (Str.value == null || Delimiter.value == null) {
            Out.value = Collections.emptyList();
        } else {
            Out.value = Arrays.asList(Str.value.split(Delimiter.value));
        }
    }

    @BluexFunction(category = "操作|字符串转大写", executable = false)
    public static void ToUpper(
            INPUT<String> Str,
            OUTPUT<String> Out
    ) {
        Out.value = Str.value == null ? null : Str.value.toUpperCase();
    }

    @BluexFunction(category = "操作|字符串转小写", executable = false)
    public static void ToLower(
            INPUT<String> Str,
            OUTPUT<String> Out
    ) {
        Out.value = Str.value == null ? null : Str.value.toLowerCase();
    }

    @BluexFunction(category = "操作|字符串替换", executable = false)
    public static void Replace(
            INPUT<String> Str,
            INPUT<String> Target,
            INPUT<String> Replacement,
            OUTPUT<String> Out
    ) {
        if (Str.value == null || Target.value == null || Replacement.value == null) {
            Out.value = Str.value;
        } else {
            Out.value = Str.value.replace(Target.value, Replacement.value);
        }
    }

    @BluexFunction(category = "操作|字符串裁剪", executable = false)
    public static void Trim(
            INPUT<String> Str,
            OUTPUT<String> Out
    ) {
        Out.value = Str.value == null ? null : Str.value.trim();
    }

    @BluexFunction(category = "查找|字符串子串(起始索引)", executable = false)
    public static void SubstringFrom(
            INPUT<String> Str,
            INPUT<Integer> StartIndex,
            OUTPUT<String> Out
    ) {
        if (Str.value == null || StartIndex.value == null) {
            Out.value = null;
        } else {
            int start = StartIndex.value;
            if (start < 0) start = 0;
            if (start > Str.value.length()) start = Str.value.length();
            Out.value = Str.value.substring(start);
        }
    }

    @BluexFunction(category = "查找|字符串子串(起始索引和长度)", executable = false)
    public static void SubstringLength(
            INPUT<String> Str,
            INPUT<Integer> StartIndex,
            INPUT<Integer> Length,
            OUTPUT<String> Out
    ) {
        if (Str.value == null || StartIndex.value == null || Length.value == null) {
            Out.value = null;
        } else {
            int start = StartIndex.value;
            int len = Length.value;
            if (start < 0) start = 0;
            if (len < 0) len = 0;
            if (start > Str.value.length()) start = Str.value.length();
            int end = Math.min(start + len, Str.value.length());
            Out.value = Str.value.substring(start, end);
        }
    }

    @BluexFunction(category = "其他|字符串是否为空", executable = false)
    public static void IsEmpty(
            INPUT<String> Str,
            OUTPUT<Boolean> Out
    ) {
        Out.value = (Str.value == null || Str.value.isEmpty());
    }

    @BluexFunction(category = "其他|字符串是否为空或空白", executable = false)
    public static void IsBlank(
            INPUT<String> Str,
            OUTPUT<Boolean> Out
    ) {
        Out.value = (Str.value == null || Str.value.trim().isEmpty());
    }

    @BluexFunction(category = "类型转换|String转Int", executable = false)
    public static void StringToInteger(
            INPUT<String> Str,
            OUTPUT<Integer> Out
    ) {
        try {
            Out.value = Integer.parseInt(Str.value);
        } catch (Exception e) {
            Out.value = 0;
        }
    }

    @BluexFunction(category = "类型转换|String转Bool", executable = false)
    public static void StringToBoolean(
            INPUT<String> Str,
            OUTPUT<Boolean> Out
    ) {
        if (Str.value == null) {
            Out.value = false;
            return;
        }
        String s = Str.value.trim().toLowerCase();
        Out.value = s.equals("true") || s.equals("1") || s.equals("yes") || s.equals("on");
    }

    @BluexFunction(category = "类型转换|String转Double", executable = false)
    public static void StringToDouble(
            INPUT<String> Str,
            OUTPUT<Double> Out
    ) {
        try {
            Out.value = Double.parseDouble(Str.value);
        } catch (Exception e) {
            Out.value = 0.0;
        }
    }

    @BluexFunction(category = "类型转换|String转Float", executable = false)
    public static void StringToFloat(
            INPUT<String> Str,
            OUTPUT<Float> Out
    ) {
        try {
            Out.value = Float.parseFloat(Str.value);
        } catch (Exception e) {
            Out.value = 0.0f;
        }
    }

    @BluexFunction(category = "类型转换|String转Long", executable = false)
    public static void StringToLong(
            INPUT<String> Str,
            OUTPUT<Long> Out
    ) {
        try {
            Out.value = Long.parseLong(Str.value);
        } catch (Exception e) {
            Out.value = 0L;
        }
    }

    @BluexFunction(category = "类型转换|String转Char", executable = false)
    public static void StringToChar(
            INPUT<String> Str,
            OUTPUT<Character> Out
    ) {
        if (Str.value == null || Str.value.isEmpty()) {
            Out.value = '\0';
        } else {
            Out.value = Str.value.charAt(0);
        }
    }

    @BluexFunction(category = "操作|字符串重复", executable = false)
    public static void Repeat(
            INPUT<String> Str,
            INPUT<Integer> Count,
            OUTPUT<String> Out
    ) {
        if (Str.value == null || Count.value == null || Count.value <= 0) {
            Out.value = "";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < Count.value; i++) {
                sb.append(Str.value);
            }
            Out.value = sb.toString();
        }
    }
}
