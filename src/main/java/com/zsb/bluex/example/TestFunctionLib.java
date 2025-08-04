package com.zsb.bluex.example;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;

import java.util.List;

@BluexFunctionLib
public class TestFunctionLib {

    @BluexFunction()
    public static void MathCalculate(
            INPUT<Integer> Num,
            INPUT<List<Integer>> NumList,
            OUTPUT<Integer> Ret
    ) {
        Integer Sum = 0;
        Sum += Num.value;
        for (Integer integer : NumList.value) {
            Sum += integer;
        }
        Ret.value = Sum;
    }
}
