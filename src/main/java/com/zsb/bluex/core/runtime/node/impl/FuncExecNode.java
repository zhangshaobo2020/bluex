package com.zsb.bluex.core.runtime.node.impl;

import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.core.runtime.ExecTask;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.ExecNode;

import java.lang.reflect.Method;

public class FuncExecNode extends ExecNode {
    public final Method method;
    public String nextExec;

    public FuncExecNode(String id, Method method) {
        super(id, "FuncExecNode" + "->" + method.getName());
        this.method = method;
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
