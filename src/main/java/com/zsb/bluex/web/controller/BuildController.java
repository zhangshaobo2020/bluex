package com.zsb.bluex.web.controller;

import com.zsb.bluex.core.graph.GraphNode;
import com.zsb.bluex.core.graph.GraphView;
import com.zsb.bluex.core.job.delegate.SingleTriggerJob;
import com.zsb.bluex.web.WebResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/build")
public class BuildController {

    @PostMapping("/autoMatchTest")
    public WebResult<String> autoMatchTest(@RequestBody GraphView graphView) throws Exception {
        List<GraphNode> graphNodes = graphView.getNodes()
                .stream()
                .filter(node -> node.getQualifiedName().startsWith("DELEGATE:"))
                .collect(Collectors.toList());
        if (graphNodes.size() != 1) {
            throw new RuntimeException("事件委托节点数量有且只能有一个！");
        }
        GraphNode graphNode = graphNodes.get(0);
        if ("DELEGATE:SingleTriggerJob".equals(graphNode.getQualifiedName())) {
            SingleTriggerJob singleTriggerJob = new SingleTriggerJob(graphView);
            singleTriggerJob.start();
        } else {
            throw new RuntimeException("只支持\"单次触发\"的事件委托");
        }
        return WebResult.success("OK");
    }
}
