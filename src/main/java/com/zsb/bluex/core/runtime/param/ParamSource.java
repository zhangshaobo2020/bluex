package com.zsb.bluex.core.runtime.param;

import com.zsb.bluex.core.runtime.ExecutionContext;

public interface ParamSource<T> {
    T getValue(ExecutionContext ctx) throws Exception;
}
