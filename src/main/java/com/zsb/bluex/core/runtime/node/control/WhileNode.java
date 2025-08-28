package com.zsb.bluex.core.runtime.node.control;

import com.zsb.bluex.core.def.ControlDef;
import com.zsb.bluex.core.def.ParamDef;
import com.zsb.bluex.core.launch.MetaHolder;
import com.zsb.bluex.core.runtime.ExecTask;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.ExecNode;
import com.zsb.bluex.core.runtime.node.ExecNodeDefinition;

public class WhileNode extends ExecNode implements ExecNodeDefinition {

    private final static String PARAM_PIN_COND = "Cond";
    public String loopBodyExec;
    public String completedExec;

    public WhileNode() {
    }

    public WhileNode(String id) {
        super(id, "WhileNode");
    }

    @Override
    public void execute(ExecutionContext ctx) throws Exception {
        Boolean condition = (Boolean) inputParams.get(PARAM_PIN_COND).getValue(ctx);
        if (condition) {
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
                        PARAM_PIN_COND,
                        MetaHolder.PRIMITIVE_DEFINITION.get("java.lang.Boolean")
                )
        );
        return def;
    }
}
