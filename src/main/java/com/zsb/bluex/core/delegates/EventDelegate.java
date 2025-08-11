package com.zsb.bluex.core.delegates;

import com.zsb.bluex.core.graph.GraphView;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class EventDelegate {
    public GraphView graphView;

    public EventDelegate(GraphView graphView) {
        this.graphView = graphView;
    }

    public abstract void start() throws Exception;

    public abstract void end() throws Exception;
}
