package com.zsb.bluex.web.controller;

import com.zsb.bluex.core.delegates.impl.FileSystemListener;
import com.zsb.bluex.core.delegates.impl.ManuallyTriggered;
import com.zsb.bluex.core.graph.GraphView;
import com.zsb.bluex.web.WebResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/build")
public class BuildController {

    @PostMapping("/manuallyTriggeredTest")
    public WebResult<String> graphTransferTest(@RequestBody GraphView graphView) throws Exception {

        ManuallyTriggered manuallyTriggered = new ManuallyTriggered(graphView);
        manuallyTriggered.start();
        return WebResult.success("OK");
    }

    @PostMapping("/fileSystemListenerTest")
    public WebResult<String> fileSystemListenerTest(@RequestBody GraphView graphView) throws Exception {

        FileSystemListener fileSystemListener = new FileSystemListener(graphView);
        fileSystemListener.start();
        return WebResult.success("OK");
    }
}
