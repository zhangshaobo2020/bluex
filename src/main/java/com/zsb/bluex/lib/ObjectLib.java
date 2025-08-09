package com.zsb.bluex.lib;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;

@BluexFunctionLib(category = "Object")
public class ObjectLib {

    @BluexFunction(displayName = "打印到控制台")
    public static void Print(
            INPUT<Object> Obj
    ) {
        System.out.println(Obj.value);
    }

    @BluexFunction(displayName = "Object转String")
    public static void ObjectToString(
            INPUT<Object> Obj,
            OUTPUT<Object> Str
    ) {
        Str.value = Obj.value;
    }

    @BluexFunction(displayName = "判断是否为null", executable = false)
    public static void IsNull(
            INPUT<Object> Obj,
            OUTPUT<Boolean> Out
    ) {
        Out.value = (Obj.value == null);
    }

    @BluexFunction(displayName = "判断是否不为null", executable = false)
    public static void IsNotNull(
            INPUT<Object> Obj,
            OUTPUT<Boolean> Out
    ) {
        Out.value = (Obj.value != null);
    }

    @BluexFunction(displayName = "对象引用相等判断", executable = false)
    public static void ReferenceEquals(
            INPUT<Object> Obj1,
            INPUT<Object> Obj2,
            OUTPUT<Boolean> Out
    ) {
        Out.value = Obj1.value == Obj2.value;
    }

    @BluexFunction(displayName = "对象内容相等判断", executable = false)
    public static void Equals(
            INPUT<Object> Obj1,
            INPUT<Object> Obj2,
            OUTPUT<Boolean> Out
    ) {
        if (Obj1.value == null && Obj2.value == null) {
            Out.value = true;
        } else if (Obj1.value == null || Obj2.value == null) {
            Out.value = false;
        } else {
            Out.value = Obj1.value.equals(Obj2.value);
        }
    }

    @BluexFunction(displayName = "获取对象哈希码", executable = false)
    public static void HashCode(
            INPUT<Object> Obj,
            OUTPUT<Integer> Out
    ) {
        Out.value = (Obj.value == null) ? 0 : Obj.value.hashCode();
    }

    @BluexFunction(displayName = "获取对象类型名称", executable = false)
    public static void GetClassName(
            INPUT<Object> Obj,
            OUTPUT<String> Out
    ) {
        Out.value = (Obj.value == null) ? "null" : Obj.value.getClass().getName();
    }

    @BluexFunction(displayName = "对象转字符串", executable = false)
    public static void ToString(
            INPUT<Object> Obj,
            OUTPUT<String> Out
    ) {
        Out.value = (Obj.value == null) ? "null" : Obj.value.toString();
    }

    @BluexFunction(displayName = "浅拷贝", executable = false)
    public static void ShallowCopy(
            INPUT<Object> Obj,
            OUTPUT<Object> Out
    ) {
        if (Obj.value == null) {
            Out.value = null;
            return;
        }
        try {
            if (Obj.value instanceof Cloneable) {
                // 通过反射调用clone方法
                Out.value = Obj.value.getClass().getMethod("clone").invoke(Obj.value);
            } else {
                Out.value = Obj.value; // 不支持克隆则直接返回引用
            }
        } catch (Exception e) {
            Out.value = Obj.value; // 出错也直接返回引用
        }
    }
}
