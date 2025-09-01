package com.zsb.bluex.core.utils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MybatisPlusUtils {
    public static <T1, T2> QueryWrapper<T1> getQueryWrapper(T2 searchEntity) {
        QueryWrapper<T1> wrapper = new QueryWrapper<>();
        for (Field field : searchEntity.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object value = field.get(searchEntity);
                if (null != value) {
                    String fieldName = field.getName();
                    String columnName = fieldName;
                    TableField tableField = (TableField) field.getAnnotation(TableField.class);
                    if (tableField != null) {
                        columnName = tableField.value();
                    }
                    if (value instanceof String) {
                        String valueText = (String) value;
                        if (!valueText.isEmpty()) {
                            if (valueText.contains("%")) {
                                wrapper.like(columnName, valueText);
                            } else {
                                wrapper.eq(columnName, valueText);
                            }
                        }
                    } else if (fieldName.endsWith("Start")) {
                        wrapper.ge(columnName, value);
                    } else if (fieldName.endsWith("End")) {
                        wrapper.le(columnName, value);
                    } else {
                        wrapper.eq(columnName, value);
                    }
                }
            } catch (IllegalAccessException ignored) {
            }
        }
        return wrapper;
    }

    /**
     * 校验 Mybatis-Plus 实体类：
     * - 如果继承了 Model：
     *   - 必须且只能有一个 @TableId 字段
     *   - 且该字段必须是 String 类型
     *   - 校验通过返回 true，否则抛出异常
     *
     * - 如果没有继承 Model：
     *   - 直接返回 false
     *
     * @param clazz 待校验的实体类
     * @return 是否是合法的 Mybatis-Plus 实体
     */
    public static boolean validateMybatisPlusEntity(Class<?> clazz) {
        // 1. 是否继承 Model
        if (!Model.class.isAssignableFrom(clazz)) {
            return false;
        }

        // 2. 查找所有带 @TableId 的字段
        Field[] fields = clazz.getDeclaredFields();
        List<Field> tableIdFields = Arrays.stream(fields)
                .filter(f -> f.isAnnotationPresent(TableId.class))
                .collect(Collectors.toList());

        if (tableIdFields.size() != 1) {
            throw new IllegalArgumentException(clazz.getName() + " 必须且只能有一个 @TableId 字段");
        }

        // 3. 校验字段类型必须是 String
        Field tableIdField = tableIdFields.get(0);
        if (!tableIdField.getType().equals(String.class)) {
            throw new IllegalArgumentException(clazz.getName() + " 的 @TableId 字段必须是 String 类型");
        }

        return true;
    }
}
