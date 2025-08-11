package com.zsb.bluex.core.runtime.node.impl;

import com.zsb.bluex.core.runtime.ExecTask;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.ExecNode;

public class DelegateNode extends ExecNode {
    public String nextExec;

    public DelegateNode(String id) {
        super(id, "DelegateNode");
    }

    @Override
    public void execute(ExecutionContext ctx) throws Exception {
        // DelegateNode 仅作为起点，直接进入下一节点
        if (nextExec != null) {
            ctx.schedule(new ExecTask(nextExec, null));
        }
    }
}
