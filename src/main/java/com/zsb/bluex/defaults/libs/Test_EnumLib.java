package com.zsb.bluex.defaults.libs;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.defaults.enums.DayEnum;

@BluexFunctionLib(category = "测试|枚举")
public class Test_EnumLib {

    @BluexFunction(displayName = "构造DayEnum", executable = false)
    public static void CreateDayEnum(
            INPUT<String> Str,
            OUTPUT<DayEnum> Enum
    ) {
        Enum.value = DayEnum.valueOf(Str.value);
    }

    @BluexFunction(displayName = "DayEnum字符串", executable = false)
    public static void DayEnumToString(
            INPUT<DayEnum> Enum,
            OUTPUT<String> Str
    ) {
        switch (Enum.value) {
            case MON:
                Str.value = "星期一";
                break;
            case TUE:
                Str.value = "星期二";
                break;
            case WED:
                Str.value = "星期三";
                break;
            case THU:
                Str.value = "星期四";
                break;
            case FRI:
                Str.value = "星期五";
                break;
            case SAT:
                Str.value = "星期六";
                break;
            case SUN:
                Str.value = "星期七";
                break;
            default:
                break;
        }
    }
}
