package com.zsb.bluex.defaults.libs;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;

@BluexFunctionLib(category = "Java基本类型|Short")
public class SysLib_Short {

    @BluexFunction(category = "运算|Short相加", executable = false)
    public static void AddShort(
            INPUT<Short> Num1,
            INPUT<Short> Num2,
            OUTPUT<Short> Ret
    ) {
        Ret.value = (short) (Num1.value + Num2.value);
    }

    @BluexFunction(category = "运算|Short相减", executable = false)
    public static void SubtractShort(
            INPUT<Short> Num1,
            INPUT<Short> Num2,
            OUTPUT<Short> Ret
    ) {
        Ret.value = (short) (Num1.value - Num2.value);
    }

    @BluexFunction(category = "运算|Short相乘", executable = false)
    public static void MultiplyShort(
            INPUT<Short> Num1,
            INPUT<Short> Num2,
            OUTPUT<Short> Ret
    ) {
        Ret.value = (short) (Num1.value * Num2.value);
    }

    @BluexFunction(category = "运算|Short相除", executable = false)
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

    @BluexFunction(category = "运算|Short取余", executable = false)
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

    @BluexFunction(category = "运算|Short绝对值", executable = false)
    public static void AbsShort(
            INPUT<Short> Num,
            OUTPUT<Short> Ret
    ) {
        Ret.value = (short) Math.abs(Num.value);
    }

    @BluexFunction(category = "运算|Short最大值", executable = false)
    public static void MaxShort(
            INPUT<Short> Num1,
            INPUT<Short> Num2,
            OUTPUT<Short> Max
    ) {
        Max.value = (short) Math.max(Num1.value, Num2.value);
    }

    @BluexFunction(category = "运算|Short最小值", executable = false)
    public static void MinShort(
            INPUT<Short> Num1,
            INPUT<Short> Num2,
            OUTPUT<Short> Min
    ) {
        Min.value = (short) Math.min(Num1.value, Num2.value);
    }

    @BluexFunction(category = "运算|Short幂运算", executable = false)
    public static void PowShort(
            INPUT<Short> Base,
            INPUT<Short> Exp,
            OUTPUT<Short> Ret
    ) {
        Ret.value = (short) Math.pow(Base.value, Exp.value);
    }

    @BluexFunction(category = "运算|Short平方根", executable = false)
    public static void SqrtShort(
            INPUT<Short> Num,
            OUTPUT<Short> Ret
    ) {
        if (Num.value < 0) {
            throw new ArithmeticException("Negative number cannot have an integer square root");
        }
        Ret.value = (short) Math.sqrt(Num.value);
    }

    @BluexFunction(category = "运算|Short取负", executable = false)
    public static void NegateShort(
            INPUT<Short> Num,
            OUTPUT<Short> Ret
    ) {
        Ret.value = (short) -Num.value;
    }

    // 类型转换

    @BluexFunction(category = "类型转换|Short转Int", executable = false)
    public static void ShortToInteger(
            INPUT<Short> In,
            OUTPUT<Integer> Out
    ) {
        Out.value = In.value.intValue();
    }

    @BluexFunction(category = "类型转换|Short转Float", executable = false)
    public static void ShortToFloat(
            INPUT<Short> In,
            OUTPUT<Float> Out
    ) {
        Out.value = In.value.floatValue();
    }

    @BluexFunction(category = "类型转换|Short转Double", executable = false)
    public static void ShortToDouble(
            INPUT<Short> In,
            OUTPUT<Double> Out
    ) {
        Out.value = In.value.doubleValue();
    }

    @BluexFunction(category = "类型转换|Short转Long", executable = false)
    public static void ShortToLong(
            INPUT<Short> In,
            OUTPUT<Long> Out
    ) {
        Out.value = In.value.longValue();
    }

    @BluexFunction(category = "类型转换|Short转Byte", executable = false)
    public static void ShortToByte(
            INPUT<Short> In,
            OUTPUT<Byte> Out
    ) {
        Out.value = In.value.byteValue();
    }

    @BluexFunction(category = "类型转换|Short转Bool", executable = false)
    public static void ShortToBoolean(
            INPUT<Short> In,
            OUTPUT<Boolean> Out
    ) {
        Out.value = In.value != 0;
    }

    @BluexFunction(category = "类型转换|Short转Char", executable = false)
    public static void ShortToChar(
            INPUT<Short> In,
            OUTPUT<Character> Out
    ) {
        Out.value = (char) In.value.intValue();
    }

    @BluexFunction(category = "类型转换|Short转String", executable = false)
    public static void ShortToString(
            INPUT<Short> In,
            OUTPUT<String> Out
    ) {
        Out.value = String.valueOf(In.value);
    }

    @BluexFunction(category = "比较|Short等于", executable = false)
    public static void Equal(
            INPUT<Short> Num1,
            INPUT<Short> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Num1.value.equals(Num2.value);
    }

    @BluexFunction(category = "比较|Short不等于", executable = false)
    public static void NotEqual(
            INPUT<Short> Num1,
            INPUT<Short> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = !Num1.value.equals(Num2.value);
    }

    @BluexFunction(category = "比较|Short大于", executable = false)
    public static void GreaterThan(
            INPUT<Short> Num1,
            INPUT<Short> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Num1.value > Num2.value;
    }

    @BluexFunction(category = "比较|Short大于等于", executable = false)
    public static void GreaterEqualThan(
            INPUT<Short> Num1,
            INPUT<Short> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Num1.value >= Num2.value;
    }

    @BluexFunction(category = "比较|Short小于", executable = false)
    public static void LessThan(
            INPUT<Short> Num1,
            INPUT<Short> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Num1.value < Num2.value;
    }

    @BluexFunction(category = "比较|Short小于等于", executable = false)
    public static void LessEqualThan(
            INPUT<Short> Num1,
            INPUT<Short> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Num1.value <= Num2.value;
    }
}
