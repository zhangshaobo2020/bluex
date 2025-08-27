package com.zsb.bluex.web.controller;

import com.zsb.bluex.model.entity.BluexProgram;
import com.zsb.bluex.model.entity.BluexProgramSearch;
import com.zsb.bluex.web.Pagination;
import com.zsb.bluex.web.WebResult;
import com.zsb.bluex.web.mapping.TaskGraph;
import com.zsb.bluex.web.service.ProgramService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/program")
public class ProgramController {

    @Resource
    private ProgramService programService;

    @GetMapping("/programPage")
    public WebResult<Pagination<BluexProgram>> programPage(HttpServletRequest request, BluexProgramSearch search) {
        return WebResult.success(programService.programPage(new Pagination<>(request), search));
    }

    @GetMapping("/programDetail")
    public WebResult<BluexProgram> programDetail(@RequestParam String programNo) {
        return WebResult.success(programService.programDetail(programNo));
    }

    @PostMapping("/programSubmit")
    public WebResult<BluexProgram> programSubmit(@RequestBody TaskGraph taskGraph) {
        return WebResult.success(programService.programSubmit(taskGraph));
    }

    @PostMapping("/programDelete")
    public WebResult<String> programDelete(@RequestParam String programNo) {
        return WebResult.success(programService.programDelete(programNo));
    }
}
