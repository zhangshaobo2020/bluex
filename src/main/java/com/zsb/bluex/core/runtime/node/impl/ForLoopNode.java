package com.zsb.bluex.core.runtime.node.impl;

import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.ExecNode;
import com.zsb.bluex.core.runtime.param.ParamSource;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ForLoopNode extends ExecNode {

    private ParamSource<Integer> from;
    private ParamSource<Integer> to;
    private String stepExec;
    private String completedExec;
    private int currentIndex;
    private boolean started = false;
    // 缓存值
    private int cachedIndex;

    public static String INDEX = "Index";

    public ForLoopNode(String id, String name) {
        super(id, name);
    }

    public void setRange(ParamSource<Integer> from, ParamSource<Integer> to) {
        this.from = from;
        this.to = to;
        // 赋值from作为起始索引
        cachedIndex = from.getValue(getCtx());
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
    public String execute(ExecutionContext ctx) {
        if (!started) {
            currentIndex = from.getValue(ctx);
            started = true;
        }
        if (currentIndex < to.getValue(ctx)) {
            // 缓存当前值
            cachedIndex = currentIndex;
            currentIndex++;
            return stepExec != null ? stepExec : getId();
        } else {
            return completedExec;
        }
    }
}
