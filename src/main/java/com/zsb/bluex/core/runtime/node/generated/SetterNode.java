package com.zsb.bluex.core.runtime.node.generated;

import com.zsb.bluex.core.runtime.ExecTask;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.ExecNode;

public class SetterNode extends ExecNode {
    public Class<?> clazz;
    public String nextExec;

    public SetterNode(String id, Class<?> clazz) {
        super(id, "SetterNode" + "->" + clazz.toString());
        this.name = name;
        this.clazz = clazz;
    }

    @Override
    public void execute(ExecutionContext ctx) throws Exception {
        String varName = (String) inputParams.get("Name").getValue(ctx);
        Object varValue = inputParams.get("Var").getValue(ctx);
        ctx.getRuntimeVars().put(varName, varValue);
        if (nextExec != null) {
            ctx.schedule(new ExecTask(nextExec, null));
        }
    }
}
