package com.zsb.bluex.defaults.libs;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;

@BluexFunctionLib(category = "数字|Int")
public class IntLib {

    @BluexFunction(displayName = "Int加法", executable = false)
    public static void AddInteger(
            INPUT<Integer> Num1,
            INPUT<Integer> Num2,
            OUTPUT<Integer> Ret
    ) {
        Ret.value = Num1.value + Num2.value;
    }

    @BluexFunction(displayName = "Int减法", executable = false)
    public static void SubtractInteger(
            INPUT<Integer> Num1,
            INPUT<Integer> Num2,
            OUTPUT<Integer> Ret
    ) {
        Ret.value = Num1.value - Num2.value;
    }

    @BluexFunction(displayName = "Int乘法", executable = false)
    public static void MultiplyInteger(
            INPUT<Integer> Num1,
            INPUT<Integer> Num2,
            OUTPUT<Integer> Ret
    ) {
        Ret.value = Num1.value * Num2.value;
    }

    @BluexFunction(displayName = "Int除法", executable = false)
    public static void DivideInteger(
            INPUT<Integer> Num1,
            INPUT<Integer> Num2,
            OUTPUT<Integer> Ret
    ) {
        if (Num2.value == 0) {
            throw new ArithmeticException("Division by zero");
        }
        Ret.value = Num1.value / Num2.value;
    }

    @BluexFunction(displayName = "Int取余", executable = false)
    public static void ModuloInteger(
            INPUT<Integer> Num1,
            INPUT<Integer> Num2,
            OUTPUT<Integer> Ret
    ) {
        if (Num2.value == 0) {
            throw new ArithmeticException("Division by zero");
        }
        Ret.value = Num1.value % Num2.value;
    }

    @BluexFunction(displayName = "Int绝对值", executable = false)
    public static void AbsInteger(
            INPUT<Integer> Num,
            OUTPUT<Integer> Ret
    ) {
        Ret.value = Math.abs(Num.value);
    }

    @BluexFunction(displayName = "Int最大值", executable = false)
    public static void MaxInteger(
            INPUT<Integer> Num1,
            INPUT<Integer> Num2,
            OUTPUT<Integer> Max
    ) {
        Max.value = Math.max(Num1.value, Num2.value);
    }

    @BluexFunction(displayName = "Int最小值", executable = false)
    public static void MinInteger(
            INPUT<Integer> Num1,
            INPUT<Integer> Num2,
            OUTPUT<Integer> Min
    ) {
        Min.value = Math.min(Num1.value, Num2.value);
    }

    @BluexFunction(displayName = "Int幂运算", executable = false)
    public static void PowInteger(
            INPUT<Integer> Base,
            INPUT<Integer> Exp,
            OUTPUT<Integer> Ret
    ) {
        Ret.value = (int) Math.pow(Base.value, Exp.value);
    }

    @BluexFunction(displayName = "Int平方根", executable = false)
    public static void SqrtInteger(
            INPUT<Integer> Num,
            OUTPUT<Integer> Ret
    ) {
        if (Num.value < 0) {
            throw new ArithmeticException("Negative number cannot have an integer square root");
        }
        Ret.value = (int) Math.sqrt(Num.value);
    }

    @BluexFunction(displayName = "Int取负", executable = false)
    public static void NegateInteger(
            INPUT<Integer> Num,
            OUTPUT<Integer> Ret
    ) {
        Ret.value = -Num.value;
    }

    @BluexFunction(displayName = "Int转Float", executable = false)
    public static void IntegerToFloat(
            INPUT<Integer> In,
            OUTPUT<Float> Out
    ) {
        Out.value = In.value.floatValue();
    }

    @BluexFunction(displayName = "Int转Double", executable = false)
    public static void IntegerToDouble(
            INPUT<Integer> In,
            OUTPUT<Double> Out
    ) {
        Out.value = In.value.doubleValue();
    }

    @BluexFunction(displayName = "Int转Long", executable = false)
    public static void IntegerToLong(
            INPUT<Integer> In,
            OUTPUT<Long> Out
    ) {
        Out.value = In.value.longValue();
    }

    @BluexFunction(displayName = "Int转Short", executable = false)
    public static void IntegerToShort(
            INPUT<Integer> In,
            OUTPUT<Short> Out
    ) {
        Out.value = In.value.shortValue();
    }

    @BluexFunction(displayName = "Int转Byte", executable = false)
    public static void IntegerToByte(
            INPUT<Integer> In,
            OUTPUT<Byte> Out
    ) {
        Out.value = In.value.byteValue();
    }

    @BluexFunction(displayName = "Int转Bool", executable = false)
    public static void IntegerToBoolean(
            INPUT<Integer> In,
            OUTPUT<Boolean> Out
    ) {
        Out.value = In.value != 0;
    }

    @BluexFunction(displayName = "Int转Char", executable = false)
    public static void IntegerToChar(
            INPUT<Integer> In,
            OUTPUT<Character> Out
    ) {
        Out.value = (char) In.value.intValue();
    }

    @BluexFunction(displayName = "Int转String", executable = false)
    public static void IntegerToString(
            INPUT<Integer> In,
            OUTPUT<String> Out
    ) {
        Out.value = String.valueOf(In.value);
    }

    @BluexFunction(displayName = "Int等于", executable = false)
    public static void Equal(
            INPUT<Integer> Num1,
            INPUT<Integer> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Num1.value.equals(Num2.value);
    }

    @BluexFunction(displayName = "Int不等于", executable = false)
    public static void NotEqual(
            INPUT<Integer> Num1,
            INPUT<Integer> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = !Num1.value.equals(Num2.value);
    }

    @BluexFunction(displayName = "Int大于", executable = false)
    public static void GreaterThan(
            INPUT<Integer> Num1,
            INPUT<Integer> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Num1.value > Num2.value;
    }

    @BluexFunction(displayName = "Int大于等于", executable = false)
    public static void GreaterEqualThan(
            INPUT<Integer> Num1,
            INPUT<Integer> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Num1.value >= Num2.value;
    }

    @BluexFunction(displayName = "Int小于", executable = false)
    public static void LessThan(
            INPUT<Integer> Num1,
            INPUT<Integer> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Num1.value < Num2.value;
    }

    @BluexFunction(displayName = "Int小于等于", executable = false)
    public static void LessEqualThan(
            INPUT<Integer> Num1,
            INPUT<Integer> Num2,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = Num1.value <= Num2.value;
    }
}
