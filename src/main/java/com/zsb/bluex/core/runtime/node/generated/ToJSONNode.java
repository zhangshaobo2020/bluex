package com.zsb.bluex.core.runtime.node.generated;

import com.alibaba.fastjson2.JSON;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.PureNode;

public class ToJSONNode extends PureNode {
    public Class<?> clazz;

    public ToJSONNode(String id, Class<?> clazz) {
        super(id, "ToJSONNode" + "->" + clazz.toString());
        this.clazz = clazz;
    }

    @Override
    public Object evaluate(String outputName, ExecutionContext ctx) throws Exception {
        return JSON.toJSONString(inputParams.get("Obj").getValue(ctx));
    }
}
