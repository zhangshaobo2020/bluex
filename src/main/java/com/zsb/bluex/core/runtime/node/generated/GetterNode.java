package com.zsb.bluex.core.runtime.node.generated;

import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.PureNode;
import com.zsb.bluex.core.runtime.param.ParamSource;

public class GetterNode extends PureNode {
    public ParamSource<String> varName;

    public GetterNode(String id, ParamSource<String> varName) {
        super(id, "GetterNode" + "->" + varName.toString());
        this.varName = varName;
    }

    @Override
    public Object evaluate(String outputName, ExecutionContext ctx) throws Exception {
        String varNameValue = varName.getValue(ctx);
        return ctx.getRuntimeVars().getOrDefault(varNameValue, null);
    }
}
