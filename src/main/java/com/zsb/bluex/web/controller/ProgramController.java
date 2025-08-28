package com.zsb.bluex.web.controller;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsb.bluex.model.entity.BluexProgram;
import com.zsb.bluex.model.entity.BluexProgramSearch;
import com.zsb.bluex.model.service.BluexProgramService;
import com.zsb.bluex.utils.CommonUtil;
import com.zsb.bluex.utils.MybatisPlusUtils;
import com.zsb.bluex.web.Pagination;
import com.zsb.bluex.web.WebResult;
import com.zsb.bluex.web.mapping.TaskGraph;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/program")
public class ProgramController {

    @Resource
    private BluexProgramService bluexProgramService;

    @GetMapping("/programPage")
    public WebResult<Pagination<BluexProgram>> programPage(HttpServletRequest request, BluexProgramSearch search) {
        Pagination<BluexProgram> pagination = new Pagination<>(request);
        QueryWrapper<BluexProgram> queryWrapper = MybatisPlusUtils.getQueryWrapper(search);
        Page<BluexProgram> page = bluexProgramService.page(pagination.generate(), queryWrapper);
        return WebResult.success(pagination.transfer(page));
    }

    @GetMapping("/programDetail")
    public WebResult<BluexProgram> programDetail(@RequestParam String programNo) {
        return WebResult.success(bluexProgramService.getById(programNo));
    }

    @PostMapping("/programSubmit")
    @Transactional(rollbackFor = Throwable.class)
    public WebResult<BluexProgram> programSubmit(@RequestBody TaskGraph taskGraph) {
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

    @PostMapping("/programDelete")
    @Transactional(rollbackFor = Throwable.class)
    public WebResult<String> programDelete(@RequestParam String programNo) {
        bluexProgramService.removeById(programNo);
        return WebResult.success("OK");
    }
}
