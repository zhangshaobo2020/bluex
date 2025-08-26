package com.zsb.bluex.defaults.libs;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;

@BluexFunctionLib(category = "Java基本类型|Float")
public class SysLib_Float {

    @BluexFunction(category = "运算|Float相加", executable = false)
    public static void AddFloat(
            INPUT<Float> Num1,
            INPUT<Float> Num2,
            OUTPUT<Float> Ret
    ) {
        Ret.value = Num1.value + Num2.value;
    }

    @BluexFunction(category = "运算|Float相减", executable = false)
    public static void SubtractFloat(
            INPUT<Float> Num1,
            INPUT<Float> Num2,
            OUTPUT<Float> Ret
    ) {
        Ret.value = Num1.value - Num2.value;
    }

    @BluexFunction(category = "运算|Float相乘", executable = false)
    public static void MultiplyFloat(
            INPUT<Float> Num1,
            INPUT<Float> Num2,
            OUTPUT<Float> Ret
    ) {
        Ret.value = Num1.value * Num2.value;
    }

    @BluexFunction(category = "运算|Float相除", executable = false)
    public static void DivideFloat(
            INPUT<Float> Num1,
            INPUT<Float> Num2,
            OUTPUT<Float> Ret
    ) {
        if (Num2.value == 0.0f) {
            throw new ArithmeticException("Division by zero");
        }
        Ret.value = Num1.value / Num2.value;
    }

    @BluexFunction(category = "运算|Float取余", executable = false)
    public static void ModuloFloat(
            INPUT<Float> Num1,
            INPUT<Float> Num2,
            OUTPUT<Float> Ret
    ) {
        if (Num2.value == 0.0f) {
            throw new ArithmeticException("Division by zero");
        }
        Ret.value = Num1.value % Num2.value;
    }

    @BluexFunction(category = "运算|Float绝对值", executable = false)
    public static void AbsFloat(
            INPUT<Float> Num,
            OUTPUT<Float> Ret
    ) {
        Ret.value = Math.abs(Num.value);
    }

    @BluexFunction(category = "运算|Float最大值", executable = false)
    public static void MaxFloat(
            INPUT<Float> Num1,
            INPUT<Float> Num2,
            OUTPUT<Float> Max
    ) {
        Max.value = Math.max(Num1.value, Num2.value);
    }

    @BluexFunction(category = "运算|Float最小值", executable = false)
    public static void MinFloat(
            INPUT<Float> Num1,
            INPUT<Float> Num2,
            OUTPUT<Float> Min
    ) {
        Min.value = Math.min(Num1.value, Num2.value);
    }

    @BluexFunction(category = "运算|Float幂运算", executable = false)
    public static void PowFloat(
            INPUT<Float> Base,
            INPUT<Float> Exp,
            OUTPUT<Float> Ret
    ) {
        Ret.value = (float) Math.pow(Base.value, Exp.value);
    }

    @BluexFunction(category = "运算|Float平方根", executable = false)
    public static void SqrtFloat(
            INPUT<Float> Num,
            OUTPUT<Float> Ret
    ) {
        if (Num.value < 0.0f) {
            throw new ArithmeticException("Negative number cannot have a real square root");
        }
        Ret.value = (float) Math.sqrt(Num.value);
    }

    @BluexFunction(category = "运算|Float取负", executable = false)
    public static void NegateFloat(
            INPUT<Float> Num,
            OUTPUT<Float> Ret
    ) {
        Ret.value = -Num.value;
    }

    // 类型转换

    @BluexFunction(category = "类型转换|Float转Int", executable = false)
    public static void FloatToInteger(
            INPUT<Float> In,
            OUTPUT<Integer> Out
    ) {
        Out.value = In.value.intValue();
    }

    @BluexFunction(category = "类型转换|Float转Double", executable = false)
    public static void FloatToDouble(
            INPUT<Float> In,
            OUTPUT<Double> Out
    ) {
        Out.value = In.value.doubleValue();
    }

    @BluexFunction(category = "类型转换|Float转Long", executable = false)
    public static void FloatToLong(
            INPUT<Float> In,
            OUTPUT<Long> Out
    ) {
        Out.value = In.value.longValue();
    }

    @BluexFunction(category = "类型转换|Float转Short", executable = false)
    public static void FloatToShort(
            INPUT<Float> In,
            OUTPUT<Short> Out
    ) {
        Out.value = In.value.shortValue();
    }

    @BluexFunction(category = "类型转换|Float转Byte", executable = false)
    public static void FloatToByte(
            INPUT<Float> In,
            OUTPUT<Byte> Out
    ) {
        Out.value = In.value.byteValue();
    }

    @BluexFunction(category = "类型转换|Float转Bool", executable = false)
    public static void FloatToBoolean(
            INPUT<Float> In,
            OUTPUT<Boolean> Out
    ) {
        Out.value = In.value != 0.0f;
    }

    @BluexFunction(category = "类型转换|Float转Char", executable = false)
    public static void FloatToChar(
            INPUT<Float> In,
            OUTPUT<Character> Out
    ) {
        Out.value = (char) In.value.intValue();
    }

    @BluexFunction(category = "类型转换|Float转String", executable = false)
    public static void FloatToString(
            INPUT<Float> In,
            OUTPUT<String> Out
    ) {
        Out.value = String.valueOf(In.value);
    }

    @BluexFunction(category = "比较|Float等于", executable = false)
    public static void Equal(
            INPUT<Float> Num1,
            INPUT<Float> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Num1.value.equals(Num2.value);
    }

    @BluexFunction(category = "比较|Float不等于", executable = false)
    public static void NotEqual(
            INPUT<Float> Num1,
            INPUT<Float> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = !Num1.value.equals(Num2.value);
    }

    @BluexFunction(category = "比较|Float大于", executable = false)
    public static void GreaterThan(
            INPUT<Float> Num1,
            INPUT<Float> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Num1.value > Num2.value;
    }

    @BluexFunction(category = "比较|Float大于等于", executable = false)
    public static void GreaterEqualThan(
            INPUT<Float> Num1,
            INPUT<Float> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Num1.value >= Num2.value;
    }

    @BluexFunction(category = "比较|Float小于", executable = false)
    public static void LessThan(
            INPUT<Float> Num1,
            INPUT<Float> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Num1.value < Num2.value;
    }

    @BluexFunction(category = "比较|Float小于等于", executable = false)
    public static void LessEqualThan(
            INPUT<Float> Num1,
            INPUT<Float> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Num1.value <= Num2.value;
    }
}
