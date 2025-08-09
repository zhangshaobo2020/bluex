package com.zsb.bluex.web.controller;

import com.zsb.bluex.core.graph.GraphView;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.web.WebResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/build")
public class BuildController {

    @PostMapping("/graphTransferTest")
    public WebResult<String> graphTransferTest(@RequestBody GraphView graphView) throws Exception {

        ExecutionContext ctx = graphView.buildExecCtx();
        ctx.run();

        return WebResult.success("OK");
    }
}
