package com.zsb.bluex.defaults.libs;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;

@BluexFunctionLib(category = "Java基本类型|Bool")
public class SysLib_Bool {

    @BluexFunction(category = "运算|Bool与 (AND)", executable = false)
    public static void And(
            INPUT<Boolean> A,
            INPUT<Boolean> B,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = A.value && B.value;
    }

    @BluexFunction(category = "运算|Bool或 (OR)", executable = false)
    public static void Or(
            INPUT<Boolean> A,
            INPUT<Boolean> B,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = A.value || B.value;
    }

    @BluexFunction(category = "运算|Bool非 (NOT)", executable = false)
    public static void Not(
            INPUT<Boolean> A,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = !A.value;
    }

    @BluexFunction(category = "运算|Bool异或 (XOR)", executable = false)
    public static void Xor(
            INPUT<Boolean> A,
            INPUT<Boolean> B,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = A.value ^ B.value;
    }

    @BluexFunction(category = "比较|Bool相等 (Equals)", executable = false)
    public static void Equals(
            INPUT<Boolean> A,
            INPUT<Boolean> B,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = A.value.equals(B.value);
    }

    @BluexFunction(category = "类型转换|Bool转Int", executable = false)
    public static void BoolToInteger(
            INPUT<Boolean> In,
            OUTPUT<Integer> Out
    ) {
        Out.value = In.value ? 1 : 0;
    }

    @BluexFunction(category = "类型转换|Bool转Byte", executable = false)
    public static void BoolToByte(
            INPUT<Boolean> In,
            OUTPUT<Byte> Out
    ) {
        Out.value = In.value ? (byte) 1 : (byte) 0;
    }

    @BluexFunction(category = "类型转换|Bool转Short", executable = false)
    public static void BoolToShort(
            INPUT<Boolean> In,
            OUTPUT<Short> Out
    ) {
        Out.value = In.value ? (short) 1 : (short) 0;
    }

    @BluexFunction(category = "类型转换|Bool转Long", executable = false)
    public static void BoolToLong(
            INPUT<Boolean> In,
            OUTPUT<Long> Out
    ) {
        Out.value = In.value ? 1L : 0L;
    }

    @BluexFunction(category = "类型转换|Bool转Float", executable = false)
    public static void BoolToFloat(
            INPUT<Boolean> In,
            OUTPUT<Float> Out
    ) {
        Out.value = In.value ? 1.0f : 0.0f;
    }

    @BluexFunction(category = "类型转换|Bool转Double", executable = false)
    public static void BoolToDouble(
            INPUT<Boolean> In,
            OUTPUT<Double> Out
    ) {
        Out.value = In.value ? 1.0 : 0.0;
    }

    @BluexFunction(category = "类型转换|Bool转Char", executable = false)
    public static void BoolToChar(
            INPUT<Boolean> In,
            OUTPUT<Character> Out
    ) {
        Out.value = In.value ? '1' : '0';
    }

    @BluexFunction(category = "类型转换|Bool转String", executable = false)
    public static void BoolToString(
            INPUT<Boolean> In,
            OUTPUT<String> Out
    ) {
        Out.value = String.valueOf(In.value);
    }

    @BluexFunction(category = "类型转换|String转Bool", executable = false)
    public static void StringToBool(
            INPUT<String> In,
            OUTPUT<Boolean> Out
    ) {
        if (In.value == null) {
            Out.value = false;
            return;
        }
        String s = In.value.trim().toLowerCase();
        Out.value = s.equals("true") || s.equals("1") || s.equals("yes") || s.equals("on");
    }
}
