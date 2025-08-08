package com.zsb.bluex.lib;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;

@BluexFunctionLib(category = "Math|Float")
public class FloatLib {

    @BluexFunction(displayName = "Float加法", executable = false)
    public static void AddFloat(
            INPUT<Float> Num1,
            INPUT<Float> Num2,
            OUTPUT<Float> Ret
    ) {
        Ret.value = Num1.value + Num2.value;
    }

    @BluexFunction(displayName = "Float减法", executable = false)
    public static void SubtractFloat(
            INPUT<Float> Num1,
            INPUT<Float> Num2,
            OUTPUT<Float> Ret
    ) {
        Ret.value = Num1.value - Num2.value;
    }

    @BluexFunction(displayName = "Float乘法", executable = false)
    public static void MultiplyFloat(
            INPUT<Float> Num1,
            INPUT<Float> Num2,
            OUTPUT<Float> Ret
    ) {
        Ret.value = Num1.value * Num2.value;
    }

    @BluexFunction(displayName = "Float除法", executable = false)
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

    @BluexFunction(displayName = "Float取余", executable = false)
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

    @BluexFunction(displayName = "Float绝对值", executable = false)
    public static void AbsFloat(
            INPUT<Float> Num,
            OUTPUT<Float> Ret
    ) {
        Ret.value = Math.abs(Num.value);
    }

    @BluexFunction(displayName = "Float最大值", executable = false)
    public static void MaxFloat(
            INPUT<Float> Num1,
            INPUT<Float> Num2,
            OUTPUT<Float> Max
    ) {
        Max.value = Math.max(Num1.value, Num2.value);
    }

    @BluexFunction(displayName = "Float最小值", executable = false)
    public static void MinFloat(
            INPUT<Float> Num1,
            INPUT<Float> Num2,
            OUTPUT<Float> Min
    ) {
        Min.value = Math.min(Num1.value, Num2.value);
    }

    @BluexFunction(displayName = "Float幂运算", executable = false)
    public static void PowFloat(
            INPUT<Float> Base,
            INPUT<Float> Exp,
            OUTPUT<Float> Ret
    ) {
        Ret.value = (float) Math.pow(Base.value, Exp.value);
    }

    @BluexFunction(displayName = "Float平方根", executable = false)
    public static void SqrtFloat(
            INPUT<Float> Num,
            OUTPUT<Float> Ret
    ) {
        if (Num.value < 0.0f) {
            throw new ArithmeticException("Negative number cannot have a real square root");
        }
        Ret.value = (float) Math.sqrt(Num.value);
    }

    @BluexFunction(displayName = "Float取负", executable = false)
    public static void NegateFloat(
            INPUT<Float> Num,
            OUTPUT<Float> Ret
    ) {
        Ret.value = -Num.value;
    }

    // 类型转换

    @BluexFunction(displayName = "Float转Int", executable = false)
    public static void FloatToInteger(
            INPUT<Float> In,
            OUTPUT<Integer> Out
    ) {
        Out.value = In.value.intValue();
    }

    @BluexFunction(displayName = "Float转Double", executable = false)
    public static void FloatToDouble(
            INPUT<Float> In,
            OUTPUT<Double> Out
    ) {
        Out.value = In.value.doubleValue();
    }

    @BluexFunction(displayName = "Float转Long", executable = false)
    public static void FloatToLong(
            INPUT<Float> In,
            OUTPUT<Long> Out
    ) {
        Out.value = In.value.longValue();
    }

    @BluexFunction(displayName = "Float转Short", executable = false)
    public static void FloatToShort(
            INPUT<Float> In,
            OUTPUT<Short> Out
    ) {
        Out.value = In.value.shortValue();
    }

    @BluexFunction(displayName = "Float转Byte", executable = false)
    public static void FloatToByte(
            INPUT<Float> In,
            OUTPUT<Byte> Out
    ) {
        Out.value = In.value.byteValue();
    }

    @BluexFunction(displayName = "Float转Bool", executable = false)
    public static void FloatToBoolean(
            INPUT<Float> In,
            OUTPUT<Boolean> Out
    ) {
        Out.value = In.value != 0.0f;
    }

    @BluexFunction(displayName = "Float转Char", executable = false)
    public static void FloatToChar(
            INPUT<Float> In,
            OUTPUT<Character> Out
    ) {
        Out.value = (char) In.value.intValue();
    }

    @BluexFunction(displayName = "Float转String", executable = false)
    public static void FloatToString(
            INPUT<Float> In,
            OUTPUT<String> Out
    ) {
        Out.value = String.valueOf(In.value);
    }
}
