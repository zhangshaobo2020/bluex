package com.zsb.bluex.core.runtime.node.control;

import com.zsb.bluex.core.def.ControlDef;
import com.zsb.bluex.core.def.ParamDef;
import com.zsb.bluex.core.launch.MetaHolder;
import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.core.runtime.ExecTask;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.ExecNode;
import com.zsb.bluex.core.runtime.node.ExecNodeDefinition;

public class ForLoopNode extends ExecNode implements ExecNodeDefinition {

    private final static String PARAM_PIN_FROM = "From";
    private final static String PARAM_PIN_TO = "To";
    private final static String PARAM_PIN_INDEX = "Index";
    public String loopBodyExec;
    public String completedExec;
    public int currentIndex;
    public boolean started = false;

    public static String INDEX = "Index";

    public ForLoopNode() {
    }

    public ForLoopNode(String id) {
        super(id, "ForLoopNode");
    }

    @Override
    public OUTPUT<?> getOutputParam(String outputParamName) {
        if (INDEX.equals(outputParamName)) {
            return new OUTPUT<>(currentIndex - 1);
        } else {
            return super.getOutputParam(outputParamName);
        }
    }

    @Override
    public void execute(ExecutionContext ctx) throws Exception {
        if (!started) {
            currentIndex = (Integer) inputParams.get(PARAM_PIN_FROM).getValue(ctx);
            started = true;
        }
        if (currentIndex < (Integer) inputParams.get(PARAM_PIN_TO).getValue(ctx)) {
            currentIndex++;
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
        def.setName("ForLoop");
        def.setDisplayName("For循环");
        def.setCategory("控制节点|ForLoop");
        def.setQualifiedName("CONTROL:ForLoop");
        def.setSignature("CONTROL:ForLoop");

        def.getInputExecDefs().add(new ParamDef("Exec"));
        def.getOutputExecDefs().add(new ParamDef("LoopBody"));
        def.getOutputExecDefs().add(new ParamDef("Completed"));

        def.getInputParamDefs().add(
                new ParamDef(
                        PARAM_PIN_FROM,
                        MetaHolder.PRIMITIVE_DEFINITION.get("java.lang.Integer")
                )
        );
        def.getInputParamDefs().add(
                new ParamDef(
                        PARAM_PIN_TO,
                        MetaHolder.PRIMITIVE_DEFINITION.get("java.lang.Integer")
                )
        );
        def.getOutputParamDefs().add(
                new ParamDef(
                        PARAM_PIN_INDEX,
                        MetaHolder.PRIMITIVE_DEFINITION.get("java.lang.Integer")
                )
        );
        return def;
    }
}
