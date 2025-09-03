package com.zsb.bluex.core.resolver;

import com.zsb.bluex.core.script.ScriptRegistrar;

public class ClassResolver {

    public static Class<?> forClass(String className) throws ClassNotFoundException {
        for (Class<?> value : ScriptRegistrar.CLASS_CACHE.values()) {
            if (value.getName().equals(className)) {
                return value;
            }
        }
        // 默认走系统加载
        return Class.forName(className);
    }
}
