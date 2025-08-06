package com.zsb.bluex.core.runtime.node.impl;

import com.zsb.bluex.core.runtime.ExecTask;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.ExecNode;
import com.zsb.bluex.core.runtime.param.ParamSource;

public class BranchNode extends ExecNode {
    public ParamSource<Boolean> condition;
    public String trueExec;
    public String falseExec;

    public BranchNode(String id, String name) {
        super(id, name);
    }

    @Override
    public void execute(ExecutionContext ctx) throws Exception {
        boolean cond = condition.getValue(ctx);
        if (cond) {
            if (trueExec != null) {
                ctx.schedule(new ExecTask(trueExec, null));
            }
        } else {
            if (falseExec != null) {
                ctx.schedule(new ExecTask(falseExec, null));
            }
        }
    }
}
