package com.zsb.bluex.web.controller;

import com.zsb.bluex.web.WebResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/meta")
public class MetaController {

    @GetMapping("/graphDefinition")
    public WebResult<Map<String, Object>> graphDefinition() throws Exception {
        // 获取Type定义
        // 获取Control定义
        // 获取Function定义
        return WebResult.success(null);
    }
}
