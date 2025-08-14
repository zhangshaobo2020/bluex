package com.zsb.bluex.core.graph;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsb.bluex.core.def.ControlDef;
import com.zsb.bluex.core.def.FunctionDef;
import com.zsb.bluex.core.def.ParamDef;
import com.zsb.bluex.core.launch.MetaHolder;
import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.BaseNode;
import com.zsb.bluex.core.runtime.node.impl.*;
import com.zsb.bluex.core.runtime.param.LiteralValueSource;
import com.zsb.bluex.core.runtime.param.NodeOutputSource;
import com.zsb.bluex.core.runtime.param.ParamSource;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Slf4j
@Data
@NoArgsConstructor
public class GraphView implements Serializable {
    private List<GraphNode> nodes;
    private List<GraphConnection> connections;

    public ExecutionContext buildExecCtx() throws Exception {
        ExecutionContext ctx = new ExecutionContext();

        for (GraphNode node : nodes) {
            if (node.isExecutable()) {
                if (node.getQualifiedName().startsWith("CONTROL:")) {
                    switch (node.getQualifiedName()) {
                        // 匹配 Branch
                        case "CONTROL:Branch": {
                            BranchNode branchNode = new BranchNode(node.getId());
                            // 查找Cond引脚是否有进行连接
                            String nodePinMapping = findSourceParamNodeAndPin(node, "Cond");
                            if (StringUtils.isBlank(nodePinMapping)) {
                                Boolean cond = findForCurrentPinValue(node, "Cond", Boolean.class);
                                branchNode.condition = new LiteralValueSource<>(cond);
                            } else {
                                String[] split = nodePinMapping.split("-");
                                branchNode.condition = new NodeOutputSource<>(split[0], split[1]);
                            }
                            branchNode.trueExec = findForNextExecNode(node, "True");
                            branchNode.falseExec = findForNextExecNode(node, "False");
                            ctx.addExecNode(branchNode);
                            break;
                        }
                        // 匹配 While
                        case "CONTROL:While": {
                            WhileNode whileNode = new WhileNode(node.getId());
                            // 查找Cond引脚是否有进行连接
                            String nodePinMapping = findSourceParamNodeAndPin(node, "Cond");
                            if (StringUtils.isBlank(nodePinMapping)) {
                                Boolean cond = findForCurrentPinValue(node, "Cond", Boolean.class);
                                whileNode.condition = new LiteralValueSource<>(cond);
                            } else {
                                String[] split = nodePinMapping.split("-");
                                whileNode.condition = new NodeOutputSource<>(split[0], split[1]);
                                log.debug("While节点使用NodeOutputSource");
                            }
                            whileNode.loopBodyExec = findForNextExecNode(node, "LoopBody");
                            whileNode.completedExec = findForNextExecNode(node, "Completed");
                            ctx.addExecNode(whileNode);
                            break;
                        }
                        // 匹配 ForLoop
                        case "CONTROL:ForLoop": {
                            ForLoopNode forLoopNode = new ForLoopNode(node.getId());
                            ParamSource<Integer> fromPin;
                            // 查找From引脚是否有进行连接
                            String fromMapping = findSourceParamNodeAndPin(node, "From");
                            if (StringUtils.isBlank(fromMapping)) {
                                Integer from = findForCurrentPinValue(node, "From", Integer.class);
                                fromPin = new LiteralValueSource<>(from);
                            } else {
                                String[] split = fromMapping.split("-");
                                fromPin = new NodeOutputSource<>(split[0], split[1]);
                                log.debug("ForLoop节点使用NodeOutputSource");
                            }
                            ParamSource<Integer> toPin;
                            // 查找To引脚是否有进行连接
                            String toMapping = findSourceParamNodeAndPin(node, "To");
                            if (StringUtils.isBlank(toMapping)) {
                                Integer to = findForCurrentPinValue(node, "To", Integer.class);
                                toPin = new LiteralValueSource<>(to);
                            } else {
                                String[] split = toMapping.split("-");
                                toPin = new NodeOutputSource<>(split[0], split[1]);
                            }
                            forLoopNode.setRange(fromPin, toPin);
                            forLoopNode.loopBodyExec = findForNextExecNode(node, "LoopBody");
                            forLoopNode.completedExec = findForNextExecNode(node, "Completed");
                            ctx.addExecNode(forLoopNode);
                            break;
                        }
                        // 匹配 Delay
                        case "CONTROL:Delay": {
                            DelayNode delayNode = new DelayNode(node.getId());
                            // 查找Delay(ms)引脚是否有进行连接
                            String nodePinMapping = findSourceParamNodeAndPin(node, "Delay(ms)");
                            if (StringUtils.isBlank(nodePinMapping)) {
                                Long delayMillis = findForCurrentPinValue(node, "Delay(ms)", Long.class);
                                delayNode.delayMillis = new LiteralValueSource<>(delayMillis);
                            } else {
                                String[] split = nodePinMapping.split("-");
                                delayNode.delayMillis = new NodeOutputSource<>(split[0], split[1]);
                            }
                            delayNode.nextExec = findForNextExecNode(node, "Exec");
                            ctx.addExecNode(delayNode);
                            break;
                        }
                        // 匹配 Delay
                        case "CONTROL:FileSystemListener": {
                            DelegateNode delayNode = new DelegateNode(node.getId());
                            delayNode.nextExec = findForNextExecNode(node, "Exec");
                            ctx.addExecNode(delayNode);
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                } else if (node.getQualifiedName().startsWith("DELEGATE:")) {
                    // 说明是事件委托节点
                    DelegateNode delegateNode = new DelegateNode(node.getId());
                    processNodeParams(delegateNode, node);
                    delegateNode.nextExec = findForNextExecNode(node, "Exec");
                    ctx.addExecNode(delegateNode);
                } else if (node.getQualifiedName().startsWith("FUNCTION:")) {
                    // 说明是FuncExecNode
                    Method method = matchJavaMethod(node.getQualifiedName());
                    FuncExecNode execNode = new FuncExecNode(node.getId(), method);
                    processNodeParams(execNode, node);

                    // 尝试查找下一个Exec节点
                    execNode.nextExec = findForNextExecNode(node, "Exec");
                    ctx.addExecNode(execNode);
                }
                // TODO: 实现GENERATED逻辑
            } else {
                if (node.getQualifiedName().startsWith("FUNCTION:")) {
                    // 说明是FuncPureNode
                    Method method = matchJavaMethod(node.getQualifiedName());
                    FuncPureNode pureNode = new FuncPureNode(node.getId(), method);
                    processNodeParams(pureNode, node);
                    ctx.addPureNode(pureNode);
                }
                // TODO: 实现GENERATED逻辑
            }
        }
        ctx.initStartupNode();
        return ctx;
    }

    /**
     * @param baseNode 实际节点
     * @param node     节点信息
     * @throws Exception 可能发生的异常
     */
    private void processNodeParams(BaseNode baseNode, GraphNode node) throws Exception {
        for (GraphParamPin graphParamPin : node.getParamInputs().values()) {
            String paramName = graphParamPin.getName();

            ParamSource<?> paramPin;
            // 查找参数引脚是否有进行连接
            String paramMapping = findSourceParamNodeAndPin(node, paramName);
            if (StringUtils.isBlank(paramMapping)) {
                // 这里需要根据参数类型去获取实际的实参类型
                String qualifiedName = node.getQualifiedName();
                FunctionDef functionDef = MetaHolder.FUNCTION_DEFINITION.get(qualifiedName);
                if (functionDef != null) {
                    ParamDef paramDef = functionDef.getInputParamDefs()
                            .stream().filter(def -> def.getName().equals(paramName))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException(qualifiedName + "的函数中参数" + paramName + "不存在!"));
                    // 理论上LiteralValueSource肯定是基本数据类型，不是泛型等
                    String className = paramDef.getTypeDef().getQualifiedName().replace("TYPE:", "");
                    paramPin = new LiteralValueSource<>(findForCurrentPinValue(node, paramName, Class.forName(className)));
                } else {
                    // 尝试去匹配事件委托节点
                    ControlDef controlDef = MetaHolder.CONTROL_DEFINITION.get(qualifiedName);
                    if (controlDef != null) {
                        ParamDef paramDef = controlDef.getInputParamDefs()
                                .stream().filter(def -> def.getName().equals(paramName))
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException(qualifiedName + "的委托中参数" + paramName + "不存在!"));
                        // 理论上LiteralValueSource肯定是基本数据类型，不是泛型等
                        String className = paramDef.getTypeDef().getQualifiedName().replace("TYPE:", "");
                        paramPin = new LiteralValueSource<>(findForCurrentPinValue(node, paramName, Class.forName(className)));
                    } else {
                        throw new RuntimeException(qualifiedName + "的函数委托或不存在!");
                    }
                }
            } else {
                String[] split = paramMapping.split("-");
                paramPin = new NodeOutputSource<>(split[0], split[1]);
            }
            baseNode.setInputParam(paramName, paramPin);
        }

        for (GraphParamPin graphParamPin : node.getParamOutputs().values()) {
            String paramName = graphParamPin.getName();
            baseNode.setOutput(paramName, new OUTPUT<>());
        }
    }

