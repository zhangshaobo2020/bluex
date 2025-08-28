package com.zsb.bluex.web.controller;

import com.zsb.bluex.model.entity.BluexJob;
import com.zsb.bluex.model.entity.BluexJobSearch;
import com.zsb.bluex.web.Pagination;
import com.zsb.bluex.web.WebResult;
import com.zsb.bluex.web.service.JobService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/job")
public class JobController {

    @Resource
    private JobService jobService;

    @GetMapping("/jobPage")
    public WebResult<Pagination<BluexJob>> jobPage(HttpServletRequest request, BluexJobSearch search) {
        return WebResult.success(jobService.jobPage(new Pagination<>(request), search));
    }

    @GetMapping("/jobDetail")
    public WebResult<BluexJob> jobDetail(@RequestParam String jobNo) {
        return WebResult.success(jobService.jobDetail(jobNo));
    }

    @PostMapping("/jobSubmit")
    public WebResult<BluexJob> jobSubmit(@RequestBody BluexJob bluexJob) {
        return WebResult.success(jobService.jobSubmit(bluexJob));
    }

    @PostMapping("/jobDelete")
    public WebResult<String> jobDelete(@RequestParam String jobNo) {
        return WebResult.success(jobService.jobDelete(jobNo));
    }
}
