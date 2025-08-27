package com.zsb.bluex.web.service;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsb.bluex.model.entity.BluexProgram;
import com.zsb.bluex.model.entity.BluexProgramSearch;
import com.zsb.bluex.model.service.BluexProgramService;
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
public class ProgramService {
    @Resource
    private BluexProgramService bluexProgramService;

    public Pagination<BluexProgram> programPage(Pagination<BluexProgram> pagination, BluexProgramSearch search) {
        QueryWrapper<BluexProgram> queryWrapper = MybatisPlusUtils.getQueryWrapper(search);
        Page<BluexProgram> page = bluexProgramService.page(pagination.generate(), queryWrapper);
        return pagination.transfer(page);
    }

    public BluexProgram programDetail(String programNo) {
        return bluexProgramService.getById(programNo);
    }

    @Transactional(rollbackFor = Throwable.class)
    public BluexProgram programSubmit(TaskGraph taskGraph) {
        BluexProgram program = taskGraph.getProgram();
        program.setJsonContent(JSON.toJSONString(taskGraph.getGraph()));

        if (StringUtils.isBlank(program.getProgramNo())) {
            // 新增
            program.setProgramNo(CommonUtil.generateUUID());
            program.setCreateTime(LocalDateTime.now());
            program.setUpdateTime(LocalDateTime.now());
            bluexProgramService.save(program);
        } else {
            program.setUpdateTime(LocalDateTime.now());
            bluexProgramService.updateById(program);
        }
        return programDetail(program.getProgramNo());
    }

    @Transactional(rollbackFor = Throwable.class)
    public String programDelete(String programNo) {
        bluexProgramService.removeById(programNo);
        return "OK";
    }
}
