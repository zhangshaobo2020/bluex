package com.zsb.bluex.core.graph;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class GraphConnection implements Serializable {
    // 唯一ID
    private String id;
    // 开始节点ID
    private String source;
    // 开始节点Pin的ID
    private String sourceOutput;
    // 结束节点ID
    private String target;
    // 结束节点Pin的ID
    private String targetInput;
}
