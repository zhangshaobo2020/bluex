package com.zsb.bluex.core.runtime.node.impl;

import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.ExecNode;

@Deprecated
public class EndPlayNode extends ExecNode {

    public EndPlayNode(String id, String name) {
        super(id, name);
    }

    @Override
    public void execute(ExecutionContext ctx) throws Exception {
        // EndPlay 是终点，不再有下一步
    }
}
