package com.zsb.bluex.core.resolver;

import com.zsb.bluex.core.def.ControlDef;
import com.zsb.bluex.core.runtime.delegates.impl.FileSystemListener;
import com.zsb.bluex.core.runtime.delegates.impl.ManuallyTriggered;

import java.util.HashMap;
import java.util.Map;

public class DelegateResolver {

    public static Map<String, ControlDef> processDefaultDelegate() {
        Map<String, ControlDef> map = new HashMap<>();
        // ManuallyTriggered节点
        map.put("DELEGATE:ManuallyTriggered", new ManuallyTriggered().provideDefinition());
        // FileSystemListener节点
        map.put("DELEGATE:FileSystemListener", new FileSystemListener().provideDefinition());
        return map;
    }
}
