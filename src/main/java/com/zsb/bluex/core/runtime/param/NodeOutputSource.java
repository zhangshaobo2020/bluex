package com.zsb.bluex.core.runtime.param;

import com.zsb.bluex.core.runtime.ExecutionContext;
import lombok.Getter;

@Getter
public class NodeOutputSource<T> implements ParamSource<T> {
    private final String nodeId;
    private final String outputParamName;

    public NodeOutputSource(String nodeId, String outputParamName) {
        this.nodeId = nodeId;
        this.outputParamName = outputParamName;
    }

    @Override
    public T getValue(ExecutionContext ctx) {
        return ctx.getNodeOutputParamValue(nodeId, outputParamName);
    }
}
