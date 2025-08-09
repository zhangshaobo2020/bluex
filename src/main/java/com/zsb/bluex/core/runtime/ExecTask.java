package com.zsb.bluex.core.runtime;

import java.util.Map;

public class ExecTask {
    public String nodeId;
    public Map<String, Object> payload;

    public ExecTask(String nodeId, Map<String, Object> payload) {
        this.nodeId = nodeId;
        this.payload = payload;
    }
}
