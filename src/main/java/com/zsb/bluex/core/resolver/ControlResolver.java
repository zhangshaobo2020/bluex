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
        // Branch节点
        map.put("CONTROL.Branch", definitionBranch());
        // While节点
        map.put("CONTROL.While", definitionWhile());
        // ForLoop节点
        map.put("CONTROL.ForLoop", definitionForLoop());
        // Delay节点
        map.put("CONTROL.Delay", definitionDelay());
        return map;
    }

    private static ControlDef definitionBeginPlay() {
        ControlDef def = new ControlDef();
        def.setName("BeginPlay");
        def.setDisplayName("开始运行");
        def.setCategory("CONTROL|BeginPlay");
        def.setQualifiedName("CONTROL.BeginPlay");

        def.getOutputExecDefs().add(new ParamDef("Exec"));
        return def;
    }

    private static ControlDef definitionBranch() {
        ControlDef def = new ControlDef();
        def.setName("Branch");
        def.setDisplayName("Branch分支");
        def.setCategory("CONTROL|Branch");
        def.setQualifiedName("CONTROL.Branch");

        def.getInputExecDefs().add(new ParamDef("Exec"));
        def.getOutputExecDefs().add(new ParamDef("True"));
        def.getOutputExecDefs().add(new ParamDef("False"));

        def.getInputParamDefs().add(
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
        def.setCategory("CONTROL|While");
        def.setQualifiedName("CONTROL.While");

        def.getInputExecDefs().add(new ParamDef("Exec"));
        def.getOutputExecDefs().add(new ParamDef("Body"));
        def.getOutputExecDefs().add(new ParamDef("Completed"));

        def.getInputParamDefs().add(
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
        def.setCategory("CONTROL|ForLoop");
        def.setQualifiedName("CONTROL.ForLoop");

        def.getInputExecDefs().add(new ParamDef("Exec"));
        def.getOutputExecDefs().add(new ParamDef("Step"));
        def.getOutputExecDefs().add(new ParamDef("Completed"));

        def.getInputParamDefs().add(
                new ParamDef(
                        "From",
                        MetaHolder.PRIMITIVE_TYPE_DEFINITION.get("java.lang.Integer")
                )
        );
        def.getInputParamDefs().add(
                new ParamDef(
                        "To",
                        MetaHolder.PRIMITIVE_TYPE_DEFINITION.get("java.lang.Integer")
                )
        );
        def.getOutputParamDefs().add(
                new ParamDef(
                        "Index",
                        MetaHolder.PRIMITIVE_TYPE_DEFINITION.get("java.lang.Integer")
                )
        );
        return def;
    }

    private static ControlDef definitionDelay() {
        ControlDef def = new ControlDef();
        def.setName("Delay");
        def.setDisplayName("延迟");
        def.setCategory("CONTROL|Delay");
        def.setQualifiedName("CONTROL.Delay");

        def.getInputExecDefs().add(new ParamDef("Exec"));
        def.getOutputExecDefs().add(new ParamDef("Exec"));

        def.getInputParamDefs().add(
                new ParamDef(
                        "Delay(ms)",
                        MetaHolder.PRIMITIVE_TYPE_DEFINITION.get("java.lang.Long")
                )
        );
        return def;
    }
}
