package com.zsb.bluex.lib;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;

@BluexFunctionLib(category = "Math|Short")
public class ShortLib {

    @BluexFunction(displayName = "Short加法", executable = false)
    public static void AddShort(
            INPUT<Short> Num1,
            INPUT<Short> Num2,
            OUTPUT<Short> Ret
    ) {
        Ret.value = (short) (Num1.value + Num2.value);
    }

    @BluexFunction(displayName = "Short减法", executable = false)
    public static void SubtractShort(
            INPUT<Short> Num1,
            INPUT<Short> Num2,
            OUTPUT<Short> Ret
    ) {
        Ret.value = (short) (Num1.value - Num2.value);
    }

    @BluexFunction(displayName = "Short乘法", executable = false)
    public static void MultiplyShort(
            INPUT<Short> Num1,
            INPUT<Short> Num2,
            OUTPUT<Short> Ret
    ) {
        Ret.value = (short) (Num1.value * Num2.value);
    }

    @BluexFunction(displayName = "Short除法", executable = false)
    public static void DivideShort(
            INPUT<Short> Num1,
            INPUT<Short> Num2,
            OUTPUT<Short> Ret
    ) {
        if (Num2.value == 0) {
            throw new ArithmeticException("Division by zero");
        }
        Ret.value = (short) (Num1.value / Num2.value);
    }

    @BluexFunction(displayName = "Short取余", executable = false)
    public static void ModuloShort(
            INPUT<Short> Num1,
            INPUT<Short> Num2,
            OUTPUT<Short> Ret
    ) {
        if (Num2.value == 0) {
            throw new ArithmeticException("Division by zero");
        }
        Ret.value = (short) (Num1.value % Num2.value);
    }

    @BluexFunction(displayName = "Short绝对值", executable = false)
    public static void AbsShort(
            INPUT<Short> Num,
            OUTPUT<Short> Ret
    ) {
        Ret.value = (short) Math.abs(Num.value);
    }

    @BluexFunction(displayName = "Short最大值", executable = false)
    public static void MaxShort(
            INPUT<Short> Num1,
            INPUT<Short> Num2,
            OUTPUT<Short> Max
    ) {
        Max.value = (short) Math.max(Num1.value, Num2.value);
    }

    @BluexFunction(displayName = "Short最小值", executable = false)
    public static void MinShort(
            INPUT<Short> Num1,
            INPUT<Short> Num2,
            OUTPUT<Short> Min
    ) {
        Min.value = (short) Math.min(Num1.value, Num2.value);
    }

    @BluexFunction(displayName = "Short幂运算", executable = false)
    public static void PowShort(
            INPUT<Short> Base,
            INPUT<Short> Exp,
            OUTPUT<Short> Ret
    ) {
        Ret.value = (short) Math.pow(Base.value, Exp.value);
    }

    @BluexFunction(displayName = "Short平方根", executable = false)
    public static void SqrtShort(
            INPUT<Short> Num,
            OUTPUT<Short> Ret
    ) {
        if (Num.value < 0) {
            throw new ArithmeticException("Negative number cannot have an integer square root");
        }
        Ret.value = (short) Math.sqrt(Num.value);
    }

    @BluexFunction(displayName = "Short取负", executable = false)
    public static void NegateShort(
            INPUT<Short> Num,
            OUTPUT<Short> Ret
    ) {
        Ret.value = (short) -Num.value;
    }

    // 类型转换

    @BluexFunction(displayName = "Short转Int", executable = false)
    public static void ShortToInteger(
            INPUT<Short> In,
            OUTPUT<Integer> Out
    ) {
        Out.value = In.value.intValue();
    }

    @BluexFunction(displayName = "Short转Float", executable = false)
    public static void ShortToFloat(
            INPUT<Short> In,
            OUTPUT<Float> Out
    ) {
        Out.value = In.value.floatValue();
    }

    @BluexFunction(displayName = "Short转Double", executable = false)
    public static void ShortToDouble(
            INPUT<Short> In,
            OUTPUT<Double> Out
    ) {
        Out.value = In.value.doubleValue();
    }

    @BluexFunction(displayName = "Short转Long", executable = false)
    public static void ShortToLong(
            INPUT<Short> In,
            OUTPUT<Long> Out
    ) {
        Out.value = In.value.longValue();
    }

    @BluexFunction(displayName = "Short转Byte", executable = false)
    public static void ShortToByte(
            INPUT<Short> In,
            OUTPUT<Byte> Out
    ) {
        Out.value = In.value.byteValue();
    }

    @BluexFunction(displayName = "Short转Bool", executable = false)
    public static void ShortToBoolean(
            INPUT<Short> In,
            OUTPUT<Boolean> Out
    ) {
        Out.value = In.value != 0;
    }

    @BluexFunction(displayName = "Short转Char", executable = false)
    public static void ShortToChar(
            INPUT<Short> In,
            OUTPUT<Character> Out
    ) {
        Out.value = (char) In.value.intValue();
    }

    @BluexFunction(displayName = "Short转String", executable = false)
    public static void ShortToString(
            INPUT<Short> In,
            OUTPUT<String> Out
    ) {
        Out.value = String.valueOf(In.value);
    }
}
