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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
            func.setQualifiedName("FUNCTION:" + clazz.getName() + ":" + method.getName());

            String functionCategory;
            if (StringUtils.isNotBlank(bf.category())) {
                functionCategory = bf.category();
            } else {
                functionCategory = method.getName();
            }
            func.setCategory(functionLibCategory + "|" + functionCategory);

            String functionDisplayName;
            if (StringUtils.isNotBlank(bf.displayName())) {
                functionDisplayName = bf.displayName();
            } else {
                functionDisplayName = method.getName();
            }
            func.setDisplayName(functionDisplayName);
            func.setDescription(bf.description());

            func.setExecutable(bf.executable());
            func.setLatent(bf.latent());
            func.setUnsafe(bf.unsafe());
            func.setSignature(getSignature(method));

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

    private static String getSignature(Method method) {
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();

        String params = Arrays.stream(method.getParameters())
                .map(p -> typeToString(p.getParameterizedType()) + p.getName())
                .collect(Collectors.joining(","));

        return className + "." + methodName + "(" + params + ")";
    }

    private static String typeToString(Type type) {
        if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            String rawTypeName = typeToString(pt.getRawType());
            String typeArgs = Arrays.stream(pt.getActualTypeArguments())
                    .map(FunctionResolver::typeToString)
                    .collect(Collectors.joining(","));
            return rawTypeName + "<" + typeArgs + ">";
        } else if (type instanceof Class<?>) {
            Class<?> clazz = (Class<?>) type;
            return clazz.getSimpleName();
        } else if (type instanceof TypeVariable<?>) {
            return ((TypeVariable<?>) type).getName();
        } else if (type instanceof WildcardType) {
            WildcardType wt = (WildcardType) type;
            StringBuilder sb = new StringBuilder("?");
            if (wt.getUpperBounds().length > 0 && wt.getUpperBounds()[0] != Object.class) {
                sb.append(" extends ").append(typeToString(wt.getUpperBounds()[0]));
            }
            if (wt.getLowerBounds().length > 0) {
                sb.append(" super ").append(typeToString(wt.getLowerBounds()[0]));
            }
            return sb.toString();
        }
        return type.getTypeName().replace("java.lang.", "");
    }
}

