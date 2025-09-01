package com.zsb.bluex.web.controller;

import com.zsb.bluex.core.launch.MetaHolder;
import com.zsb.bluex.web.WebResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meta")
public class MetaController {

    @GetMapping("/graphDefinition")
    public WebResult<MetaHolder.MetaInfo> graphDefinition() throws Exception {
        return WebResult.success(
                new MetaHolder.MetaInfo(
                        MetaHolder.PRIMITIVE_DEFINITION,
                        MetaHolder.CLASS_DEFINITION,
                        MetaHolder.ENUM_DEFINITION,
                        MetaHolder.CONTROL_DEFINITION,
                        MetaHolder.DELEGATE_DEFINITION,
                        MetaHolder.FUNCTION_DEFINITION,
                        MetaHolder.GENERATED_DEFINITION,
                        MetaHolder.ENTITY_DEFINITION
                )
        );
    }
}
