package com.zsb.bluex.core.runtime;

import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.core.runtime.node.impl.DelayNode;
import com.zsb.bluex.core.runtime.node.impl.ForLoopNode;
import com.zsb.bluex.core.runtime.node.impl.FuncExecNode;
import com.zsb.bluex.core.runtime.node.impl.FuncPureNode;
import com.zsb.bluex.core.runtime.param.LiteralValueSource;
import com.zsb.bluex.core.runtime.param.NodeOutputSource;
import com.zsb.bluex.lib.ObjectLib;
import com.zsb.bluex.lib.RandomLib;

import java.lang.reflect.Method;

public class RuntimeTest2 {
    public static void main(String[] args) throws Exception {
        ExecutionContext ctx = new ExecutionContext();

        Method method1 = ObjectLib.class.getMethod("Print", INPUT.class);
        FuncExecNode funcExecNode = new FuncExecNode("0001", "FuncExec", method1);
        funcExecNode.setInputParam("Obj", new LiteralValueSource<>("Hello"));
        funcExecNode.nextExec = "0002";
        ctx.addExecNode(funcExecNode);

        DelayNode delayNode = new DelayNode("0002", "DelayNode");
        delayNode.delayMillis = 2000L;
        delayNode.nextExec = "0003";
        ctx.addExecNode(delayNode);

        FuncExecNode funcExecNode2 = new FuncExecNode("0003", "FuncExec", method1);
        funcExecNode2.setInputParam("Obj", new LiteralValueSource<>("World"));
        ctx.addExecNode(funcExecNode2);

        ctx.schedule(new ExecTask("0001", null));
        ctx.run();
    }
}
