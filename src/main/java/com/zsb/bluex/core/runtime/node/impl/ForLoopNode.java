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
    public String stepExec;
    public String completedExec;
    public int currentIndex;
    public boolean started = false;
    // 缓存值
    public int cachedIndex;

    public static String INDEX = "Index";

    public ForLoopNode(String id, String name) {
        super(id, name);
    }

    @SneakyThrows
    public void setRange(ParamSource<Integer> from, ParamSource<Integer> to) {
        this.from = from;
        this.to = to;
        // 赋值from作为起始索引
        cachedIndex = from.getValue(ctx);
    }

    @Override
    public OUTPUT<?> getOutputParam(String outputParamName) {
        if (INDEX.equals(outputParamName)) {
            return new OUTPUT<>(cachedIndex);
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
            // 缓存当前值
            cachedIndex = currentIndex;
            currentIndex++;
            if (stepExec != null) {
                ctx.schedule(new ExecTask(stepExec, null));
            }
            ctx.schedule(new ExecTask(id, null));
        } else {
            if (completedExec != null) {
                ctx.schedule(new ExecTask(completedExec, null));
            }
        }
    }
}
