package com.zsb.bluex.core.runtime.delegates.impl;

import com.zsb.bluex.core.def.ControlDef;
import com.zsb.bluex.core.def.ParamDef;
import com.zsb.bluex.core.graph.GraphView;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.delegates.EventDelegate;

public class ManuallyTriggered extends EventDelegate {

    public ManuallyTriggered() {
    }

    public ManuallyTriggered(GraphView graphView) {
        super(graphView);
    }

    @Override
    public void start(boolean isDebug) throws Exception {
        /* 执行开始 */
        ExecutionContext newCtx = graphView.buildExecCtx();
        newCtx.run();
        /* 执行结束 */
    }

    @Override
    public void end() throws Exception {

    }

    @Override
    public ControlDef provideDefinition() {
        ControlDef def = new ControlDef();
        def.setName("ManuallyTriggered");
        def.setDisplayName("手动触发");
        def.setCategory("事件委托|ManuallyTriggered");
        def.setQualifiedName("DELEGATE:ManuallyTriggered");
        def.setSignature("DELEGATE:ManuallyTriggered");
        def.setDelegate(true);

        def.getOutputExecDefs().add(new ParamDef("Exec"));
        return def;
    }
}