    /**
     * @param functionQualifiedName 函数的完全限定名称
     * @return 返回对应的 Method
     * @throws Exception 可能发生的一场
     */
    private Method matchJavaMethod(String functionQualifiedName) throws Exception {
        functionQualifiedName = functionQualifiedName.replace("FUNCTION:", "");
        int lastDot = functionQualifiedName.lastIndexOf(':');
        String className = functionQualifiedName.substring(0, lastDot);
        String methodName = functionQualifiedName.substring(lastDot + 1);
        Class<?> clazz = Class.forName(className);
        for (Method method : clazz.getMethods()) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        throw new RuntimeException(className + "." + methodName + "的方法不存在!");
    }

    /**
     * @param nodeId 节点的 id
     * @return 找到的节点
     */
    private GraphNode findNode(String nodeId) {
        return nodes.stream()
                .filter(node -> nodeId.equals(node.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("未找到节点: " + nodeId));
    }

    /**
     * @param currentNode    当前节点
     * @param outputExecName 当前节点 output exec 的名称
     * @return 下一个执行节点 的 ID
     */
    private String findForNextExecNode(GraphNode currentNode, String outputExecName) {
        // 自身的nodeId
        String nodeId = currentNode.getId();
        String outputExecId = findOutputExecId(currentNode, outputExecName);
        if (StringUtils.isBlank(outputExecId)) return null;
        for (GraphConnection connection : connections) {
            if (nodeId.equals(connection.getSource()) && outputExecId.equals(connection.getSourceOutput())) {
                return connection.getTarget();
            }
        }
        return null;
    }

    /**
     * @param currentNode    当前节点
     * @param outputExecName 当前节点的 outputExec 名称
     * @return 找到当前节点的 outputExec id
     */
    private static String findOutputExecId(GraphNode currentNode, String outputExecName) {
        // 自身的outputExecId
        String outputExecId = null;
        // 进行匹配
        for (GraphExecPin graphExecPin : currentNode.getExecOutputs().values()) {
            if (outputExecName.equals(graphExecPin.getName())) {
                outputExecId = graphExecPin.getId();
            }
        }
        return outputExecId;
    }

    /**
     * @param currentNode    当前节点
     * @param inputParamName 当前节点的 inputParam 名称
     * @return 找到当前节点的 inputParam id
     */
    private static String findInputParamId(GraphNode currentNode, String inputParamName) {
        // 自身的inputParamId
        String inputParamId = null;
        // 进行匹配
        for (GraphParamPin graphParamPin : currentNode.getParamInputs().values()) {
            if (inputParamName.equals(graphParamPin.getName())) {
                inputParamId = graphParamPin.getId();
            }
        }
        return inputParamId;
    }

    /**
     * @param currentNode    当前节点
     * @param inputParamName 当前节点 input param 的名称
     * @return 来源节点的 nodeId, 以及来源节点的 outputParamName
     */
    private String findSourceParamNodeAndPin(GraphNode currentNode, String inputParamName) {
        // 自身的nodeId
        String nodeId = currentNode.getId();
        String inputParamId = findInputParamId(currentNode, inputParamName);
        if (StringUtils.isBlank(inputParamId)) return null;

        for (GraphConnection connection : connections) {
            if (connection.getTarget().equals(nodeId) && connection.getTargetInput().equals(inputParamId)) {
                String sourceNodeId = connection.getSource();
                String sourceNodeOutputId = connection.getSourceOutput();
                // 要根据nodeId和nodeOutputId找到对应的参数名
                GraphNode sourceNode = findNode(sourceNodeId);
                if (sourceNode != null) {
                    for (GraphParamPin paramPin : sourceNode.getParamOutputs().values()) {
                        if (sourceNodeOutputId.equals(paramPin.getId())) {
                            return connection.getSource() + "-" + paramPin.getName();
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * 根据当前节点的 input param 名称获取值，并转换为指定类型。
     *
     * @param currentNode    当前节点
     * @param inputParamName 当前节点 input param 的名称
     * @param targetType     目标类型的 Class
     * @param <T>            返回值类型
     * @return 转换后的值，如果无法转换则返回 null
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public <T> T findForCurrentPinValue(GraphNode currentNode, String inputParamName, Class<T> targetType) throws Exception {
        String paramInputId = findInputParamId(currentNode, inputParamName);
        if (StringUtils.isBlank(paramInputId)) {
            return null;
        }
        Object rawValue = currentNode.getParamInputs().get(paramInputId).getValue();
        if (rawValue == null) {
            return null;
        }
        // 已经是目标类型，直接返回
        if (targetType.isInstance(rawValue)) {
            return (T) rawValue;
        }
        if (targetType == String.class) {
            return (T) String.valueOf(rawValue);
        }
        if (Number.class.isAssignableFrom(targetType)) {
            // 转换为数字
            String strValue = rawValue.toString();
            if (targetType == Integer.class) return (T) Integer.valueOf(strValue);
            if (targetType == Long.class) return (T) Long.valueOf(strValue);
            if (targetType == Double.class) return (T) Double.valueOf(strValue);
            if (targetType == Float.class) return (T) Float.valueOf(strValue);
            if (targetType == Short.class) return (T) Short.valueOf(strValue);
            if (targetType == Byte.class) return (T) Byte.valueOf(strValue);
        }
        if (targetType == Boolean.class) {
            return (T) Boolean.valueOf(rawValue.toString());
        }
        if (targetType == Character.class) {
            return (T) Character.valueOf(rawValue.toString().toCharArray()[0]);
        }
        if (targetType == LocalDateTime.class) {
            String strValue = rawValue.toString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return (T) LocalDateTime.parse(strValue, formatter);
        }
        if (targetType == Date.class) {
            String strValue = rawValue.toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return (T) sdf.parse(strValue);
        }
        if (targetType.isEnum()) {
            return (T) Enum.valueOf((Class<Enum>) targetType.asSubclass(Enum.class), rawValue.toString());
        }
        // 其他复杂类型：尝试用 JSON 转换
        return new ObjectMapper().convertValue(rawValue, targetType);
    }
}
