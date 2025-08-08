package com.zsb.bluex.core.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BluexFunctionLib {
    // 蓝图编辑器上展示的分类(以|分隔)
    String category() default "";
}
