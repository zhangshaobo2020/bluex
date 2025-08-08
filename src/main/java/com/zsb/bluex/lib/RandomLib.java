package com.zsb.bluex.lib;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;

import java.util.Random;

@BluexFunctionLib
public class RandomLib {

    @BluexFunction(displayName = "随机整数", pure = true)
    public static void RandomInteger(
            INPUT<Integer> Min,
            INPUT<Integer> Max,
            OUTPUT<Integer> Ret
    ) {
        if (Min.value > Max.value) {
            throw new IllegalArgumentException("Min需要小于等于Max");
        }
        Ret.value = new Random().nextInt((Max.value - Min.value) + 1) + Min.value;
    }
}
