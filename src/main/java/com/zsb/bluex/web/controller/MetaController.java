package com.zsb.bluex.web.controller;

import com.zsb.bluex.core.def.ControlDef;
import com.zsb.bluex.core.def.FunctionDef;
import com.zsb.bluex.core.def.TypeDef;
import com.zsb.bluex.core.launch.MetaHolder;
import com.zsb.bluex.web.WebResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/meta")
public class MetaController {

    @GetMapping("/graphDefinition")
    public WebResult<MetaHolder.MetaInfo> graphDefinition() throws Exception {
        // 获取Type定义
        Map<String, TypeDef> typeDef = MetaHolder.TYPE_DEFINITION;
        // 获取Control定义
        Map<String, ControlDef> controlDef = MetaHolder.CONTROL_DEFINITION;
        // 获取Function定义
        Map<String, FunctionDef> functionDef = MetaHolder.FUNCTION_DEFINITION;
        // 获取Generated定义
        Map<String, FunctionDef> generatedDef = MetaHolder.GENERATED_DEFINITION;
        return WebResult.success(
                new MetaHolder.MetaInfo(typeDef, controlDef, functionDef, generatedDef)
        );
    }
}
