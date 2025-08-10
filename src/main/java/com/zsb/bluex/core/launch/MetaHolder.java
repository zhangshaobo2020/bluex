package com.zsb.bluex.core.launch;

import com.zsb.bluex.core.anno.BluexEnum;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.anno.BluexType;
import com.zsb.bluex.core.def.ControlDef;
import com.zsb.bluex.core.def.FunctionDef;
import com.zsb.bluex.core.def.TypeDef;
import com.zsb.bluex.core.resolver.ControlResolver;
import com.zsb.bluex.core.resolver.FunctionResolver;
import com.zsb.bluex.core.resolver.TypeResolver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class MetaHolder {

    @Data
    @AllArgsConstructor
    public static class MetaInfo implements Serializable {
        private Map<String, TypeDef> typeDef;
        private Map<String, ControlDef> controlDef;
        private Map<String, FunctionDef> functionDef;
    }

    public static final Map<String, TypeDef> PRIMITIVE_TYPE_DEFINITION = new LinkedHashMap<>();
    public static final Map<String, TypeDef> TYPE_DEFINITION = new LinkedHashMap<>();
    public static final Map<String, FunctionDef> FUNCTION_DEFINITION = new LinkedHashMap<>();
    public static final Map<String, ControlDef> CONTROL_DEFINITION = new LinkedHashMap<>();

    public void processDefaultType() {
        TypeResolver.PRIMITIVE_CLASSES.forEach(clazz -> {
            TypeDef def = TypeResolver.resolveType(clazz);
            TYPE_DEFINITION.put(clazz.getName(), def);
            PRIMITIVE_TYPE_DEFINITION.put(clazz.getName(), def);
        });
    }

    public void processDefaultControl() {
        Map<String, ControlDef> map = ControlResolver.processDefaultControl();
        CONTROL_DEFINITION.putAll(map);
    }

    public void processBluexEnum(List<String> basePackages) throws Exception {
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);

        scanner.addIncludeFilter(new AnnotationTypeFilter(BluexEnum.class));
        loopProcessEnumAndType(basePackages, scanner);
    }

    public void processBluexType(List<String> basePackages) throws Exception {
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);

        scanner.addIncludeFilter(new AnnotationTypeFilter(BluexType.class));
        loopProcessEnumAndType(basePackages, scanner);
    }

    private void loopProcessEnumAndType(List<String> basePackages, ClassPathScanningCandidateComponentProvider scanner) throws ClassNotFoundException {
        for (String basePackage : basePackages) {
            for (BeanDefinition bd : scanner.findCandidateComponents(basePackage)) {
                Class<?> clazz = Class.forName(bd.getBeanClassName());
                TypeDef def = TypeResolver.resolveType(clazz);
                TYPE_DEFINITION.put(clazz.getName(), def);
            }
        }
    }

    public void processBluexFunction(List<String> basePackages) throws Exception {
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);

        scanner.addIncludeFilter(new AnnotationTypeFilter(BluexFunctionLib.class));

        List<FunctionDef> definitionList = new ArrayList<>();
        for (String basePackage : basePackages) {
            for (BeanDefinition bd : scanner.findCandidateComponents(basePackage)) {
                Class<?> clazz = Class.forName(bd.getBeanClassName());
                definitionList.addAll(FunctionResolver.resolveFromClass(clazz));
            }
        }
        definitionList.forEach(definition -> {
            // PUT之前校验是否有同名函数
            if (FUNCTION_DEFINITION.containsKey(definition.getQualifiedName())) {
                throw new IllegalArgumentException("检测到同名函数:" + definition.getQualifiedName());
            }
            FUNCTION_DEFINITION.put(definition.getQualifiedName(), definition);
        });
    }
}
