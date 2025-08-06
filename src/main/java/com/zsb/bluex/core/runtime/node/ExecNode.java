package com.zsb.bluex.core.runtime.node;

import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.core.runtime.ExecutionContext;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class ExecNode extends BaseNode {

    public final Map<String, OUTPUT<?>> outputs = new LinkedHashMap<>();

    public ExecNode(String id, String name) {
        super(id, name);
    }

    public OUTPUT<?> getOutputParam(String outputName) {
        return outputs.get(outputName);
    }

    public abstract void execute(ExecutionContext ctx) throws Exception;
}
