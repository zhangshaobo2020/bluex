package com.zsb.bluex.core.runtime;

import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.runtime.node.control.DelayNode;
import com.zsb.bluex.core.runtime.node.function.FuncExecNode;
import com.zsb.bluex.core.runtime.param.LiteralValueSource;
import com.zsb.bluex.defaults.libs.ObjectLib;

import java.lang.reflect.Method;

public class RuntimeTest2 {
    public static void main(String[] args) throws Exception {
        ExecutionContext ctx = new ExecutionContext();

        Method method1 = ObjectLib.class.getMethod("Print", INPUT.class);
        FuncExecNode funcExecNode = new FuncExecNode("0001", method1);
        funcExecNode.setInputParam("Obj", new LiteralValueSource<>("Hello"));
        funcExecNode.nextExec = "0002";
        ctx.addExecNode(funcExecNode);

        DelayNode delayNode = new DelayNode("0002");
        delayNode.delayMillis = new LiteralValueSource<>(2000L);
        delayNode.nextExec = "0003";
        ctx.addExecNode(delayNode);

        FuncExecNode funcExecNode2 = new FuncExecNode("0003", method1);
        funcExecNode2.setInputParam("Obj", new LiteralValueSource<>("World"));
        ctx.addExecNode(funcExecNode2);

        ctx.schedule(new ExecTask("0001", null));
        ctx.run();
    }
}
