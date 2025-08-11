package com.zsb.bluex.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsb.bluex.core.graph.GraphView;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/save")
public class SaveController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/graphFile")
    public void graphTransferTest(@RequestBody GraphView graphView, HttpServletResponse response) throws Exception {
        // 1. 将对象转成 JSON 字符串
        String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(graphView);
        // 2. 设置响应头（下载文件）
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"graph.json\"");
        // 3. 写出到响应流
        response.getOutputStream().write(jsonString.getBytes(StandardCharsets.UTF_8));
        response.flushBuffer();
    }
}
