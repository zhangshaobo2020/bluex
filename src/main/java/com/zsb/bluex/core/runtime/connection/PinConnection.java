package com.zsb.bluex.core.runtime.connection;

public class PinConnection {
    public String id;
    public String source;
    public String sourceOutput;
    public String target;
    public String targetInput;

    public PinConnection(String id, String source, String sourceOutput, String target, String targetInput) {
        this.id = id;
        this.source = source;
        this.sourceOutput = sourceOutput;
        this.target = target;
        this.targetInput = targetInput;
    }
}
