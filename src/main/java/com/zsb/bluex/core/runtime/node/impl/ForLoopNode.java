package com.zsb.bluex.core.runtime.node.impl;

import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.core.runtime.ExecTask;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.ExecNode;
import com.zsb.bluex.core.runtime.param.ParamSource;
import lombok.SneakyThrows;

public class ForLoopNode extends ExecNode {

    public ParamSource<Integer> from;
    public ParamSource<Integer> to;
    public String loopBodyExec;
    public String completedExec;
    public int currentIndex;
    public boolean started = false;

    public static String INDEX = "Index";

    public ForLoopNode(String id) {
        super(id, "ForLoopNode");
    }

    @SneakyThrows
    public void setRange(ParamSource<Integer> from, ParamSource<Integer> to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public OUTPUT<?> getOutputParam(String outputParamName) {
        if (INDEX.equals(outputParamName)) {
            return new OUTPUT<>(currentIndex - 1);
        } else {
            return super.getOutputParam(outputParamName);
        }
    }

    @Override
    public void execute(ExecutionContext ctx) throws Exception {
        if (!started) {
            currentIndex = from.getValue(ctx);
            started = true;
        }
        if (currentIndex < to.getValue(ctx)) {
            currentIndex++;
            if (loopBodyExec != null) {
                ctx.schedule(new ExecTask(loopBodyExec, null));
            }
            ctx.schedule(new ExecTask(id, null));
        } else {
            if (completedExec != null) {
                ctx.schedule(new ExecTask(completedExec, null));
            }
        }
    }
}
