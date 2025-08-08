package com.zsb.bluex.core.resolver;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.def.FunctionDef;
import com.zsb.bluex.core.def.ParamDef;
import com.zsb.bluex.core.def.TypeDef;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class FunctionResolver {

    public static List<FunctionDef> resolveFromClass(Class<?> clazz) {
        List<FunctionDef> functions = new ArrayList<>();

        BluexFunctionLib bfl = clazz.getAnnotation(BluexFunctionLib.class);
        String functionLibCategory = bfl.category();
        if (StringUtils.isEmpty(functionLibCategory)) {
            functionLibCategory = clazz.getSimpleName();
        }

        for (Method method : clazz.getDeclaredMethods()) {
            if (!Modifier.isStatic(method.getModifiers())) continue;
            BluexFunction bf = method.getAnnotation(BluexFunction.class);
            if (bf == null) continue;

            FunctionDef func = new FunctionDef();
            func.setName(method.getName());
            func.setQualifiedName(clazz.getName() + "." + method.getName());
            func.setCategory(functionLibCategory + "|" + method.getName());
            func.setDisplayName(bf.displayName());
            func.setDescription(bf.description());

            func.setExecutable(bf.executable());
            func.setLatent(bf.latent());
            func.setUnsafe(bf.unsafe());

            Parameter[] parameters = method.getParameters();
            for (Parameter param : parameters) {
                if (param.getType() == INPUT.class) {
                    // 说明是INPUT
                    ParamDef paramDef = new ParamDef();
                    paramDef.setName(param.getName());
                    paramDef.setTypeDef(resolveParamType(param.getParameterizedType()));
                    func.getInputParamDefs().add(paramDef);
                } else if (param.getType() == OUTPUT.class) {
                    // 说明是OUTPUT
                    ParamDef paramDef = new ParamDef();
                    paramDef.setName(param.getName());
                    paramDef.setTypeDef(resolveParamType(param.getParameterizedType()));
                    func.getOutputParamDefs().add(paramDef);
                } else {
                    throw new IllegalArgumentException("请使用INPUT<T>或OUTPUT<T>");
                }
            }
            functions.add(func);
        }
        return functions;
    }

    private static TypeDef resolveParamType(Type paramType) {
        // 期待为:INPUT<T>或OUTPUT<T>
        if (paramType instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) paramType;
            Type actualType = pt.getActualTypeArguments()[0];
            return TypeResolver.resolveType(actualType);
        }
        throw new IllegalArgumentException("请使用INPUT<T>或OUTPUT<T>");
    }
}

