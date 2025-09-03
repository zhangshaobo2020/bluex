package com.zsb.bluex.core.script;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GroovyFieldUtils {

    private static final List<String> GROOVY_INTERNAL_FIELD_NAMES = Arrays.asList(
            "metaClass",
            "__$stMC",
            "$staticClassInfo",
            "$callSiteArray"
    );

    private static final List<String> GROOVY_INTERNAL_FIELD_TYPES = Arrays.asList(
            "groovy.lang.MetaClass",
            "org.codehaus.groovy.reflection.ClassInfo",
            "java.lang.ref.SoftReference"
    );

    public static List<Field> getUserDeclaredFields(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> !isGroovyInternalField(f))
                .collect(Collectors.toList());
    }

    private static boolean isGroovyInternalField(Field field) {
        String name = field.getName();
        String type = field.getType().getName();

        if (GROOVY_INTERNAL_FIELD_NAMES.contains(name)) {
            return true;
        }
        if (GROOVY_INTERNAL_FIELD_TYPES.contains(type)) {
            return true;
        }
        // 兜底规则：Groovy 的内部字段大多以 "__" 或 "$" 开头
        return name.startsWith("__") || name.startsWith("$");
    }
}
