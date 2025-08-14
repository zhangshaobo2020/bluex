package com.zsb.bluex.core.runtime.node.impl;

import com.zsb.bluex.core.def.ControlDef;
import com.zsb.bluex.core.def.ParamDef;
import com.zsb.bluex.core.launch.MetaHolder;
import com.zsb.bluex.core.runtime.ExecTask;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.ExecNodeDefinition;
import com.zsb.bluex.core.runtime.node.ExecNode;
import com.zsb.bluex.core.runtime.param.ParamSource;

public class WhileNode extends ExecNode implements ExecNodeDefinition {
    public ParamSource<Boolean> condition;
    public String loopBodyExec;
    public String completedExec;

    public WhileNode() {
    }

    public WhileNode(String id) {
        super(id, "WhileNode");
    }

    @Override
    public void execute(ExecutionContext ctx) throws Exception {
        if (Boolean.TRUE.equals(condition.getValue(ctx))) {
            if (loopBodyExec != null) {
                ctx.schedule(new ExecTask(loopBodyExec, null));
            }
            ctx.schedule(new ExecTask(id, null));
        } else {
            if (completedExec != null) {
                ctx.schedule(new ExecTask(completedExec, null));
            }
        }
    }

    @Override
    public ControlDef provideDefinition() {
        ControlDef def = new ControlDef();
        def.setName("While");
        def.setDisplayName("While循环");
        def.setCategory("控制节点|While");
        def.setQualifiedName("CONTROL:While");
        def.setSignature("CONTROL:While");

        def.getInputExecDefs().add(new ParamDef("Exec"));
        def.getOutputExecDefs().add(new ParamDef("LoopBody"));
        def.getOutputExecDefs().add(new ParamDef("Completed"));

        def.getInputParamDefs().add(
                new ParamDef(
                        "Cond",
                        MetaHolder.PRIMITIVE_DEFINITION.get("java.lang.Boolean")
                )
        );
        return def;
    }
}
