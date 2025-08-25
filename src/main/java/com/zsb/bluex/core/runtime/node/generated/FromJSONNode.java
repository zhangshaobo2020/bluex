package com.zsb.bluex.core.runtime.node.generated;

import com.alibaba.fastjson2.JSON;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.PureNode;

public class FromJSONNode extends PureNode {
    public Class<?> clazz;

    public FromJSONNode(String id, Class<?> clazz) {
        super(id, "FromJSONNode" + "->" + clazz.toString());
        this.clazz = clazz;
    }

    @Override
    public Object evaluate(String outputName, ExecutionContext ctx) throws Exception {
        return JSON.parseObject((String) inputParams.get("Json").getValue(ctx), clazz);
    }
}
