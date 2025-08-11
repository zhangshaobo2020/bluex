package com.zsb.bluex.core.resolver;

import com.zsb.bluex.core.def.ControlDef;
import com.zsb.bluex.core.def.ParamDef;
import com.zsb.bluex.core.launch.MetaHolder;

import java.util.HashMap;
import java.util.Map;

public class DelegateResolver {

    public static Map<String, ControlDef> processDefaultDelegate() {
        Map<String, ControlDef> map = new HashMap<>();
        // ManuallyTriggered节点
        map.put("DELEGATE.ManuallyTriggered", definitionManuallyTriggered());
        // FileSystemListener节点
        map.put("DELEGATE.FileSystemListener", definitionFileSystemListener());
        return map;
    }

    private static ControlDef definitionManuallyTriggered() {
        ControlDef def = new ControlDef();
        def.setName("ManuallyTriggered");
        def.setDisplayName("手动触发");
        def.setCategory("事件委托|ManuallyTriggered");
        def.setQualifiedName("DELEGATE.ManuallyTriggered");
        def.setSignature("DELEGATE.ManuallyTriggered");

        def.getOutputExecDefs().add(new ParamDef("Exec"));
        return def;
    }

    private static ControlDef definitionFileSystemListener() {
        ControlDef def = new ControlDef();
        def.setName("definitionFileSystemListener");
        def.setDisplayName("文件系统监听");
        def.setCategory("事件委托|FileSystemListener");
        def.setQualifiedName("DELEGATE.FileSystemListener");
        def.setSignature("DELEGATE.FileSystemListener");

        def.getInputParamDefs().add(
                new ParamDef(
                        "Dir",
                        MetaHolder.PRIMITIVE_TYPE_DEFINITION.get("java.lang.String")
                )
        );

        def.getOutputExecDefs().add(new ParamDef("Exec"));

        def.getOutputParamDefs().add(
                new ParamDef(
                        "File",
                        MetaHolder.PRIMITIVE_TYPE_DEFINITION.get("java.io.File")
                )
        );
        def.getOutputParamDefs().add(
                new ParamDef(
                        "OpType",
                        MetaHolder.TYPE_DEFINITION.get("com.zsb.bluex.defaults.enums.FileOpTypeEnum")
                )
        );
        return def;
    }
}
