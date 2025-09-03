package com.zsb.bluex.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsb.bluex.core.model.entity.BluexScript;
import com.zsb.bluex.core.model.entity.BluexScriptSearch;
import com.zsb.bluex.core.model.service.BluexScriptService;
import com.zsb.bluex.core.script.ScriptRegistrar;
import com.zsb.bluex.core.utils.CommonUtil;
import com.zsb.bluex.core.utils.MybatisPlusUtils;
import com.zsb.bluex.web.Pagination;
import com.zsb.bluex.web.WebResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/script")
public class ScriptController {

    @Resource
    private BluexScriptService bluexScriptService;
    @Resource
    private ScriptRegistrar scriptRegistrar;

    @GetMapping("/scriptPage")
    public WebResult<Pagination<BluexScript>> scriptPage(HttpServletRequest request, BluexScriptSearch search) {
        Pagination<BluexScript> pagination = new Pagination<>(request);
        QueryWrapper<BluexScript> queryWrapper = MybatisPlusUtils.getQueryWrapper(search);
        Page<BluexScript> page = bluexScriptService.page(pagination.generate(), queryWrapper);
        for (BluexScript record : page.getRecords()) {
            boolean activated = ScriptRegistrar.CLASS_CACHE.containsKey(record.getScriptNo());
            record.setRowState(activated ? "Y" : "N");
        }
        return WebResult.success(pagination.transfer(page));
    }

    @GetMapping("/scriptDetail")
    public WebResult<BluexScript> scriptDetail(@RequestParam String scriptNo) {
        return WebResult.success(bluexScriptService.getById(scriptNo));
    }

    @PostMapping("/scriptSubmit")
    @Transactional(rollbackFor = Throwable.class)
    public WebResult<BluexScript> scriptSubmit(@RequestBody BluexScript bluexScript) {

        if (StringUtils.isBlank(bluexScript.getScriptNo())) {
            // 新增
            bluexScript.setScriptNo(CommonUtil.generateUUID());
            bluexScript.setCreateTime(LocalDateTime.now());
            bluexScript.setUpdateTime(LocalDateTime.now());
            bluexScriptService.save(bluexScript);
        } else {
            bluexScript.setUpdateTime(LocalDateTime.now());
            bluexScriptService.updateById(bluexScript);
        }
        return scriptDetail(bluexScript.getScriptNo());
    }

    @PostMapping("/scriptDelete")
    @Transactional(rollbackFor = Throwable.class)
    public WebResult<String> scriptDelete(@RequestParam String scriptNo) {
        bluexScriptService.removeById(scriptNo);
        return WebResult.success("OK");
    }

    @PostMapping("/scriptRegister")
    public WebResult<String> scriptRegister(@RequestParam String scriptNo) throws Exception {
        scriptRegistrar.register(scriptNo);
        return WebResult.success("OK");
    }

    @PostMapping("/scriptUnregister")
    public WebResult<String> scriptUnregister(@RequestParam String scriptNo) throws Exception {
        scriptRegistrar.unregister(scriptNo);
        return WebResult.success("OK");
    }
}
