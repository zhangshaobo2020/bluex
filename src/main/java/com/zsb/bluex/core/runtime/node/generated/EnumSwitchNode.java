package com.zsb.bluex.core.runtime.node.generated;

import com.zsb.bluex.core.def.ControlDef;
import com.zsb.bluex.core.runtime.ExecTask;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.ExecNode;
import com.zsb.bluex.core.runtime.node.ExecNodeDefinition;

import java.util.Map;

public class EnumSwitchNode extends ExecNode implements ExecNodeDefinition {

    /**
     * 枚举类
     */
    public Class<?> clazz;

    /**
     * 枚举值到 exec 节点 id 的映射
     */
    public Map<String, String> execMapping;

    public EnumSwitchNode(String id, Class<?> clazz, Map<String, String> execMapping) {
        super(id, "EnumSwitchNode" + "->" + clazz.toString());
        this.clazz = clazz;
        this.execMapping = execMapping;
    }

    @Override
    public void execute(ExecutionContext ctx) throws Exception {
        if (clazz == null || !clazz.isEnum()) {
            throw new IllegalStateException("EnumSwitchNode requires a valid enum class");
        }
        Enum<?> enumValue = (Enum<?>) inputParams.get("Enum").getValue(ctx);
        if (enumValue == null) {
            return; // 无值则不调度任何分支
        }

        String key = enumValue.name();
        String nextExecId = execMapping != null ? execMapping.get(key) : null;

        if (nextExecId != null) {
            ctx.schedule(new ExecTask(nextExecId, null));
        }
    }

    @Override
    public ControlDef provideDefinition() {
        // 因为是根据Enum动态生成的，所以不需要provideDefinition
        return null;
    }
}
