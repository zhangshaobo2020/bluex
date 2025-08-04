package com.zsb.bluex.core.runtime.node.impl;

import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.PureNode;
import com.zsb.bluex.core.runtime.param.ParamSource;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class FuncPureNode extends PureNode {
    private final Method method;
    private final Map<String, ParamSource<?>> inputParams = new LinkedHashMap<>();
    private final Map<String, OUTPUT<?>> outputs = new LinkedHashMap<>();

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
    public Object evaluate(String outputName, ExecutionContext ctx) {
        try {
            ExecutionContext.prepareArgs(ctx, inputParams, outputs, method);
            return outputs.get(outputName).value;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
