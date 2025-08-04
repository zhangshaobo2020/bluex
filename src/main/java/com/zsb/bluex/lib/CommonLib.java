package com.zsb.bluex.lib;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;

@BluexFunctionLib
public class CommonLib {

    @BluexFunction(description = "打印到控制台")
    public static void Print(
            INPUT<Object> Obj
    ) {
        System.out.println(Obj.value);
    }
}
