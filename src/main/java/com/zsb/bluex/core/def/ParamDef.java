package com.zsb.bluex.core.def;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ParamDef implements Serializable {
    // 形参名称（例如 Num）
    private String name;
    // 参数类型结构
    private TypeDef typeDef;

    public ParamDef(String name, TypeDef typeDef) {
        this.name = name;
        this.typeDef = typeDef;
    }
}
