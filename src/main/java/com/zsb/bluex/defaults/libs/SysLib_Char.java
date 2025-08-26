package com.zsb.bluex.defaults.libs;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;

@BluexFunctionLib(category = "Java基本类型|Char")
public class SysLib_Char {

    @BluexFunction(category = "运算|Char相加", executable = false)
    public static void AddChar(
            INPUT<Character> Char1,
            INPUT<Character> Char2,
            OUTPUT<Character> Ret
    ) {
        Ret.value = (char) (Char1.value + Char2.value);
    }

    @BluexFunction(category = "运算|Char相减", executable = false)
    public static void SubtractChar(
            INPUT<Character> Char1,
            INPUT<Character> Char2,
            OUTPUT<Character> Ret
    ) {
        Ret.value = (char) (Char1.value - Char2.value);
    }

    @BluexFunction(category = "运算|Char相乘", executable = false)
    public static void MultiplyChar(
            INPUT<Character> Char1,
            INPUT<Character> Char2,
            OUTPUT<Character> Ret
    ) {
        Ret.value = (char) (Char1.value * Char2.value);
    }

    @BluexFunction(category = "运算|Char相除", executable = false)
    public static void DivideChar(
            INPUT<Character> Char1,
            INPUT<Character> Char2,
            OUTPUT<Character> Ret
    ) {
        if (Char2.value == 0) {
            throw new ArithmeticException("Division by zero");
        }
        Ret.value = (char) (Char1.value / Char2.value);
    }

    @BluexFunction(category = "运算|Char取模", executable = false)
    public static void ModuloChar(
            INPUT<Character> Char1,
            INPUT<Character> Char2,
            OUTPUT<Character> Ret
    ) {
        if (Char2.value == 0) {
            throw new ArithmeticException("Division by zero");
        }
        Ret.value = (char) (Char1.value % Char2.value);
    }

    @BluexFunction(category = "类型转换|Char转Int", executable = false)
    public static void CharToInteger(
            INPUT<Character> In,
            OUTPUT<Integer> Out
    ) {
        Out.value = (int) In.value;
    }

    @BluexFunction(category = "类型转换|Char转Byte", executable = false)
    public static void CharToByte(
            INPUT<Character> In,
            OUTPUT<Byte> Out
    ) {
        Out.value = (byte) In.value.charValue();
    }

    @BluexFunction(category = "类型转换|Char转Short", executable = false)
    public static void CharToShort(
            INPUT<Character> In,
            OUTPUT<Short> Out
    ) {
        Out.value = (short) In.value.charValue();
    }

    @BluexFunction(category = "类型转换|Char转Long", executable = false)
    public static void CharToLong(
            INPUT<Character> In,
            OUTPUT<Long> Out
    ) {
        Out.value = (long) In.value;
    }

    @BluexFunction(category = "类型转换|Char转Float", executable = false)
    public static void CharToFloat(
            INPUT<Character> In,
            OUTPUT<Float> Out
    ) {
        Out.value = (float) In.value;
    }

    @BluexFunction(category = "类型转换|Char转Double", executable = false)
    public static void CharToDouble(
            INPUT<Character> In,
            OUTPUT<Double> Out
    ) {
        Out.value = (double) In.value;
    }

    @BluexFunction(category = "类型转换|Char转Boolean（非零为真）", executable = false)
    public static void CharToBoolean(
            INPUT<Character> In,
            OUTPUT<Boolean> Out
    ) {
        Out.value = In.value != '\0';
    }

    @BluexFunction(category = "类型转换|Char转String", executable = false)
    public static void CharToString(
            INPUT<Character> In,
            OUTPUT<String> Out
    ) {
        Out.value = String.valueOf(In.value);
    }

    @BluexFunction(category = "比较|字符是否为数字", executable = false)
    public static void IsDigit(
            INPUT<Character> In,
            OUTPUT<Boolean> Out
    ) {
        Out.value = Character.isDigit(In.value);
    }

    @BluexFunction(category = "比较|字符是否为字母", executable = false)
    public static void IsLetter(
            INPUT<Character> In,
            OUTPUT<Boolean> Out
    ) {
        Out.value = Character.isLetter(In.value);
    }

    @BluexFunction(category = "其他|字符是否为空白", executable = false)
    public static void IsWhitespace(
            INPUT<Character> In,
            OUTPUT<Boolean> Out
    ) {
        Out.value = Character.isWhitespace(In.value);
    }

    @BluexFunction(category = "其他|字符转大写", executable = false)
    public static void ToUpperCase(
            INPUT<Character> In,
            OUTPUT<Character> Out
    ) {
        Out.value = Character.toUpperCase(In.value);
    }

    @BluexFunction(category = "其他|字符转小写", executable = false)
    public static void ToLowerCase(
            INPUT<Character> In,
            OUTPUT<Character> Out
    ) {
        Out.value = Character.toLowerCase(In.value);
    }
}
