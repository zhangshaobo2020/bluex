package com.zsb.bluex.lib;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;

import java.util.Random;

@BluexFunctionLib(category = "Random")
public class RandomLib {

    private static final Random random = new Random();

    @BluexFunction(displayName = "随机Int [0,1)", executable = false)
    public static void RandomIntRange(
            OUTPUT<Integer> Ret
    ) {
        Ret.value = random.nextInt();
    }

    @BluexFunction(displayName = "随机Float [0,1)", executable = false)
    public static void RandomFloat(
            OUTPUT<Float> Ret
    ) {
        Ret.value = random.nextFloat();
    }

    @BluexFunction(displayName = "随机Double [0,1)", executable = false)
    public static void RandomDouble(
            OUTPUT<Double> Ret
    ) {
        Ret.value = random.nextDouble();
    }

    @BluexFunction(displayName = "随机Bool", executable = false)
    public static void RandomBoolean(
            OUTPUT<Boolean> Ret
    ) {
        Ret.value = random.nextBoolean();
    }

    @BluexFunction(displayName = "随机Int [Min,Max)", executable = false)
    public static void RandomIntRange(
            INPUT<Integer> Min,
            INPUT<Integer> Max,
            OUTPUT<Integer> Ret
    ) {
        if (Min.value > Max.value) {
            throw new IllegalArgumentException("Min需要小于等于Max");
        }
        Ret.value = Min.value + random.nextInt(Max.value - Min.value);
    }

    @BluexFunction(displayName = "随机Float [Min, Max)", executable = false)
    public static void RandomFloatRange(
            INPUT<Float> Min,
            INPUT<Float> Max,
            OUTPUT<Float> Ret
    ) {
        if (Min.value > Max.value) {
            throw new IllegalArgumentException("Min需要小于等于Max");
        }
        Ret.value = Min.value + random.nextFloat() * (Max.value - Min.value);
    }

    @BluexFunction(displayName = "随机Double [Min, Max)", executable = false)
    public static void RandomDoubleRange(
            INPUT<Double> Min,
            INPUT<Double> Max,
            OUTPUT<Double> Ret
    ) {
        if (Min.value > Max.value) {
            throw new IllegalArgumentException("Min需要小于等于Max");
        }
        Ret.value = Min.value + random.nextDouble() * (Max.value - Min.value);
    }
}
