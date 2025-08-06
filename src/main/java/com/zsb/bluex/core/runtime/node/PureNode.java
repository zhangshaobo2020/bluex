package com.zsb.bluex.core.runtime.node;

import com.zsb.bluex.core.runtime.ExecutionContext;

public abstract class PureNode extends BaseNode {
    public PureNode(String id, String name) {
        super(id, name);
    }

    public abstract Object evaluate(String outputName, ExecutionContext ctx) throws Exception;
}
