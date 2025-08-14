package com.zsb.bluex.core.runtime.node.impl;

import com.zsb.bluex.core.def.ControlDef;
import com.zsb.bluex.core.def.ParamDef;
import com.zsb.bluex.core.launch.MetaHolder;
import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.core.runtime.ExecTask;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.ExecNodeDefinition;
import com.zsb.bluex.core.runtime.node.ExecNode;
import com.zsb.bluex.core.runtime.param.ParamSource;
import lombok.SneakyThrows;

public class ForLoopNode extends ExecNode implements ExecNodeDefinition {
    public ParamSource<Integer> from;
    public ParamSource<Integer> to;
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

    @SneakyThrows
    public void setRange(ParamSource<Integer> from, ParamSource<Integer> to) {
        this.from = from;
        this.to = to;
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
            currentIndex = from.getValue(ctx);
            started = true;
        }
        if (currentIndex < to.getValue(ctx)) {
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
                        "From",
                        MetaHolder.PRIMITIVE_DEFINITION.get("java.lang.Integer")
                )
        );
        def.getInputParamDefs().add(
                new ParamDef(
                        "To",
                        MetaHolder.PRIMITIVE_DEFINITION.get("java.lang.Integer")
                )
        );
        def.getOutputParamDefs().add(
                new ParamDef(
                        "Index",
                        MetaHolder.PRIMITIVE_DEFINITION.get("java.lang.Integer")
                )
        );
        return def;
    }
}
