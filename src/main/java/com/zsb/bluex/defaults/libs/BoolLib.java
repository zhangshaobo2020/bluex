package com.zsb.bluex.defaults.libs;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;

@BluexFunctionLib(category = "布尔")
public class BoolLib {

    @BluexFunction(displayName = "布尔与 (AND)", executable = false)
    public static void And(
            INPUT<Boolean> A,
            INPUT<Boolean> B,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = A.value && B.value;
    }

    @BluexFunction(displayName = "布尔或 (OR)", executable = false)
    public static void Or(
            INPUT<Boolean> A,
            INPUT<Boolean> B,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = A.value || B.value;
    }

    @BluexFunction(displayName = "布尔非 (NOT)", executable = false)
    public static void Not(
            INPUT<Boolean> A,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = !A.value;
    }

    @BluexFunction(displayName = "布尔异或 (XOR)", executable = false)
    public static void Xor(
            INPUT<Boolean> A,
            INPUT<Boolean> B,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = A.value ^ B.value;
    }

    @BluexFunction(displayName = "布尔相等 (Equals)", executable = false)
    public static void Equals(
            INPUT<Boolean> A,
            INPUT<Boolean> B,
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = A.value.equals(B.value);
    }

    // 布尔转换相关

    @BluexFunction(displayName = "Bool转Int", executable = false)
    public static void BoolToInteger(
            INPUT<Boolean> In,
            OUTPUT<Integer> Out
    ) {
        Out.value = In.value ? 1 : 0;
    }

    @BluexFunction(displayName = "Bool转Byte", executable = false)
    public static void BoolToByte(
            INPUT<Boolean> In,
            OUTPUT<Byte> Out
    ) {
        Out.value = In.value ? (byte) 1 : (byte) 0;
    }

    @BluexFunction(displayName = "Bool转Short", executable = false)
    public static void BoolToShort(
            INPUT<Boolean> In,
            OUTPUT<Short> Out
    ) {
        Out.value = In.value ? (short) 1 : (short) 0;
    }

    @BluexFunction(displayName = "Bool转Long", executable = false)
    public static void BoolToLong(
            INPUT<Boolean> In,
            OUTPUT<Long> Out
    ) {
        Out.value = In.value ? 1L : 0L;
    }

    @BluexFunction(displayName = "Bool转Float", executable = false)
    public static void BoolToFloat(
            INPUT<Boolean> In,
            OUTPUT<Float> Out
    ) {
        Out.value = In.value ? 1.0f : 0.0f;
    }

    @BluexFunction(displayName = "Bool转Double", executable = false)
    public static void BoolToDouble(
            INPUT<Boolean> In,
            OUTPUT<Double> Out
    ) {
        Out.value = In.value ? 1.0 : 0.0;
    }

    @BluexFunction(displayName = "Bool转Char", executable = false)
    public static void BoolToChar(
            INPUT<Boolean> In,
            OUTPUT<Character> Out
    ) {
        Out.value = In.value ? '1' : '0';
    }

    @BluexFunction(displayName = "Bool转字符串", executable = false)
    public static void BoolToString(
            INPUT<Boolean> In,
            OUTPUT<String> Out
    ) {
        Out.value = String.valueOf(In.value);
    }

    // 反向转换，字符串转Bool（常见用法）

    @BluexFunction(displayName = "字符串转Bool", executable = false)
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
