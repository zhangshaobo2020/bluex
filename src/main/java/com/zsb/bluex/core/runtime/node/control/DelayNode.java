package com.zsb.bluex.core.runtime.node.control;

import com.zsb.bluex.core.def.ControlDef;
import com.zsb.bluex.core.def.ParamDef;
import com.zsb.bluex.core.launch.MetaHolder;
import com.zsb.bluex.core.runtime.ExecTask;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.ExecNode;
import com.zsb.bluex.core.runtime.node.ExecNodeDefinition;
import com.zsb.bluex.core.runtime.param.ParamSource;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class DelayNode extends ExecNode implements ExecNodeDefinition {

    private final static String PARAM_PIN_DELAY = "Delay";
    public String nextExec;

    private static void spin() {
    }

    public DelayNode() {
    }

    public DelayNode(String id) {
        super(id, "DelayNode");
    }

    @Override
    public void execute(ExecutionContext ctx) throws Exception {
        log.debug("[DelayNode] 延迟开始 {}", LocalDateTime.now());
        Long delayMillis = (Long) inputParams.get(PARAM_PIN_DELAY).getValue(ctx);
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < delayMillis) {
            spin();
        }
//        Thread.sleep(delayMillis.getValue(ctx));
        log.debug("[DelayNode] 延迟结束 {}", LocalDateTime.now());
        if (nextExec != null) {
            ctx.schedule(new ExecTask(nextExec, null));
        }
    }

    @Override
    public ControlDef provideDefinition() {
        ControlDef def = new ControlDef();
        def.setName("Delay");
        def.setDisplayName("延迟");
        def.setCategory("控制节点|Delay");
        def.setQualifiedName("CONTROL:Delay");
        def.setSignature("CONTROL:Delay");

        def.getInputExecDefs().add(new ParamDef("Exec"));
        def.getOutputExecDefs().add(new ParamDef("Exec"));

        def.getInputParamDefs().add(
                new ParamDef(
                        PARAM_PIN_DELAY,
                        MetaHolder.PRIMITIVE_DEFINITION.get("java.lang.Long")
                )
        );
        return def;
    }
}
