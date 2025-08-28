package com.zsb.bluex.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsb.bluex.job.JobRegistry;
import com.zsb.bluex.model.entity.BluexJob;
import com.zsb.bluex.model.entity.BluexJobSearch;
import com.zsb.bluex.model.service.BluexJobService;
import com.zsb.bluex.utils.CommonUtil;
import com.zsb.bluex.utils.MybatisPlusUtils;
import com.zsb.bluex.web.Pagination;
import com.zsb.bluex.web.WebResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/job")
public class JobController {

    @Resource
    private BluexJobService bluexJobService;
    @Resource
    private JobRegistry jobRegistry;

    @GetMapping("/jobPage")
    public WebResult<Pagination<BluexJob>> jobPage(HttpServletRequest request, BluexJobSearch search) {
        Pagination<BluexJob> pagination = new Pagination<>(request);
        QueryWrapper<BluexJob> queryWrapper = MybatisPlusUtils.getQueryWrapper(search);
        Page<BluexJob> page = bluexJobService.page(pagination.generate(), queryWrapper);
        return WebResult.success(pagination.transfer(page));
    }

    @GetMapping("/jobDetail")
    public WebResult<BluexJob> jobDetail(@RequestParam String jobNo) {
        return WebResult.success(bluexJobService.getById(jobNo));
    }

    @PostMapping("/jobSubmit")
    @Transactional(rollbackFor = Throwable.class)
    public WebResult<BluexJob> jobSubmit(@RequestBody BluexJob bluexJob) {

        if (StringUtils.isBlank(bluexJob.getJobNo())) {
            // 新增
            bluexJob.setJobNo(CommonUtil.generateUUID());
            bluexJob.setCreateTime(LocalDateTime.now());
            bluexJob.setUpdateTime(LocalDateTime.now());
            bluexJobService.save(bluexJob);
        } else {
            bluexJob.setUpdateTime(LocalDateTime.now());
            bluexJobService.updateById(bluexJob);
        }
        return jobDetail(bluexJob.getJobNo());
    }

    @PostMapping("/jobDelete")
    @Transactional(rollbackFor = Throwable.class)
    public WebResult<String> jobDelete(@RequestParam String jobNo) {
        bluexJobService.removeById(jobNo);
        return WebResult.success("OK");
    }

    @PostMapping("/registerJob")
    public WebResult<String> registerJob(@RequestParam String jobNo) {
        jobRegistry.registerJob(bluexJobService.getById(jobNo));
        return WebResult.success("OK");
    }

    @PostMapping("/unregisterJob")
    public WebResult<String> unregisterJob(@RequestParam String jobNo) {
        jobRegistry.unregisterJob(jobNo);
        return WebResult.success("OK");
    }
}
