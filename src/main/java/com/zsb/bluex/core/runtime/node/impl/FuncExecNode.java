package com.zsb.bluex.core.runtime.node.impl;

import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.core.runtime.ExecTask;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.ExecNode;
import com.zsb.bluex.core.runtime.param.ParamSource;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

public class FuncExecNode extends ExecNode {
    public final Method method;
    public final Map<String, ParamSource<?>> inputParams = new LinkedHashMap<>();
    public String nextExec;

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
    public void execute(ExecutionContext ctx) throws Exception {
        ExecutionContext.prepareArgs(ctx, inputParams, outputs, method);
        if (nextExec != null) {
            ctx.schedule(new ExecTask(nextExec, null));
        }
    }
}
