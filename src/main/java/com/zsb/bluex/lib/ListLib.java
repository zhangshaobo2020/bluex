package com.zsb.bluex.lib;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;

import java.util.ArrayList;
import java.util.List;

@BluexFunctionLib(category = "Collection|List")
public class ListLib {

    @BluexFunction(displayName = "创建空List", executable = false)
    public static void CreateEmptyList(
            OUTPUT<List<Object>> Out
    ) {
        Out.value = new ArrayList<>();
    }

    @BluexFunction(displayName = "添加元素到List")
    public static void AddElement(
            INPUT<List<Object>> List,
            INPUT<Object> Element,
            OUTPUT<List<Object>> Out
    ) {
        List.value.add(Element.value);
        Out.value = List.value;
    }

    @BluexFunction(displayName = "从List移除元素")
    public static void RemoveElement(
            INPUT<List<Object>> List,
            INPUT<Object> Element,
            OUTPUT<List<Object>> Out
    ) {
        List.value.remove(Element.value);
        Out.value = List.value;
    }

    @BluexFunction(displayName = "获取List长度", executable = false)
    public static void GetCount(
            INPUT<List<Object>> List,
            OUTPUT<Integer> Count
    ) {
        if (List.value == null) {
            Count.value = 0;
        } else {
            Count.value = List.value.size();
        }
    }

    @BluexFunction(displayName = "判断List是否包含元素", executable = false)
    public static void Contains(
            INPUT<List<Object>> List,
            INPUT<Object> Element,
            OUTPUT<Boolean> Result
    ) {
        if (List.value == null) {
            Result.value = false;
        } else {
            Result.value = List.value.contains(Element.value);
        }
    }

    @BluexFunction(displayName = "获取List指定索引的元素", executable = false)
    public static void GetElementAt(
            INPUT<List<Object>> List,
            INPUT<Integer> Index,
            OUTPUT<Object> Element
    ) {
        if (List.value == null || Index.value < 0 || Index.value >= List.value.size()) {
            Element.value = null;
        } else {
            Element.value = List.value.get(Index.value);
        }
    }

    @BluexFunction(displayName = "清空List")
    public static void Clear(
            INPUT<List<Object>> List
    ) {
        List.value = new ArrayList<>();
    }
}
