package com.zsb.bluex.core.runtime.param;

import com.zsb.bluex.core.runtime.ExecutionContext;
import lombok.Getter;

@Getter
public class LiteralValueSource<T> implements ParamSource<T> {
    private final T value;

    public LiteralValueSource(T value) {
        this.value = value;
    }

    @Override
    public T getValue(ExecutionContext ctx) {
        return value;
    }
}
