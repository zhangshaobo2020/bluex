package com.zsb.bluex.core.launch;

import com.zsb.bluex.core.groovy.GroovyRegistrar;
import com.zsb.bluex.core.groovy.GroovyScriptLoader;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class BluexInitializer implements ApplicationRunner {

    @Resource
    private MetaHolder metaHolder;
    @Resource
    private CustomPathConfig customPathConfig;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 解析默认的内置Java类型
        metaHolder.process_Primitive();
        // 扫描自定义的@BluexEnum
        metaHolder.process_Bluex_Enum(customPathConfig.getEnumScanPaths());
        // 扫描自定义的@BluexType
        metaHolder.process_Bluex_Class(customPathConfig.getClassScanPaths());
        // 解析默认的流程控制节点
        metaHolder.process_Control();
        // 扫描自定义的@BluexFunctionLib
        metaHolder.process_Bluex_Function(customPathConfig.getFunctionScanPaths());
        // 解析默认的流程控制节点
        metaHolder.process_Delegate();

        // 最后加载Groovy类
        String script = "package com.zsb.bluex.core.groovy.scripts\n" +
                "\n" +
                "import com.zsb.bluex.core.anno.BluexClass\n" +
                "import lombok.Data\n" +
                "import lombok.NoArgsConstructor\n" +
                "\n" +
                "@BluexClass\n" +
                "@Data\n" +
                "@NoArgsConstructor\n" +
                "class Person {\n" +
                "    String name\n" +
                "    Integer age\n" +
                "}\n";

        GroovyScriptLoader loader = new GroovyScriptLoader();
        Class<?> clazz = loader.loadScript(script);

        // 注册到 MetaHolder
        GroovyRegistrar.register(clazz);

        // 假设 Groovy 脚本中有 Person、Weather、GroovyLib
        System.out.println("Classes: " + MetaHolder.CLASS_DEFINITION.keySet());
        System.out.println("Enums: " + MetaHolder.ENUM_DEFINITION.keySet());
        System.out.println("Functions: " + MetaHolder.FUNCTION_DEFINITION.keySet());
    }
}
