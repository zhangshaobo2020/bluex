package com.zsb.bluex.core.runtime.node.impl;

import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.ExecNode;
import com.zsb.bluex.core.runtime.param.ParamSource;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class WhileNode extends ExecNode {
    private ParamSource<Boolean> condition;
    private String bodyExec;
    private String completedExec;

    public WhileNode(String id, String name) {
        super(id, name);
    }

    @Override
    public String execute(ExecutionContext ctx) {
        if (condition != null && Boolean.TRUE.equals(condition.getValue(ctx))) {
            return bodyExec != null ? bodyExec : getId();
        } else {
            return completedExec;
        }
    }
}
