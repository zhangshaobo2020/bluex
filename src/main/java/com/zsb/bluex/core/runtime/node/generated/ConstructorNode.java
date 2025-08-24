package com.zsb.bluex.core.runtime.node.generated;

import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.PureNode;
import com.zsb.bluex.core.runtime.param.ParamSource;

import java.lang.reflect.Field;
import java.util.Map;

public class ConstructorNode extends PureNode {
    public Class<?> clazz;

    public ConstructorNode(String id, Class<?> clazz) {
        super(id, "ConstructorNode" + "->" + clazz.toString());
        this.clazz = clazz;
    }

    @Override
    public Object evaluate(String outputName, ExecutionContext ctx) throws Exception {
        Object obj = clazz.newInstance();
        for (Map.Entry<String, ParamSource<?>> entry : inputParams.entrySet()) {
            String fieldName = entry.getKey();
            ParamSource<?> fieldValue = entry.getValue();
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, fieldValue.getValue(ctx));
        }
        return obj;
    }
}
