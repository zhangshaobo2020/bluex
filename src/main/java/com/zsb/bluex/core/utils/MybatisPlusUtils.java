package com.zsb.bluex.core.utils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.lang.reflect.Field;

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
}
