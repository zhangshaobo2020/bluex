package com.zsb.bluex.core.runtime.node;

import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.core.runtime.ExecutionContext;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

@NoArgsConstructor
public abstract class ExecNode extends BaseNode {

    protected final Map<String, OUTPUT<?>> outputs = new LinkedHashMap<>();

    public ExecNode(String id, String name) {
        super(id, name);
    }

    public OUTPUT<?> getOutputParam(String outputName) {
        return outputs.get(outputName);
    }

    public abstract String execute(ExecutionContext ctx);
}
