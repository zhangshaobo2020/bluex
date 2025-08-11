package com.zsb.bluex.core.resolver;

import com.zsb.bluex.core.def.ControlDef;
import com.zsb.bluex.core.def.ParamDef;
import com.zsb.bluex.core.launch.MetaHolder;

import java.util.HashMap;
import java.util.Map;

public class ControlResolver {

    public static Map<String, ControlDef> processDefaultControl() {
        Map<String, ControlDef> map = new HashMap<>();
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

    private static ControlDef definitionBranch() {
        ControlDef def = new ControlDef();
        def.setName("Branch");
        def.setDisplayName("Branch分支");
        def.setCategory("控制节点|Branch");
        def.setQualifiedName("CONTROL.Branch");
        def.setSignature("CONTROL.Branch");

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
        def.setCategory("控制节点|While");
        def.setQualifiedName("CONTROL.While");
        def.setSignature("CONTROL.While");

        def.getInputExecDefs().add(new ParamDef("Exec"));
        def.getOutputExecDefs().add(new ParamDef("LoopBody"));
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
        def.setCategory("控制节点|ForLoop");
        def.setQualifiedName("CONTROL.ForLoop");
        def.setSignature("CONTROL.ForLoop");

        def.getInputExecDefs().add(new ParamDef("Exec"));
        def.getOutputExecDefs().add(new ParamDef("LoopBody"));
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
        def.setCategory("控制节点|Delay");
        def.setQualifiedName("CONTROL.Delay");
        def.setSignature("CONTROL.Delay");

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
