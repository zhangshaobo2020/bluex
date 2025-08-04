package com.zsb.bluex.core.runtime;

import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.core.runtime.node.ExecNode;
import com.zsb.bluex.core.runtime.node.PureNode;
import com.zsb.bluex.core.runtime.param.ParamSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@Data
@SuppressWarnings("unchecked")
public class ExecutionContext {

    private final Map<String, ExecNode> execNodes = new LinkedHashMap<>();
    private final Map<String, PureNode> pureNodes = new LinkedHashMap<>();

    private final Set<String> breakRequests = new HashSet<>();

    public void addExecNode(ExecNode node) {
        execNodes.put(node.getId(), node);
        node.setCtx(this);
    }

    public void addPureNode(PureNode node) {
        pureNodes.put(node.getId(), node);
        node.setCtx(this);
    }

    public <T> T getNodeOutputParamValue(String nodeId, String outputParamName) {
        PureNode pure = pureNodes.get(nodeId);
        if (pure != null) {
            return (T) pure.evaluate(outputParamName, this);
        }
        // 为了ForLoop控制节点的Index输出
        ExecNode execNode = execNodes.get(nodeId);
        if (execNode != null) {
            return (T) execNode.getOutputParam(outputParamName).value;
        }
        return null;
    }

    public void run(String startId) {
        String current = startId;
        while (current != null) {
            ExecNode node = execNodes.get(current);
            log.debug("当前执行的节点是:{}", node.getName());
            current = node.execute(this);
        }
    }

    public static void prepareArgs(ExecutionContext ctx,
                                   Map<String, ParamSource<?>> inputParams,
                                   Map<String, OUTPUT<?>> outputs,
                                   Method method) throws Exception {
        Parameter[] parameters = method.getParameters();
        Object[] args = new Object[inputParams.size() + outputs.size()];
        for (int i = 0; i < parameters.length; i++) {
            Parameter param = parameters[i];
            String paramName = param.getName();
            if (param.getType() == INPUT.class || param.getType() == OUTPUT.class) {
                if (inputParams.containsKey(paramName)) {
                    args[i] = new INPUT<>(inputParams.get(paramName).getValue(ctx));
                } else if (outputs.containsKey(paramName)) {
                    args[i] = outputs.get(paramName);
                } else {
                    String msg = String.format("未在方法{%s}中找到{%s}参数", method.getName(), paramName);
                    throw new RuntimeException(msg);
                }
            } else {
                throw new IllegalArgumentException("请使用INPUT<T>或OUTPUT<T>");
            }
        }
        method.invoke(null, args);
    }
}
