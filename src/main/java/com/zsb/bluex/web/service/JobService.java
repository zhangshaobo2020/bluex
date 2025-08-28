package com.zsb.bluex.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsb.bluex.model.entity.BluexJob;
import com.zsb.bluex.model.entity.BluexJobSearch;
import com.zsb.bluex.model.service.BluexJobService;
import com.zsb.bluex.utils.CommonUtil;
import com.zsb.bluex.utils.MybatisPlusUtils;
import com.zsb.bluex.web.Pagination;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class JobService {
    @Resource
    private BluexJobService bluexJobService;

    public Pagination<BluexJob> jobPage(Pagination<BluexJob> pagination, BluexJobSearch search) {
        QueryWrapper<BluexJob> queryWrapper = MybatisPlusUtils.getQueryWrapper(search);
        Page<BluexJob> page = bluexJobService.page(pagination.generate(), queryWrapper);
        return pagination.transfer(page);
    }

    public BluexJob jobDetail(String jobNo) {
        return bluexJobService.getById(jobNo);
    }

    @Transactional(rollbackFor = Throwable.class)
    public BluexJob jobSubmit(BluexJob job) {
        if (StringUtils.isBlank(job.getJobNo())) {
            // 新增
            job.setJobNo(CommonUtil.generateUUID());
            job.setCreateTime(LocalDateTime.now());
            job.setUpdateTime(LocalDateTime.now());
            bluexJobService.save(job);
        } else {
            job.setUpdateTime(LocalDateTime.now());
            bluexJobService.updateById(job);
        }
        return jobDetail(job.getJobNo());
    }

    @Transactional(rollbackFor = Throwable.class)
    public String jobDelete(String jobNo) {
        bluexJobService.removeById(jobNo);
        return "OK";
    }
}
