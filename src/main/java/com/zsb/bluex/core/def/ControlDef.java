package com.zsb.bluex.core.def;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class ControlDef implements Serializable {
    // 控制节点名称，如 Branch、While、ForLoop
    private String name;
    // "Control" + 控制节点名称（如 Control.Branch）
    private String qualifiedName;
    // 输入控制引脚
    private List<String> inputExecPins = new ArrayList<>();
    // 输出控制引脚
    private List<String> outputExecPins = new ArrayList<>();
    // 输入数据引脚
    private List<ParamDef> inputParamPins = new ArrayList<>();
    // 输出数据引脚
    private List<ParamDef> outputParamPins = new ArrayList<>();
    // 控制节点的详细描述
    private String description;
    // 展示的名称
    private String displayName;
}
