package com.zsb.bluex.core.graph;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class GraphExecPin implements Serializable {
    // 唯一ID
    private String id;
    // 名称
    private String name;
}
