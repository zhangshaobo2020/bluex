package com.zsb.bluex.core.def;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class FunctionDef implements Serializable {
    // 函数名，如 Test
    private String name;
    // 完整类名 + 方法名（如 com.xxx.yyy.Math.Test）
    private String qualifiedName;
    // 所有输入参数定义
    private List<ParamDef> inputParamDefs = new ArrayList<>();
    // 所有输出参数定义
    private List<ParamDef> outputParamDefs = new ArrayList<>();
    // 函数的详细描述
    private String description;
    // 展示的名称
    private String displayName;
    // 是否是纯函数
    private boolean pure = false;
    // 是否是延时函数
    private boolean latent = false;
    // 是否是unsafe函数
    private boolean unsafe = false;
}
