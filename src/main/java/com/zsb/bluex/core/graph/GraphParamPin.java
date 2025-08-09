package com.zsb.bluex.core.graph;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class GraphParamPin implements Serializable {
    // 唯一ID
    private String id;
    // 名称
    private String name;
    // 前端Control保存的值(一般为数字类型、布尔类型和字符串)
    private Object value;
}
