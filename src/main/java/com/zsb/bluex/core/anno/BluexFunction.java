package com.zsb.bluex.core.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BluexFunction {
    // 蓝图编辑器上展示的名称
    String displayName() default "";

    // 蓝图编辑器上展示的详细描述
    String description() default "";

    // 是否是可执行函数/纯函数
    boolean executable() default true;

    // 是否是延时函数
    boolean latent() default false;

    // 是否是unsafe函数
    boolean unsafe() default false;
}
