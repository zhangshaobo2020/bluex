package com.zsb.bluex.core.runtime.node.generated.database;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.PureNode;

import java.io.Serializable;

public class MybatisPlusSelectByIdNode extends PureNode {
    public Class<?> clazz;

    public MybatisPlusSelectByIdNode(String id, Class<?> clazz) {
        super(id, "MybatisPlusSelectByIdNode" + "->" + clazz.toString());
        this.clazz = clazz;
    }

    @Override
    public Object evaluate(String outputName, ExecutionContext ctx) throws Exception {
        // 获取主键
        Serializable entityId = (Serializable) inputParams.get("Id").getValue(ctx);
        // 通过反射创建 Model 实例
        Model<?> model = (Model<?>) clazz.getDeclaredConstructor().newInstance();
        // 调用 ActiveRecord 的 selectById 方法
        return model.selectById(entityId);
    }
}
