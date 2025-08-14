package com.zsb.bluex.core.def;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ControlDef implements Serializable {
    // 控制节点名称，如 Branch、While、ForLoop
    private String name;
    // "Control" + 控制节点名称（如 Control.Branch）
    private String qualifiedName;
    // 输入控制引脚
    private List<ParamDef> inputExecDefs = new ArrayList<>();
    // 输出控制引脚
    private List<ParamDef> outputExecDefs = new ArrayList<>();
    // 输入数据引脚
    private List<ParamDef> inputParamDefs = new ArrayList<>();
    // 输出数据引脚
    private List<ParamDef> outputParamDefs = new ArrayList<>();
    // 分类
    private String category;
    // 控制节点的详细描述
    private String description;
    // 展示的名称
    private String displayName;
    // 控制节点必定为可执行函数
    private boolean executable = true;
    // 类型签名(用以确定唯一性)
    private String signature;
    // 是否是事件委托
    private boolean delegate = false;
}
