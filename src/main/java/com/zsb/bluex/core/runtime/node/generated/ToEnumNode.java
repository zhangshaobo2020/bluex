package com.zsb.bluex.core.runtime.node.generated;

import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.PureNode;

@SuppressWarnings({"unchecked", "rawtypes"})
public class ToEnumNode extends PureNode {
    public Class<?> clazz;

    public ToEnumNode(String id, Class<?> clazz) {
        super(id, "ToEnumNode" + "->" + clazz.toString());
        this.clazz = clazz;
    }

    @Override
    public Object evaluate(String outputName, ExecutionContext ctx) throws Exception {
        String strValue = (String) inputParams.get("Str").getValue(ctx);
        return Enum.valueOf((Class<Enum>) clazz.asSubclass(Enum.class), strValue);
    }
}
