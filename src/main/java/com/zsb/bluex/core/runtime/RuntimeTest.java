package com.zsb.bluex.core.runtime;

import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.core.runtime.node.impl.ForLoopNode;
import com.zsb.bluex.core.runtime.node.impl.FuncExecNode;
import com.zsb.bluex.core.runtime.node.impl.FuncPureNode;
import com.zsb.bluex.core.runtime.param.LiteralValueSource;
import com.zsb.bluex.core.runtime.param.NodeOutputSource;
import com.zsb.bluex.lib.ObjectLib;
import com.zsb.bluex.lib.MathLib;
import com.zsb.bluex.lib.RandomLib;

import java.lang.reflect.Method;

public class RuntimeTest {
    public static void main(String[] args) throws Exception {
        ExecutionContext ctx = new ExecutionContext();

        ForLoopNode forLoopNode = new ForLoopNode("0001", "ForLoop");
        forLoopNode.setRange(new LiteralValueSource<>(0), new LiteralValueSource<>(10));
        forLoopNode.stepExec = "0002";
        ctx.addExecNode(forLoopNode);

        Method method1 = ObjectLib.class.getMethod("Print", INPUT.class);
        FuncExecNode funcExecNode = new FuncExecNode("0002", "FuncExec", method1);
        funcExecNode.setInputParam("Obj", new NodeOutputSource<>("0003", "Ret"));
        ctx.addExecNode(funcExecNode);

        Method method2 = RandomLib.class.getMethod("RandomInteger", INPUT.class, INPUT.class, OUTPUT.class);
        FuncPureNode funcPureNode = new FuncPureNode("0003", "FuncPure", method2);
        funcPureNode.setInputParam("Min", new LiteralValueSource<>(0));
        funcPureNode.setInputParam("Max", new LiteralValueSource<>(10));
        funcPureNode.setOutputParam("Ret", new OUTPUT<Integer>());
        ctx.addPureNode(funcPureNode);

        ctx.schedule(new ExecTask("0001", null));
        ctx.run();
    }
}
