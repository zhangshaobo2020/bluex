package com.zsb.bluex.core.runtime.node.impl;

import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.PureNode;
import com.zsb.bluex.core.runtime.param.ParamSource;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

public class FuncPureNode extends PureNode {
    public final Method method;
    public final Map<String, ParamSource<?>> inputParams = new LinkedHashMap<>();
    public final Map<String, OUTPUT<?>> outputs = new LinkedHashMap<>();

    public FuncPureNode(String id, String name, Method method) {
        super(id, name);
        this.method = method;
    }

    public void setInputParam(String name, ParamSource<?> source) {
        inputParams.put(name, source);
    }

    public <T> void setOutputParam(String name, OUTPUT<T> output) {
        outputs.put(name, output);
    }

    @Override
    public Object evaluate(String outputName, ExecutionContext ctx) throws Exception {
        ExecutionContext.prepareArgs(ctx, inputParams, outputs, method);
        OUTPUT<?> output = outputs.get(outputName);
        if (output == null) {
            String msg = String.format("未在%s节点中找到%s引脚", name, outputName);
            throw new RuntimeException(msg);
        }
        return output.value;
    }
}
