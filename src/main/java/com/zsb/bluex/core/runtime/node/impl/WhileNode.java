package com.zsb.bluex.core.runtime.node.impl;

import com.zsb.bluex.core.runtime.ExecTask;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.ExecNode;
import com.zsb.bluex.core.runtime.param.ParamSource;

public class WhileNode extends ExecNode {
    public ParamSource<Boolean> condition;
    public String loopBodyExec;
    public String completedExec;

    public WhileNode(String id) {
        super(id, "WhileNode");
    }

    @Override
    public void execute(ExecutionContext ctx) throws Exception {
        if (Boolean.TRUE.equals(condition.getValue(ctx))) {
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
