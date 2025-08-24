package com.zsb.bluex.core.runtime.node.generated;

import com.zsb.bluex.core.runtime.ExecTask;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.ExecNode;
import com.zsb.bluex.core.runtime.param.ParamSource;

public class SetterNode extends ExecNode {
    public ParamSource<?> name;
    public ParamSource<?> var;
    public String nextExec;

    public SetterNode(String id, ParamSource<?> name, ParamSource<?> var) {
        super(id, "SetterNode" + "->" + name.toString());
        this.name = name;
        this.var = var;
    }

    @Override
    public void execute(ExecutionContext ctx) throws Exception {
        ctx.getRuntimeVars().put((String) name.getValue(ctx), var.getValue(ctx));
        if (nextExec != null) {
            ctx.schedule(new ExecTask(nextExec, null));
        }
    }
}
