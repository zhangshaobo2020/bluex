package com.zsb.bluex.web.controller;

import com.zsb.bluex.core.graph.GraphView;
import com.zsb.bluex.model.entity.BluexTask;
import com.zsb.bluex.model.entity.BluexTaskSearch;
import com.zsb.bluex.web.Pagination;
import com.zsb.bluex.web.WebResult;
import com.zsb.bluex.web.mapping.TaskGraph;
import com.zsb.bluex.web.service.TaskManagementService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@RestController
@RequestMapping("/management")
public class ManagementController {

    @Resource
    private TaskManagementService taskManagementService;

    @GetMapping("/taskList")
    public WebResult<Pagination<BluexTask>> taskList(HttpServletRequest request, BluexTaskSearch search) {
        return WebResult.success(taskManagementService.taskList(new Pagination<>(request), search));
    }

    @GetMapping("/taskDetail")
    public WebResult<BluexTask> taskDetail(@RequestParam String taskNo) {
        return WebResult.success(taskManagementService.taskDetail(taskNo));
    }

    @PostMapping("/taskSubmit")
    public WebResult<BluexTask> taskSubmit(@RequestBody TaskGraph taskGraph) {
        return WebResult.success(taskManagementService.taskSubmit(taskGraph));
    }
}
