package com.zsb.bluex.core.runtime;

import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.core.runtime.connection.PinConnection;
import com.zsb.bluex.core.runtime.node.ExecNode;
import com.zsb.bluex.core.runtime.node.PureNode;
import com.zsb.bluex.core.runtime.param.ParamSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

@Slf4j
@Data
@SuppressWarnings("unchecked")
public class ExecutionContext {

    private final Queue<ExecTask> taskQueue = new LinkedList<>();

    public void schedule(ExecTask task) {
        taskQueue.offer(task);
    }

    public void run() throws Exception {
        while (!taskQueue.isEmpty()) {
            ExecTask task = taskQueue.poll();
            if (task.nodeId == null) {
                log.info("流程执行结束");
                break;
            }
            ExecNode execNode = getExecNode(task.nodeId);
            execNode.execute(this);
        }
    }

    private final Map<String, ExecNode> execNodes = new LinkedHashMap<>();
    private final Map<String, PureNode> pureNodes = new LinkedHashMap<>();

    private final List<PinConnection> connections = new LinkedList<>();

    public void addExecNode(ExecNode node) {
        node.ctx = this;
        execNodes.put(node.id, node);
    }

    public void addPureNode(PureNode node) {
        node.ctx = this;
        pureNodes.put(node.id, node);
    }

    public ExecNode getExecNode(String nodeId) {
        return execNodes.get(nodeId);
    }

    public <T> T getNodeOutputParamValue(String nodeId, String outputParamName) throws Exception {
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
                    String msg = String.format("未在方法%s中找到%s参数", method.getName(), paramName);
                    throw new RuntimeException(msg);
                }
            } else {
                throw new IllegalArgumentException("请使用INPUT<T>或OUTPUT<T>");
            }
        }
        method.invoke(null, args);
    }
}
