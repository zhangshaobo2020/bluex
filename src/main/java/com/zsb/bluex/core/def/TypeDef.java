package com.zsb.bluex.core.def;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@RequiredArgsConstructor
public class TypeDef implements Serializable {
    // 简短类型名，例如 Integer、String、MyType
    private String name;
    // 完整类名，例如 java.lang.Integer、java.util.List、com.xxx.MyType
    private String qualifiedName;
    // 是否是原始/基础类型，例如 int, String, float
    private boolean primitive = true;
    // 是否是List泛型包装类型
    private boolean list = false;
    // 是否是Map泛型包装类型
    private boolean map = false;
    // Map 或 List 的子类型
    private List<TypeDef> generics = new ArrayList<>();
    // 自定义类型下的字段
    private Map<String, TypeDef> fields = new HashMap<>();
}
