package com.zsb.bluex.core.runtime.node;

import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.param.ParamSource;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@NoArgsConstructor
public abstract class BaseNode implements Serializable {
    public String id;
    public String name;

    public Map<String, ParamSource<?>> inputParams = new LinkedHashMap<>();
    public Map<String, OUTPUT<?>> outputs = new LinkedHashMap<>();

    public void setInputParam(String name, ParamSource<?> source) {
        inputParams.put(name, source);
    }

    public <T> void setOutput(String name, OUTPUT<T> output) {
        outputs.put(name, output);
    }

    public BaseNode(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public ExecutionContext ctx;
}
