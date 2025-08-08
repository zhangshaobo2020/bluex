package com.zsb.bluex.lib;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;

@BluexFunctionLib
public class MathLib {

    @BluexFunction(displayName = "整数加法")
    public static void AddInteger(
            INPUT<Integer> Num1,
            INPUT<Integer> Num2,
            OUTPUT<Integer> Sum
    ) {
        Sum.value = Num1.value + Num2.value;
    }
}
