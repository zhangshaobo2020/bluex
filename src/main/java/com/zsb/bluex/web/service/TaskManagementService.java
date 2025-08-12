package com.zsb.bluex.web.service;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsb.bluex.model.entity.BluexTask;
import com.zsb.bluex.model.entity.BluexTaskSearch;
import com.zsb.bluex.model.service.BluexTaskService;
import com.zsb.bluex.utils.CommonUtil;
import com.zsb.bluex.utils.MybatisPlusUtils;
import com.zsb.bluex.web.Pagination;
import com.zsb.bluex.web.mapping.TaskGraph;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

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

    @Transactional(rollbackFor = Throwable.class)
    public BluexTask taskSubmit(TaskGraph taskGraph) {
        BluexTask task = taskGraph.getTask();
        task.setJsonContent(JSON.toJSONString(taskGraph.getGraph()));

        if (StringUtils.isBlank(task.getTaskNo())) {
            // 新增
            task.setTaskNo(CommonUtil.generateUUID());
            task.setCreateTime(LocalDateTime.now());
            task.setUpdateTime(LocalDateTime.now());
            bluexTaskService.save(task);
        } else {
            task.setUpdateTime(LocalDateTime.now());
            bluexTaskService.updateById(task);
        }
        return taskDetail(task.getTaskNo());
    }
}
