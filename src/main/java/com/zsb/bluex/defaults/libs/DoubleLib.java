package com.zsb.bluex.defaults.libs;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;

@BluexFunctionLib(category = "数字|Double")
public class DoubleLib {

    @BluexFunction(displayName = "Double加法", executable = false)
    public static void AddDouble(
            INPUT<Double> Num1,
            INPUT<Double> Num2,
            OUTPUT<Double> Ret
    ) {
        Ret.value = Num1.value + Num2.value;
    }

    @BluexFunction(displayName = "Double减法", executable = false)
    public static void SubtractDouble(
            INPUT<Double> Num1,
            INPUT<Double> Num2,
            OUTPUT<Double> Ret
    ) {
        Ret.value = Num1.value - Num2.value;
    }

    @BluexFunction(displayName = "Double乘法", executable = false)
    public static void MultiplyDouble(
            INPUT<Double> Num1,
            INPUT<Double> Num2,
            OUTPUT<Double> Ret
    ) {
        Ret.value = Num1.value * Num2.value;
    }

    @BluexFunction(displayName = "Double除法", executable = false)
    public static void DivideDouble(
            INPUT<Double> Num1,
            INPUT<Double> Num2,
            OUTPUT<Double> Ret
    ) {
        if (Num2.value == 0.0) {
            throw new ArithmeticException("Division by zero");
        }
        Ret.value = Num1.value / Num2.value;
    }

    @BluexFunction(displayName = "Double取余", executable = false)
    public static void ModuloDouble(
            INPUT<Double> Num1,
            INPUT<Double> Num2,
            OUTPUT<Double> Ret
    ) {
        if (Num2.value == 0.0) {
            throw new ArithmeticException("Division by zero");
        }
        Ret.value = Num1.value % Num2.value;
    }

    @BluexFunction(displayName = "Double绝对值", executable = false)
    public static void AbsDouble(
            INPUT<Double> Num,
            OUTPUT<Double> Ret
    ) {
        Ret.value = Math.abs(Num.value);
    }

    @BluexFunction(displayName = "Double最大值", executable = false)
    public static void MaxDouble(
            INPUT<Double> Num1,
            INPUT<Double> Num2,
            OUTPUT<Double> Max
    ) {
        Max.value = Math.max(Num1.value, Num2.value);
    }

    @BluexFunction(displayName = "Double最小值", executable = false)
    public static void MinDouble(
            INPUT<Double> Num1,
            INPUT<Double> Num2,
            OUTPUT<Double> Min
    ) {
        Min.value = Math.min(Num1.value, Num2.value);
    }

    @BluexFunction(displayName = "Double幂运算", executable = false)
    public static void PowDouble(
            INPUT<Double> Base,
            INPUT<Double> Exp,
            OUTPUT<Double> Ret
    ) {
        Ret.value = Math.pow(Base.value, Exp.value);
    }

    @BluexFunction(displayName = "Double平方根", executable = false)
    public static void SqrtDouble(
            INPUT<Double> Num,
            OUTPUT<Double> Ret
    ) {
        if (Num.value < 0.0) {
            throw new ArithmeticException("Negative number cannot have a real square root");
        }
        Ret.value = Math.sqrt(Num.value);
    }

    @BluexFunction(displayName = "Double取负", executable = false)
    public static void NegateDouble(
            INPUT<Double> Num,
            OUTPUT<Double> Ret
    ) {
        Ret.value = -Num.value;
    }

    // 类型转换

    @BluexFunction(displayName = "Double转Int", executable = false)
    public static void DoubleToInteger(
            INPUT<Double> In,
            OUTPUT<Integer> Out
    ) {
        Out.value = In.value.intValue();
    }

    @BluexFunction(displayName = "Double转Float", executable = false)
    public static void DoubleToFloat(
            INPUT<Double> In,
            OUTPUT<Float> Out
    ) {
        Out.value = In.value.floatValue();
    }

    @BluexFunction(displayName = "Double转Long", executable = false)
    public static void DoubleToLong(
            INPUT<Double> In,
            OUTPUT<Long> Out
    ) {
        Out.value = In.value.longValue();
    }

    @BluexFunction(displayName = "Double转Short", executable = false)
    public static void DoubleToShort(
            INPUT<Double> In,
            OUTPUT<Short> Out
    ) {
        Out.value = In.value.shortValue();
    }

    @BluexFunction(displayName = "Double转Byte", executable = false)
    public static void DoubleToByte(
            INPUT<Double> In,
            OUTPUT<Byte> Out
    ) {
        Out.value = In.value.byteValue();
    }

    @BluexFunction(displayName = "Double转Bool", executable = false)
    public static void DoubleToBoolean(
            INPUT<Double> In,
            OUTPUT<Boolean> Out
    ) {
        Out.value = In.value != 0.0;
    }

    @BluexFunction(displayName = "Double转Char", executable = false)
    public static void DoubleToChar(
            INPUT<Double> In,
            OUTPUT<Character> Out
    ) {
        Out.value = (char) In.value.intValue();
    }

    @BluexFunction(displayName = "Double转String", executable = false)
    public static void DoubleToString(
            INPUT<Double> In,
            OUTPUT<String> Out
    ) {
        Out.value = String.valueOf(In.value);
    }

    @BluexFunction(displayName = "Double等于", executable = false)
    public static void Equal(
            INPUT<Double> Num1,
            INPUT<Double> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Num1.value.equals(Num2.value);
    }

    @BluexFunction(displayName = "Double不等于", executable = false)
    public static void NotEqual(
            INPUT<Double> Num1,
            INPUT<Double> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = !Num1.value.equals(Num2.value);
    }

    @BluexFunction(displayName = "Double大于", executable = false)
    public static void GreaterThan(
            INPUT<Double> Num1,
            INPUT<Double> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Num1.value > Num2.value;
    }

    @BluexFunction(displayName = "Double大于等于", executable = false)
    public static void GreaterEqualThan(
            INPUT<Double> Num1,
            INPUT<Double> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Num1.value >= Num2.value;
    }

    @BluexFunction(displayName = "Double小于", executable = false)
    public static void LessThan(
            INPUT<Double> Num1,
            INPUT<Double> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Num1.value < Num2.value;
    }

    @BluexFunction(displayName = "Double小于等于", executable = false)
    public static void LessEqualThan(
            INPUT<Double> Num1,
            INPUT<Double> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Num1.value <= Num2.value;
    }
}
