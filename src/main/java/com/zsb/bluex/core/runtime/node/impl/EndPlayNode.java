package com.zsb.bluex.core.runtime.node.impl;

import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.ExecNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Deprecated
@EqualsAndHashCode(callSuper = true)
@Data
public class EndPlayNode extends ExecNode {

    public EndPlayNode(String id, String name) {
        super(id, name);
    }

    @Override
    public String execute(ExecutionContext ctx) {
        // EndPlay 是终点，不再有下一步
        return null;
    }
}
