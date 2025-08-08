package com.zsb.bluex.lib;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;

@BluexFunctionLib(category = "Math|Byte")
public class ByteLib {

    @BluexFunction(displayName = "Byte加法", executable = false)
    public static void AddByte(
            INPUT<Byte> Num1,
            INPUT<Byte> Num2,
            OUTPUT<Byte> Ret
    ) {
        Ret.value = (byte) (Num1.value + Num2.value);
    }

    @BluexFunction(displayName = "Byte减法", executable = false)
    public static void SubtractByte(
            INPUT<Byte> Num1,
            INPUT<Byte> Num2,
            OUTPUT<Byte> Ret
    ) {
        Ret.value = (byte) (Num1.value - Num2.value);
    }

    @BluexFunction(displayName = "Byte乘法", executable = false)
    public static void MultiplyByte(
            INPUT<Byte> Num1,
            INPUT<Byte> Num2,
            OUTPUT<Byte> Ret
    ) {
        Ret.value = (byte) (Num1.value * Num2.value);
    }

    @BluexFunction(displayName = "Byte除法", executable = false)
    public static void DivideByte(
            INPUT<Byte> Num1,
            INPUT<Byte> Num2,
            OUTPUT<Byte> Ret
    ) {
        if (Num2.value == 0) {
            throw new ArithmeticException("Division by zero");
        }
        Ret.value = (byte) (Num1.value / Num2.value);
    }

    @BluexFunction(displayName = "Byte取余", executable = false)
    public static void ModuloByte(
            INPUT<Byte> Num1,
            INPUT<Byte> Num2,
            OUTPUT<Byte> Ret
    ) {
        if (Num2.value == 0) {
            throw new ArithmeticException("Division by zero");
        }
        Ret.value = (byte) (Num1.value % Num2.value);
    }

    @BluexFunction(displayName = "Byte绝对值", executable = false)
    public static void AbsByte(
            INPUT<Byte> Num,
            OUTPUT<Byte> Ret
    ) {
        Ret.value = (byte) Math.abs(Num.value);
    }

    @BluexFunction(displayName = "Byte最大值", executable = false)
    public static void MaxByte(
            INPUT<Byte> Num1,
            INPUT<Byte> Num2,
            OUTPUT<Byte> Max
    ) {
        Max.value = (byte) Math.max(Num1.value, Num2.value);
    }

    @BluexFunction(displayName = "Byte最小值", executable = false)
    public static void MinByte(
            INPUT<Byte> Num1,
            INPUT<Byte> Num2,
            OUTPUT<Byte> Min
    ) {
        Min.value = (byte) Math.min(Num1.value, Num2.value);
    }

    @BluexFunction(displayName = "Byte幂运算", executable = false)
    public static void PowByte(
            INPUT<Byte> Base,
            INPUT<Byte> Exp,
            OUTPUT<Byte> Ret
    ) {
        Ret.value = (byte) Math.pow(Base.value, Exp.value);
    }

    @BluexFunction(displayName = "Byte平方根", executable = false)
    public static void SqrtByte(
            INPUT<Byte> Num,
            OUTPUT<Byte> Ret
    ) {
        if (Num.value < 0) {
            throw new ArithmeticException("Negative number cannot have an integer square root");
        }
        Ret.value = (byte) Math.sqrt(Num.value);
    }

    @BluexFunction(displayName = "Byte取负", executable = false)
    public static void NegateByte(
            INPUT<Byte> Num,
            OUTPUT<Byte> Ret
    ) {
        Ret.value = (byte) -Num.value;
    }

    // 类型转换

    @BluexFunction(displayName = "Byte转Int", executable = false)
    public static void ByteToInteger(
            INPUT<Byte> In,
            OUTPUT<Integer> Out
    ) {
        Out.value = In.value.intValue();
    }

    @BluexFunction(displayName = "Byte转Float", executable = false)
    public static void ByteToFloat(
            INPUT<Byte> In,
            OUTPUT<Float> Out
    ) {
        Out.value = In.value.floatValue();
    }

    @BluexFunction(displayName = "Byte转Double", executable = false)
    public static void ByteToDouble(
            INPUT<Byte> In,
            OUTPUT<Double> Out
    ) {
        Out.value = In.value.doubleValue();
    }

    @BluexFunction(displayName = "Byte转Long", executable = false)
    public static void ByteToLong(
            INPUT<Byte> In,
            OUTPUT<Long> Out
    ) {
        Out.value = In.value.longValue();
    }

    @BluexFunction(displayName = "Byte转Short", executable = false)
    public static void ByteToShort(
            INPUT<Byte> In,
            OUTPUT<Short> Out
    ) {
        Out.value = In.value.shortValue();
    }

    @BluexFunction(displayName = "Byte转Bool", executable = false)
    public static void ByteToBoolean(
            INPUT<Byte> In,
            OUTPUT<Boolean> Out
    ) {
        Out.value = In.value != 0;
    }

    @BluexFunction(displayName = "Byte转Char", executable = false)
    public static void ByteToChar(
            INPUT<Byte> In,
            OUTPUT<Character> Out
    ) {
        Out.value = (char) In.value.intValue();
    }

    @BluexFunction(displayName = "Byte转String", executable = false)
    public static void ByteToString(
            INPUT<Byte> In,
            OUTPUT<String> Out
    ) {
        Out.value = String.valueOf(In.value);
    }
}
