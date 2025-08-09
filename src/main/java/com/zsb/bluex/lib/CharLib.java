package com.zsb.bluex.lib;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;

@BluexFunctionLib(category = "Char")
public class CharLib {

    @BluexFunction(displayName = "Char加法（字符码相加）", executable = false)
    public static void AddChar(
            INPUT<Character> Char1,
            INPUT<Character> Char2,
            OUTPUT<Character> Ret
    ) {
        Ret.value = (char) (Char1.value + Char2.value);
    }

    @BluexFunction(displayName = "Char减法（字符码相减）", executable = false)
    public static void SubtractChar(
            INPUT<Character> Char1,
            INPUT<Character> Char2,
            OUTPUT<Character> Ret
    ) {
        Ret.value = (char) (Char1.value - Char2.value);
    }

    @BluexFunction(displayName = "Char乘法（字符码相乘）", executable = false)
    public static void MultiplyChar(
            INPUT<Character> Char1,
            INPUT<Character> Char2,
            OUTPUT<Character> Ret
    ) {
        Ret.value = (char) (Char1.value * Char2.value);
    }

    @BluexFunction(displayName = "Char除法（字符码相除）", executable = false)
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

    @BluexFunction(displayName = "Char取余（字符码取模）", executable = false)
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

    @BluexFunction(displayName = "Char转整数（Unicode码）", executable = false)
    public static void CharToInteger(
            INPUT<Character> In,
            OUTPUT<Integer> Out
    ) {
        Out.value = (int) In.value;
    }

    @BluexFunction(displayName = "Char转Byte", executable = false)
    public static void CharToByte(
            INPUT<Character> In,
            OUTPUT<Byte> Out
    ) {
        Out.value = (byte) In.value.charValue();
    }

    @BluexFunction(displayName = "Char转Short", executable = false)
    public static void CharToShort(
            INPUT<Character> In,
            OUTPUT<Short> Out
    ) {
        Out.value = (short) In.value.charValue();
    }

    @BluexFunction(displayName = "Char转Long", executable = false)
    public static void CharToLong(
            INPUT<Character> In,
            OUTPUT<Long> Out
    ) {
        Out.value = (long) In.value;
    }

    @BluexFunction(displayName = "Char转Float", executable = false)
    public static void CharToFloat(
            INPUT<Character> In,
            OUTPUT<Float> Out
    ) {
        Out.value = (float) In.value;
    }

    @BluexFunction(displayName = "Char转Double", executable = false)
    public static void CharToDouble(
            INPUT<Character> In,
            OUTPUT<Double> Out
    ) {
        Out.value = (double) In.value;
    }

    @BluexFunction(displayName = "Char转Boolean（非零为真）", executable = false)
    public static void CharToBoolean(
            INPUT<Character> In,
            OUTPUT<Boolean> Out
    ) {
        Out.value = In.value != '\0';
    }

    @BluexFunction(displayName = "Char转String", executable = false)
    public static void CharToString(
            INPUT<Character> In,
            OUTPUT<String> Out
    ) {
        Out.value = String.valueOf(In.value);
    }

    @BluexFunction(displayName = "字符是否为数字", executable = false)
    public static void IsDigit(
            INPUT<Character> In,
            OUTPUT<Boolean> Out
    ) {
        Out.value = Character.isDigit(In.value);
    }

    @BluexFunction(displayName = "字符是否为字母", executable = false)
    public static void IsLetter(
            INPUT<Character> In,
            OUTPUT<Boolean> Out
    ) {
        Out.value = Character.isLetter(In.value);
    }

    @BluexFunction(displayName = "字符是否为空白", executable = false)
    public static void IsWhitespace(
            INPUT<Character> In,
            OUTPUT<Boolean> Out
    ) {
        Out.value = Character.isWhitespace(In.value);
    }

    @BluexFunction(displayName = "字符转大写", executable = false)
    public static void ToUpperCase(
            INPUT<Character> In,
            OUTPUT<Character> Out
    ) {
        Out.value = Character.toUpperCase(In.value);
    }

    @BluexFunction(displayName = "字符转小写", executable = false)
    public static void ToLowerCase(
            INPUT<Character> In,
            OUTPUT<Character> Out
    ) {
        Out.value = Character.toLowerCase(In.value);
    }
}
