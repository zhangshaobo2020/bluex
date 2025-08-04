package com.zsb.bluex.core.runtime.node.impl;

import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.ExecNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BeginPlayNode extends ExecNode {

    private String nextExec;

    public BeginPlayNode(String id, String name) {
        super(id, name);
    }

    @Override
    public String execute(ExecutionContext ctx) {
        // BeginPlay 仅作为起点，直接进入下一节点
        return nextExec;
    }
}
