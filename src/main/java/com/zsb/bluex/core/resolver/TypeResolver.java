package com.zsb.bluex.core.resolver;

import com.zsb.bluex.core.anno.BluexClass;
import com.zsb.bluex.core.def.TypeDef;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class TypeResolver {

    private static final Map<Type, TypeDef> TYPE_CACHE = new HashMap<>();

    public static final Set<Class<?>> PRIMITIVE_CLASSES = new HashSet<>();

    static {
        PRIMITIVE_CLASSES.add(Object.class);
        PRIMITIVE_CLASSES.add(Class.class);

        PRIMITIVE_CLASSES.add(Byte.class);
        PRIMITIVE_CLASSES.add(Short.class);
        PRIMITIVE_CLASSES.add(Integer.class);
        PRIMITIVE_CLASSES.add(Long.class);

        PRIMITIVE_CLASSES.add(Float.class);
        PRIMITIVE_CLASSES.add(Double.class);
        PRIMITIVE_CLASSES.add(Boolean.class);
        PRIMITIVE_CLASSES.add(Character.class);

        PRIMITIVE_CLASSES.add(String.class);
        PRIMITIVE_CLASSES.add(BigDecimal.class);
        PRIMITIVE_CLASSES.add(Date.class);
        PRIMITIVE_CLASSES.add(LocalDateTime.class);

        PRIMITIVE_CLASSES.add(File.class);
        PRIMITIVE_CLASSES.add(HttpServletRequest.class);
        PRIMITIVE_CLASSES.add(HttpServletResponse.class);
    }

    public static TypeDef resolveType(Type genericType) {
        // 判断缓存中有没有，避免递归死循环
        if (TYPE_CACHE.containsKey(genericType)) {
            return TYPE_CACHE.get(genericType);
        }

        TypeDef def = new TypeDef();

        TYPE_CACHE.put(genericType, def); // 提前放入缓存

        if (genericType instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) genericType;
            Class<?> rawType = (Class<?>) pt.getRawType();
            def.setName(rawType.getSimpleName());
            def.setQualifiedName("TYPE:" + rawType.getName());

            // 标记为 List 或 Map
            def.setPrimitive(false);
            def.setList(List.class.isAssignableFrom(rawType));
            def.setMap(Map.class.isAssignableFrom(rawType));

            // 解析泛型参数
            for (Type arg : pt.getActualTypeArguments()) {
                def.getGenerics().add(resolveType(arg));
            }

            if (rawType.isAnnotationPresent(BluexClass.class)) {
                def.setFields(resolveFields(rawType));
            }

        } else if (genericType instanceof Class<?>) {
            Class<?> clazz = (Class<?>) genericType;
            def.setName(clazz.getSimpleName());
            def.setQualifiedName("TYPE:" + clazz.getName());

            // 处理枚举
            if (clazz.isEnum()) {
                def.setEnumeration(true);
                // 取枚举常量名
                Object[] enumConstants = clazz.getEnumConstants();
                List<String> enumNames = new ArrayList<>();
                if (enumConstants != null) {
                    for (Object enumConst : enumConstants) {
                        enumNames.add(enumConst.toString());
                    }
                }
                def.setEnumOptions(enumNames);

                def.setPrimitive(false);
                def.setList(false);
                def.setMap(false);
            } else {
                def.setPrimitive(isPrimitive(clazz));
                def.setList(false);
                def.setMap(false);

                if (clazz.isAnnotationPresent(BluexClass.class)) {
                    def.setFields(resolveFields(clazz));
                }
            }
        }
        return def;
    }

    private static boolean isPrimitive(Class<?> clazz) {
        if (clazz.isPrimitive()) {
            throw new IllegalArgumentException("请使用包装类型代替原始类型");
        }
        return PRIMITIVE_CLASSES.contains(clazz);
    }

    private static Map<String, TypeDef> resolveFields(Class<?> clazz) {
        Map<String, TypeDef> fields = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            fields.put(field.getName(), resolveType(field.getGenericType()));
        }
        return fields;
    }
}
