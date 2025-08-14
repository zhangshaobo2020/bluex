package com.zsb.bluex.core.runtime.node.impl;

import com.zsb.bluex.core.def.ControlDef;
import com.zsb.bluex.core.def.ParamDef;
import com.zsb.bluex.core.launch.MetaHolder;
import com.zsb.bluex.core.runtime.ExecTask;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.ExecNodeDefinition;
import com.zsb.bluex.core.runtime.node.ExecNode;
import com.zsb.bluex.core.runtime.param.ParamSource;

public class BranchNode extends ExecNode implements ExecNodeDefinition {

    public ParamSource<Boolean> condition;
    public String trueExec;
    public String falseExec;

    public BranchNode() {
    }

    public BranchNode(String id) {
        super(id, "BranchNode");
    }

    @Override
    public void execute(ExecutionContext ctx) throws Exception {
        boolean cond = condition.getValue(ctx);
        if (cond) {
            if (trueExec != null) {
                ctx.schedule(new ExecTask(trueExec, null));
            }
        } else {
            if (falseExec != null) {
                ctx.schedule(new ExecTask(falseExec, null));
            }
        }
    }

    @Override
    public ControlDef provideDefinition() {
        ControlDef def = new ControlDef();
        def.setName("Branch");
        def.setDisplayName("Branch分支");
        def.setCategory("控制节点|Branch");
        def.setQualifiedName("CONTROL:Branch");
        def.setSignature("CONTROL:Branch");

        def.getInputExecDefs().add(new ParamDef("Exec"));
        def.getOutputExecDefs().add(new ParamDef("True"));
        def.getOutputExecDefs().add(new ParamDef("False"));

        def.getInputParamDefs().add(
                new ParamDef(
                        "Cond",
                        MetaHolder.PRIMITIVE_DEFINITION.get("java.lang.Boolean")
                )
        );
        return def;
    }
}
