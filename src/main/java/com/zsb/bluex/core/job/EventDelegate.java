package com.zsb.bluex.core.job;

import com.zsb.bluex.core.graph.GraphView;
import com.zsb.bluex.core.runtime.node.ExecNodeDefinition;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class EventDelegate implements ExecNodeDefinition {

    public GraphView graphView;

    public EventDelegate(GraphView graphView) {
        this.graphView = graphView;
    }

    public abstract void start() throws Exception;

    public abstract void end() throws Exception;
}
