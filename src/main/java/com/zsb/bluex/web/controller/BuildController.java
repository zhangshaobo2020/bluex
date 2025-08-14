package com.zsb.bluex.web.controller;

import com.zsb.bluex.core.runtime.delegates.impl.FileSystemListener;
import com.zsb.bluex.core.runtime.delegates.impl.ManuallyTriggered;
import com.zsb.bluex.core.graph.GraphNode;
import com.zsb.bluex.core.graph.GraphView;
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
        if ("DELEGATE:ManuallyTriggered".equals(graphNode.getQualifiedName())) {
            ManuallyTriggered manuallyTriggered = new ManuallyTriggered(graphView);
            manuallyTriggered.start(true);
        } else if ("DELEGATE:FileSystemListener".equals(graphNode.getQualifiedName())) {
            FileSystemListener fileSystemListener = new FileSystemListener(graphView);
            fileSystemListener.start(true);
        }
        return WebResult.success("OK");
    }
}
