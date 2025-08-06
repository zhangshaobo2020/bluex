package com.zsb.bluex.core.runtime.node;

import com.zsb.bluex.core.runtime.ExecutionContext;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public abstract class BaseNode implements Serializable {
    public String id;
    public String name;

    public BaseNode(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public ExecutionContext ctx;
}
