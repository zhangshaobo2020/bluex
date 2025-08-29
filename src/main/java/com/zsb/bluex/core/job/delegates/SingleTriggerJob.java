package com.zsb.bluex.core.job.delegates;

import com.zsb.bluex.core.def.ControlDef;
import com.zsb.bluex.core.def.ParamDef;
import com.zsb.bluex.core.graph.GraphView;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.job.EventDelegate;

public class SingleTriggerJob extends EventDelegate {

    public SingleTriggerJob() {
    }

    public SingleTriggerJob(GraphView graphView) {
        super(graphView);
    }

    @Override
    public void start() throws Exception {
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
        def.setName("SingleTriggerJob");
        def.setDisplayName("单次触发");
        def.setCategory("事件委托|SingleTriggerJob");
        def.setQualifiedName("DELEGATE:SingleTriggerJob");
        def.setSignature("DELEGATE:SingleTriggerJob");
        def.setDelegate(true);

        def.getOutputExecDefs().add(new ParamDef("Exec"));
        return def;
    }
}
