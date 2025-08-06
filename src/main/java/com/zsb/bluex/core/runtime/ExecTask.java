package com.zsb.bluex.core.runtime;

public class ExecTask {
    public String nodeId;
    // input执行节点的名称
    public String execPort;

    public ExecTask(String nodeId, String execPort) {
        this.nodeId = nodeId;
        this.execPort = execPort;
    }
}
