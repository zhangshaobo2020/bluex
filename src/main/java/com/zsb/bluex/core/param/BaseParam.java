package com.zsb.bluex.core.param;

/**
 * @param <T> 用来包装参数的装饰器
 */
public abstract class BaseParam<T> {
    public T value;

    public BaseParam() {
    }

    public BaseParam(T value) {
        this.value = value;
    }
}
