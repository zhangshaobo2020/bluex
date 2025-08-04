package com.zsb.bluex.core.runtime;

import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.core.runtime.node.impl.BeginPlayNode;
import com.zsb.bluex.core.runtime.node.impl.FuncExecNode;
import com.zsb.bluex.core.runtime.node.impl.FuncPureNode;
import com.zsb.bluex.core.runtime.param.LiteralValueSource;
import com.zsb.bluex.core.runtime.param.NodeOutputSource;
import com.zsb.bluex.lib.CommonLib;
import com.zsb.bluex.lib.MathLib;

import java.lang.reflect.Method;

public class RuntimeTest {
    public static void main(String[] args) throws Exception {
        ExecutionContext ctx = new ExecutionContext();

        BeginPlayNode beginPlayNode = new BeginPlayNode("0001", "BeginPlay");
        beginPlayNode.setNextExec("0002");
        ctx.addExecNode(beginPlayNode);

        Method method1 = CommonLib.class.getMethod("Print", INPUT.class);
        FuncExecNode funcExecNode = new FuncExecNode("0002", "FuncExec", method1);
        funcExecNode.setInputParam("Obj", new NodeOutputSource<>("0003", "Sum"));
        ctx.addExecNode(funcExecNode);

        Method method2 = MathLib.class.getMethod("AddInteger", INPUT.class, INPUT.class, OUTPUT.class);
        FuncPureNode funcPureNode = new FuncPureNode("0003", "FuncPure", method2);
        funcPureNode.setInputParam("Num1", new LiteralValueSource<>(5));
        funcPureNode.setInputParam("Num2", new LiteralValueSource<>(10));
        funcPureNode.setOutputParam("Sum", new OUTPUT<Integer>());
        ctx.addPureNode(funcPureNode);

        ctx.run("0001");
    }
}
