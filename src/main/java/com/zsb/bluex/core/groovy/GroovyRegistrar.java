package com.zsb.bluex.core.groovy;

import com.zsb.bluex.core.anno.BluexClass;
import com.zsb.bluex.core.anno.BluexEnum;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.def.FunctionDef;
import com.zsb.bluex.core.launch.MetaHolder;
import com.zsb.bluex.core.resolver.FunctionResolver;

import java.util.List;

public class GroovyRegistrar {

    private static void registerClass(Class<?> clazz) {
        if (clazz.isAnnotationPresent(BluexClass.class)) {
            MetaHolder.registerClass(clazz);
        }
    }

    private static void unregisterClass(Class<?> clazz) {
        if (clazz.isAnnotationPresent(BluexClass.class)) {
            MetaHolder.unregisterClass(clazz);
        }
    }

    private static void registerEnum(Class<?> clazz) {
        if (clazz.isAnnotationPresent(BluexEnum.class)) {
            MetaHolder.registerEnum(clazz);
        }
    }

    private static void unregisterEnum(Class<?> clazz) {
        if (clazz.isAnnotationPresent(BluexEnum.class)) {
            MetaHolder.unregisterEnum(clazz);
        }
    }

    private static void registerFunctions(Class<?> clazz) {
        if (clazz.isAnnotationPresent(BluexFunctionLib.class)) {
            List<FunctionDef> defs = FunctionResolver.resolveFromClass(clazz);
            for (FunctionDef def : defs) {
                if (MetaHolder.FUNCTION_DEFINITION.containsKey(def.getQualifiedName())) {
                    throw new IllegalArgumentException("检测到同名函数:" + def.getQualifiedName());
                }
                MetaHolder.FUNCTION_DEFINITION.put(def.getQualifiedName(), def);
            }
        }
    }

    private static void unregisterFunctions(Class<?> clazz) {
        if (clazz.isAnnotationPresent(BluexFunctionLib.class)) {
            List<FunctionDef> defs = FunctionResolver.resolveFromClass(clazz);
            for (FunctionDef def : defs) {
                MetaHolder.FUNCTION_DEFINITION.remove(def.getQualifiedName());
            }
        }
    }

    public static void register(Class<?> clazz) {
        registerClass(clazz);
        registerEnum(clazz);
        registerFunctions(clazz);
    }

    public static void unregister(Class<?> clazz) {
        unregisterClass(clazz);
        unregisterEnum(clazz);
        unregisterFunctions(clazz);
        // TODO: 卸载Groovy类
    }
}
