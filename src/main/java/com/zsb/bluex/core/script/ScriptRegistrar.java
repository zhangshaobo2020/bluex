package com.zsb.bluex.core.script;

import com.zsb.bluex.core.anno.BluexClass;
import com.zsb.bluex.core.anno.BluexEnum;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.def.FunctionDef;
import com.zsb.bluex.core.launch.MetaHolder;
import com.zsb.bluex.core.model.entity.BluexScript;
import com.zsb.bluex.core.model.service.BluexScriptService;
import com.zsb.bluex.core.resolver.FunctionResolver;
import groovy.lang.GroovyClassLoader;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class ScriptRegistrar {

    @Resource
    private BluexScriptService bluexScriptService;

    // 每个脚本对应一个独立的 ClassLoader
    public static final Map<String, Class<?>> CLASS_CACHE = new ConcurrentHashMap<>();


    private static void registerClass(Class<?> clazz) {
        if (clazz.isAnnotationPresent(BluexClass.class)) {
            MetaHolder.registerClass(clazz);
        }
        if (clazz.isAnnotationPresent(BluexEnum.class)) {
            MetaHolder.registerEnum(clazz);
        }
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

    private static void unregisterClass(Class<?> clazz) {
        if (clazz.isAnnotationPresent(BluexClass.class)) {
            MetaHolder.unregisterClass(clazz);
        }
        if (clazz.isAnnotationPresent(BluexEnum.class)) {
            MetaHolder.unregisterEnum(clazz);
        }
        if (clazz.isAnnotationPresent(BluexFunctionLib.class)) {
            List<FunctionDef> defs = FunctionResolver.resolveFromClass(clazz);
            for (FunctionDef def : defs) {
                MetaHolder.FUNCTION_DEFINITION.remove(def.getQualifiedName());
            }
        }
    }

    public void register(String scriptNo) throws Exception {
        BluexScript bluexScript = bluexScriptService.getById(scriptNo);
        if (bluexScript == null) {
            throw new RuntimeException("脚本不存在: " + scriptNo);
        }
        String scriptContent = bluexScript.getScriptContent();

        CompilerConfiguration config = new CompilerConfiguration();
        config.setParameters(true);

        GroovyClassLoader loader = new GroovyClassLoader(ClassLoader.getSystemClassLoader(), config);
        Class<?> clazz = loader.parseClass(scriptContent);
        // 注册元数据
        registerClass(clazz);

        // 保存 Class 引用
        CLASS_CACHE.put(scriptNo, clazz);
    }

    public void unregister(String scriptNo) throws Exception {
        BluexScript bluexScript = bluexScriptService.getById(scriptNo);
        if (bluexScript == null) {
            throw new RuntimeException("脚本不存在: " + scriptNo);
        }
        String scriptContent = bluexScript.getScriptContent();

        Class<?> clazz = CLASS_CACHE.remove(scriptNo);
        if (clazz == null) {
            return;
        }
        unregisterClass(clazz);

        // 关键：丢弃 loader 引用，等待 GC 回收整个 ClassLoader 和其中的类
        ((GroovyClassLoader) clazz.getClassLoader()).close();
    }
}
