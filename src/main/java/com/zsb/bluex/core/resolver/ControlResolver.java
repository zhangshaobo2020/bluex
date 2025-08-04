package com.zsb.bluex.core.resolver;

import com.zsb.bluex.core.def.ControlDef;
import com.zsb.bluex.core.def.ParamDef;
import com.zsb.bluex.core.launch.MetaHolder;

import java.util.HashMap;
import java.util.Map;

public class ControlResolver {

    public static Map<String, ControlDef> processDefaultControl() {
        Map<String, ControlDef> map = new HashMap<>();
        // BeginPlay节点
        map.put("CONTROL.BeginPlay", definitionBeginPlay());
        // EndPlay节点
        map.put("CONTROL.EndPlay", definitionEndPlay());
        // Branch节点
        map.put("CONTROL.Branch", definitionBranch());
        // While节点
        map.put("CONTROL.While", definitionWhile());
        // ForLoop节点
        map.put("CONTROL.ForLoop", definitionForLoop());
        return map;
    }

    private static ControlDef definitionBeginPlay() {
        ControlDef def = new ControlDef();
        def.setName("BeginPlay");
        def.setDisplayName("开始运行");
        def.setQualifiedName("CONTROL.BeginPlay");

        def.getOutputExecPins().add("Exec");
        return def;
    }

    private static ControlDef definitionEndPlay() {
        ControlDef def = new ControlDef();
        def.setName("EndPlay");
        def.setDisplayName("结束运行");
        def.setQualifiedName("CONTROL.EndPlay");

        def.getInputExecPins().add("Exec");
        return def;
    }

    private static ControlDef definitionBranch() {
        ControlDef def = new ControlDef();
        def.setName("Branch");
        def.setDisplayName("Branch分支");
        def.setQualifiedName("CONTROL.Branch");

        def.getInputExecPins().add("Exec");
        def.getOutputExecPins().add("True");
        def.getOutputExecPins().add("False");

        def.getInputParamPins().add(
                new ParamDef(
                        "Cond",
                        MetaHolder.PRIMITIVE_TYPE_DEFINITION.get("java.lang.Boolean")
                )
        );
        return def;
    }

    private static ControlDef definitionWhile() {
        ControlDef def = new ControlDef();
        def.setName("While");
        def.setDisplayName("While循环");
        def.setQualifiedName("CONTROL.While");

        def.getInputExecPins().add("Exec");
        def.getOutputExecPins().add("Body");
        def.getOutputExecPins().add("Completed");

        def.getInputParamPins().add(
                new ParamDef(
                        "Cond",
                        MetaHolder.PRIMITIVE_TYPE_DEFINITION.get("java.lang.Boolean")
                )
        );
        return def;
    }

    private static ControlDef definitionForLoop() {
        ControlDef def = new ControlDef();
        def.setName("ForLoop");
        def.setDisplayName("For循环");
        def.setQualifiedName("CONTROL.ForLoop");

        def.getInputExecPins().add("Exec");
        def.getOutputExecPins().add("Step");
        def.getOutputExecPins().add("Completed");

        def.getInputParamPins().add(
                new ParamDef(
                        "From",
                        MetaHolder.PRIMITIVE_TYPE_DEFINITION.get("java.lang.Integer")
                )
        );
        def.getInputParamPins().add(
                new ParamDef(
                        "To",
                        MetaHolder.PRIMITIVE_TYPE_DEFINITION.get("java.lang.Integer")
                )
        );
        def.getOutputParamPins().add(
                new ParamDef(
                        "Index",
                        MetaHolder.PRIMITIVE_TYPE_DEFINITION.get("java.lang.Integer")
                )
        );
        return def;
    }
}
