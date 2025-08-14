package com.zsb.bluex.core.resolver;

import com.zsb.bluex.core.def.ControlDef;
import com.zsb.bluex.core.runtime.node.impl.BranchNode;
import com.zsb.bluex.core.runtime.node.impl.DelayNode;
import com.zsb.bluex.core.runtime.node.impl.ForLoopNode;
import com.zsb.bluex.core.runtime.node.impl.WhileNode;

import java.util.HashMap;
import java.util.Map;

public class ControlResolver {

    public static Map<String, ControlDef> processDefaultControl() {
        Map<String, ControlDef> map = new HashMap<>();
        // Branch节点
        map.put("CONTROL:Branch", new BranchNode().provideDefinition());
        // While节点
        map.put("CONTROL:While", new WhileNode().provideDefinition());
        // ForLoop节点
        map.put("CONTROL:ForLoop", new ForLoopNode().provideDefinition());
        // Delay节点
        map.put("CONTROL:Delay", new DelayNode().provideDefinition());
        return map;
    }
}
