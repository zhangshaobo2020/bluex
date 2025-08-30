package com.zsb.bluex.core.resolver;

import com.zsb.bluex.core.def.ControlDef;
import com.zsb.bluex.core.job.delegate.*;

import java.util.HashMap;
import java.util.Map;

public class DelegateResolver {

    public static Map<String, ControlDef> processDefaultDelegate() {
        Map<String, ControlDef> map = new HashMap<>();
        // SingleTriggerJob节点
        map.put("DELEGATE:SingleTriggerJob", new SingleTriggerJob().provideDefinition());
        // CronJob节点
        map.put("DELEGATE:CronJob", new CronJob().provideDefinition());
        // FileSystemJob节点
        map.put("DELEGATE:FileSystemJob", new FileSystemJob().provideDefinition());
        // HttpJob节点
        map.put("DELEGATE:HttpJob", new HttpJob().provideDefinition());
        // WebSocketJob节点
        map.put("DELEGATE:WebSocketJob", new WebSocketJob().provideDefinition());
        return map;
    }
}
