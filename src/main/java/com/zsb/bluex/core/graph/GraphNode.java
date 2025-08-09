package com.zsb.bluex.core.graph;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class GraphNode implements Serializable {
    // 唯一ID
    private String id;
    // 横坐标
    private Integer x;
    // 纵坐标
    private Integer y;
    // 节点全限定名
    private String qualifiedName;
    // 类型签名(用以确定唯一性)
    private String signature;
    // 是否是可执行函数/纯函数
    private boolean executable;

    private Map<String, GraphExecPin> execInputs = new HashMap<>();
    private Map<String, GraphExecPin> execOutputs = new HashMap<>();

    private Map<String, GraphParamPin> paramInputs = new HashMap<>();
    private Map<String, GraphParamPin> paramOutputs = new HashMap<>();
}
