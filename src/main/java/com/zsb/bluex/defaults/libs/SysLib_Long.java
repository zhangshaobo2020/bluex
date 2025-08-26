package com.zsb.bluex.defaults.libs;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;

@BluexFunctionLib(category = "Java基本类型|Long")
public class SysLib_Long {

    @BluexFunction(category = "运算|Long相加", executable = false)
    public static void AddLong(
            INPUT<Long> Num1,
            INPUT<Long> Num2,
            OUTPUT<Long> Ret
    ) {
        Ret.value = Num1.value + Num2.value;
    }

    @BluexFunction(category = "运算|Long相减", executable = false)
    public static void SubtractLong(
            INPUT<Long> Num1,
            INPUT<Long> Num2,
            OUTPUT<Long> Ret
    ) {
        Ret.value = Num1.value - Num2.value;
    }

    @BluexFunction(category = "运算|Long相乘", executable = false)
    public static void MultiplyLong(
            INPUT<Long> Num1,
            INPUT<Long> Num2,
            OUTPUT<Long> Ret
    ) {
        Ret.value = Num1.value * Num2.value;
    }

    @BluexFunction(category = "运算|Long相除", executable = false)
    public static void DivideLong(
            INPUT<Long> Num1,
            INPUT<Long> Num2,
            OUTPUT<Long> Ret
    ) {
        if (Num2.value == 0L) {
            throw new ArithmeticException("Division by zero");
        }
        Ret.value = Num1.value / Num2.value;
    }

    @BluexFunction(category = "运算|Long取余", executable = false)
    public static void ModuloLong(
            INPUT<Long> Num1,
            INPUT<Long> Num2,
            OUTPUT<Long> Ret
    ) {
        if (Num2.value == 0L) {
            throw new ArithmeticException("Division by zero");
        }
        Ret.value = Num1.value % Num2.value;
    }

    @BluexFunction(category = "运算|Long绝对值", executable = false)
    public static void AbsLong(
            INPUT<Long> Num,
            OUTPUT<Long> Ret
    ) {
        Ret.value = Math.abs(Num.value);
    }

    @BluexFunction(category = "运算|Long最大值", executable = false)
    public static void MaxLong(
            INPUT<Long> Num1,
            INPUT<Long> Num2,
            OUTPUT<Long> Max
    ) {
        Max.value = Math.max(Num1.value, Num2.value);
    }

    @BluexFunction(category = "运算|Long最小值", executable = false)
    public static void MinLong(
            INPUT<Long> Num1,
            INPUT<Long> Num2,
            OUTPUT<Long> Min
    ) {
        Min.value = Math.min(Num1.value, Num2.value);
    }

    @BluexFunction(category = "运算|Long幂运算", executable = false)
    public static void PowLong(
            INPUT<Long> Base,
            INPUT<Long> Exp,
            OUTPUT<Long> Ret
    ) {
        Ret.value = (long) Math.pow(Base.value, Exp.value);
    }

    @BluexFunction(category = "运算|Long平方根", executable = false)
    public static void SqrtLong(
            INPUT<Long> Num,
            OUTPUT<Long> Ret
    ) {
        if (Num.value < 0L) {
            throw new ArithmeticException("Negative number cannot have an integer square root");
        }
        Ret.value = (long) Math.sqrt(Num.value);
    }

    @BluexFunction(category = "运算|Long取负", executable = false)
    public static void NegateLong(
            INPUT<Long> Num,
            OUTPUT<Long> Ret
    ) {
        Ret.value = -Num.value;
    }

    // 类型转换

    @BluexFunction(category = "类型转换|Long转Int", executable = false)
    public static void LongToInteger(
            INPUT<Long> In,
            OUTPUT<Integer> Out
    ) {
        Out.value = In.value.intValue();
    }

    @BluexFunction(category = "类型转换|Long转Float", executable = false)
    public static void LongToFloat(
            INPUT<Long> In,
            OUTPUT<Float> Out
    ) {
        Out.value = In.value.floatValue();
    }

    @BluexFunction(category = "类型转换|Long转Double", executable = false)
    public static void LongToDouble(
            INPUT<Long> In,
            OUTPUT<Double> Out
    ) {
        Out.value = In.value.doubleValue();
    }

    @BluexFunction(category = "类型转换|Long转Short", executable = false)
    public static void LongToShort(
            INPUT<Long> In,
            OUTPUT<Short> Out
    ) {
        Out.value = In.value.shortValue();
    }

    @BluexFunction(category = "类型转换|Long转Byte", executable = false)
    public static void LongToByte(
            INPUT<Long> In,
            OUTPUT<Byte> Out
    ) {
        Out.value = In.value.byteValue();
    }

    @BluexFunction(category = "类型转换|Long转Bool", executable = false)
    public static void LongToBoolean(
            INPUT<Long> In,
            OUTPUT<Boolean> Out
    ) {
        Out.value = In.value != 0L;
    }

    @BluexFunction(category = "类型转换|Long转Char", executable = false)
    public static void LongToChar(
            INPUT<Long> In,
            OUTPUT<Character> Out
    ) {
        Out.value = (char) In.value.intValue();
    }

    @BluexFunction(category = "类型转换|Long转String", executable = false)
    public static void LongToString(
            INPUT<Long> In,
            OUTPUT<String> Out
    ) {
        Out.value = String.valueOf(In.value);
    }

    @BluexFunction(category = "比较|Long等于", executable = false)
    public static void Equal(
            INPUT<Long> Num1,
            INPUT<Long> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Num1.value.equals(Num2.value);
    }

    @BluexFunction(category = "比较|Long不等于", executable = false)
    public static void NotEqual(
            INPUT<Long> Num1,
            INPUT<Long> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = !Num1.value.equals(Num2.value);
    }

    @BluexFunction(category = "比较|Long大于", executable = false)
    public static void GreaterThan(
            INPUT<Long> Num1,
            INPUT<Long> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Num1.value > Num2.value;
    }

    @BluexFunction(category = "比较|Long大于等于", executable = false)
    public static void GreaterEqualThan(
            INPUT<Long> Num1,
            INPUT<Long> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Num1.value >= Num2.value;
    }

    @BluexFunction(category = "比较|Long小于", executable = false)
    public static void LessThan(
            INPUT<Long> Num1,
            INPUT<Long> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Num1.value < Num2.value;
    }

    @BluexFunction(category = "比较|Long小于等于", executable = false)
    public static void LessEqualThan(
            INPUT<Long> Num1,
            INPUT<Long> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Num1.value <= Num2.value;
    }
}
