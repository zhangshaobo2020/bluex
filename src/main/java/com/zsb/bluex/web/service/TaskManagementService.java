package com.zsb.bluex.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsb.bluex.model.entity.BluexTask;
import com.zsb.bluex.model.entity.BluexTaskSearch;
import com.zsb.bluex.model.service.BluexTaskService;
import com.zsb.bluex.utils.MybatisPlusUtils;
import com.zsb.bluex.web.Pagination;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TaskManagementService {
    @Resource
    private BluexTaskService bluexTaskService;

    public Pagination<BluexTask> taskList(Pagination<BluexTask> pagination, BluexTaskSearch search) {
        QueryWrapper<BluexTask> queryWrapper = MybatisPlusUtils.getQueryWrapper(search);
        Page<BluexTask> page = bluexTaskService.page(pagination.generate(), queryWrapper);
        return pagination.transfer(page);
    }

    public BluexTask taskDetail(String taskNo) {
        return bluexTaskService.getById(taskNo);
    }
}
