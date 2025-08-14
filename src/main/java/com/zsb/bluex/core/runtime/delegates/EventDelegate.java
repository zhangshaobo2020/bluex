package com.zsb.bluex.core.runtime.delegates;

import com.zsb.bluex.core.graph.GraphView;
import com.zsb.bluex.core.runtime.node.ExecNodeDefinition;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class EventDelegate implements ExecNodeDefinition {

    public GraphView graphView;

    public EventDelegate(GraphView graphView) {
        this.graphView = graphView;
    }

    public abstract void start(boolean isDebug) throws Exception;

    public abstract void end() throws Exception;
}
