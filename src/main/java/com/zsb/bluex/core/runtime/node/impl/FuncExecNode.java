package com.zsb.bluex.core.runtime.node.impl;

import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.ExecNode;
import com.zsb.bluex.core.runtime.param.ParamSource;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class FuncExecNode extends ExecNode {
    private final Method method;
    private final Map<String, ParamSource<?>> inputParams = new LinkedHashMap<>();
    private String nextExec;

    public FuncExecNode(String id, String name, Method method) {
        super(id, name);
        this.method = method;
    }

    public void setInputParam(String name, ParamSource<?> source) {
        inputParams.put(name, source);
    }

    public <T> void setOutput(String name, OUTPUT<T> output) {
        outputs.put(name, output);
    }

    public Object getOutputParamValue(String outputName) {
        OUTPUT<?> output = outputs.get(outputName);
        if (output == null) throw new RuntimeException("未找到" + outputName + "对应的OUTPUT值");
        return output.value;
    }

    @Override
    public String execute(ExecutionContext ctx) {
        try {
            ExecutionContext.prepareArgs(ctx, inputParams, outputs, method);
        } catch (Exception e) {
            throw new RuntimeException("Error executing " + getName(), e);
        }
        return nextExec;
    }
}
