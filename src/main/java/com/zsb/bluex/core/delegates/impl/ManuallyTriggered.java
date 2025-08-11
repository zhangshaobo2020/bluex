package com.zsb.bluex.core.delegates.impl;

import com.zsb.bluex.core.delegates.EventDelegate;
import com.zsb.bluex.core.graph.GraphView;
import com.zsb.bluex.core.runtime.ExecutionContext;

public class ManuallyTriggered extends EventDelegate {

    public ManuallyTriggered(GraphView graphView) throws Exception {
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
}
