package com.zsb.bluex.core.runtime.node.impl;

import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.ExecNode;
import com.zsb.bluex.core.runtime.param.ParamSource;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BranchNode extends ExecNode {
    private ParamSource<Boolean> condition;
    private String trueExec;
    private String falseExec;

    public BranchNode(String id, String name) {
        super(id, name);
    }

    @Override
    public String execute(ExecutionContext ctx) {
        boolean cond = condition.getValue(ctx);
        return cond ? trueExec : falseExec;
    }
}
