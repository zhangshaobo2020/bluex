package com.zsb.bluex.core.runtime.node.impl;

import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.ExecNode;

@Deprecated
public class BeginPlayNode extends ExecNode {

    public String nextExec;

    public BeginPlayNode(String id, String name) {
        super(id, name);
    }

    @Override
    public void execute(ExecutionContext ctx) throws Exception {
        // BeginPlay 仅作为起点，直接进入下一节点
    }
}
