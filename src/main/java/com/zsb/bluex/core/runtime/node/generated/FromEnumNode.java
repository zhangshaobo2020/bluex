package com.zsb.bluex.core.runtime.node.generated;

import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.PureNode;

public class FromEnumNode extends PureNode {
    public Class<?> clazz;

    public FromEnumNode(String id, Class<?> clazz) {
        super(id, "FromEnumNode" + "->" + clazz.toString());
        this.clazz = clazz;
    }

    @Override
    public Object evaluate(String outputName, ExecutionContext ctx) throws Exception {
        Enum<?> enumValue = (Enum<?>) inputParams.get("Enum").getValue(ctx);
        return enumValue.name();
    }
}
