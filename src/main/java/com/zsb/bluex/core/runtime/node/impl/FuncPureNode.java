package com.zsb.bluex.core.runtime.node.impl;

import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.PureNode;

import java.lang.reflect.Method;

public class FuncPureNode extends PureNode {
    public Method method;

    public FuncPureNode(String id, Method method) {
        super(id, "FuncPureNode" + "->" + method.getName());
        this.method = method;
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
