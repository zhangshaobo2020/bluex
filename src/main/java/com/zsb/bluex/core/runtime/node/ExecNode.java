package com.zsb.bluex.core.runtime.node;

import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.core.runtime.ExecutionContext;

public abstract class ExecNode extends BaseNode {

    public ExecNode() {
    }

    public ExecNode(String id, String name) {
        super(id, name);
    }

    public OUTPUT<?> getOutputParam(String outputName) {
        return outputs.get(outputName);
    }

    public abstract void execute(ExecutionContext ctx) throws Exception;
}
