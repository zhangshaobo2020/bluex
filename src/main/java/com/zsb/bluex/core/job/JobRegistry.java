package com.zsb.bluex.core.job;

import com.alibaba.fastjson2.JSON;
import com.zsb.bluex.core.graph.GraphNode;
import com.zsb.bluex.core.graph.GraphView;
import com.zsb.bluex.core.job.delegates.CronJob;
import com.zsb.bluex.core.job.delegates.FileSystemJob;
import com.zsb.bluex.core.job.delegates.HttpJob;
import com.zsb.bluex.core.model.entity.BluexJob;
import com.zsb.bluex.core.model.entity.BluexProgram;
import com.zsb.bluex.core.model.service.BluexJobService;
import com.zsb.bluex.core.model.service.BluexProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JobRegistry implements InitializingBean {

    // 存储被激活的任务
    public static final Map<String, EventDelegate> activatedJobs = new ConcurrentHashMap<>();

    @Resource
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Resource
    private BluexProgramService bluexProgramService;
    @Resource
    private BluexJobService bluexJobService;

    public JobRegistry() {
    }

    @Override
    public void afterPropertiesSet() {
        // TODO: 根据条件决定应用启动时是否要自动注册所有Job
    }

    public void registerJob(String jobNo) throws Exception {
        BluexJob job = bluexJobService.getById(jobNo);
        if (job == null) {
            throw new RuntimeException("Job不存在:" + jobNo);
        }
        BluexProgram program = bluexProgramService.getById(job.getProgramNo());
        if (program == null) {
            throw new RuntimeException("Program不存在:" + job.getProgramNo());
        }
        // 准备GraphView
        GraphView graphView = JSON.parseObject(program.getJsonContent(), GraphView.class);
        List<GraphNode> delegateNodes = graphView.getNodes()
                .stream()
                .filter(node -> node.getQualifiedName().startsWith("DELEGATE:"))
                .collect(Collectors.toList());
        if (delegateNodes.size() != 1) {
            throw new RuntimeException("事件委托节点数量有且只能有一个！");
        }
        // 事件委托的入口
        GraphNode delegateNode = delegateNodes.get(0);

        // 开始注册
        unregisterJob(job.getJobNo()); // 避免重复注册
        switch (job.getJobType()) {
            case "CronJob": {
                if ("DELEGATE:CronJob".equals(delegateNode.getQualifiedName())) {
                    CronJob cronJob = new CronJob(graphView, job.getCronExpression());
                    cronJob.start();
                    activatedJobs.put(job.getJobNo(), cronJob);
                } else {
                    throw new RuntimeException("CronJob的事件委托入口不存在");
                }
                break;
            }
            case "FileSystemJob": {
                if ("DELEGATE:FileSystemJob".equals(delegateNode.getQualifiedName())) {
                    FileSystemJob fileSystemJob = new FileSystemJob(graphView, job.getFilePath());
                    fileSystemJob.start();
                    activatedJobs.put(job.getJobNo(), fileSystemJob);
                } else {
                    throw new RuntimeException("FileSystemJob的事件委托入口不存在");
                }
                break;
            }
            case "HttpJob": {
                if ("DELEGATE:HttpJob".equals(delegateNode.getQualifiedName())) {
                    HttpJob httpJob = new HttpJob(
                            graphView,
                            requestMappingHandlerMapping,
                            job.getHttpMethod(),
                            job.getHttpUrlMapping()
                    );
                    httpJob.start();
                    activatedJobs.put(job.getJobNo(), httpJob);
                } else {
                    throw new RuntimeException("HttpJob的事件委托入口不存在");
                }
                break;
            }
            default:
                log.error("未知任务类型: {}", job.getJobType());
        }
        log.info("任务 [{}] 已注册", jobNo);
    }

    /**
     * 注销任务
     */
    public void unregisterJob(String jobNo) throws Exception {
        EventDelegate delegate = activatedJobs.remove(jobNo);
        if (delegate == null) return;
        delegate.end();
        log.info("任务 [{}] 已注销", jobNo);
    }
}
