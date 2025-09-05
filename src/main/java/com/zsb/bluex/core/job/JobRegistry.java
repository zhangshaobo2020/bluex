package com.zsb.bluex.core.job;

import com.alibaba.fastjson2.JSON;
import com.zsb.bluex.core.graph.GraphNode;
import com.zsb.bluex.core.graph.GraphView;
import com.zsb.bluex.core.job.config.DynamicWebSocketHandlerMapping;
import com.zsb.bluex.core.job.delegate.*;
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
    public static final Map<String, EventDelegate> ACTIVATED_JOBS = new ConcurrentHashMap<>();

    @Resource
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Resource
    private DynamicWebSocketHandlerMapping dynamicWebSocketHandlerMapping;

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
        switch (job.getProgramType()) {
            case "SingleTriggerJob": {
                if ("DELEGATE:SingleTriggerJob".equals(delegateNode.getQualifiedName())) {
                    SingleTriggerJob singleTriggerJob = new SingleTriggerJob(graphView);
                    singleTriggerJob.start();
                    ACTIVATED_JOBS.put(job.getJobNo(), singleTriggerJob);
                } else {
                    throw new RuntimeException("SingleTriggerJob的事件委托入口不存在");
                }
                break;
            }
            case "CronJob": {
                if ("DELEGATE:CronJob".equals(delegateNode.getQualifiedName())) {
                    CronJob cronJob = new CronJob(graphView, job.getCronExpression());
                    cronJob.start();
                    ACTIVATED_JOBS.put(job.getJobNo(), cronJob);
                } else {
                    throw new RuntimeException("CronJob的事件委托入口不存在");
                }
                break;
            }
            case "FileSystemListenJob": {
                if ("DELEGATE:FileSystemListenJob".equals(delegateNode.getQualifiedName())) {
                    FileSystemListenerJob fileSystemListenerJob = new FileSystemListenerJob(graphView, job.getFilePath());
                    fileSystemListenerJob.start();
                    ACTIVATED_JOBS.put(job.getJobNo(), fileSystemListenerJob);
                } else {
                    throw new RuntimeException("FileSystemListenJob的事件委托入口不存在");
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
                    ACTIVATED_JOBS.put(job.getJobNo(), httpJob);
                } else {
                    throw new RuntimeException("HttpJob的事件委托入口不存在");
                }
                break;
            }
            case "WebSocketJob": {
                if ("DELEGATE:WebSocketJob".equals(delegateNode.getQualifiedName())) {
                    WebSocketJob webSocketJob = new WebSocketJob(
                            graphView,
                            dynamicWebSocketHandlerMapping,
                            job.getWsEndpoint()
                    );
                    webSocketJob.start();
                    ACTIVATED_JOBS.put(job.getJobNo(), webSocketJob);
                } else {
                    throw new RuntimeException("WebSocketJob的事件委托入口不存在");
                }
                break;
            }
            case "MQJob": {
                if ("DELEGATE:MQJob".equals(delegateNode.getQualifiedName())) {
                    MQJob mqJob = new MQJob(
                            graphView,
                            job.getMqDriverName(),
                            job.getMqUri(),
                            job.getMqUsername(),
                            job.getMqPassword(),
                            job.getMqDestinationName(),
                            job.getMqPubSubDomain(),
                            job.getMqQueueManager(),
                            job.getMqChannel(),
                            job.getMqConnectionNameList(),
                            job.getMqCcsId()
                    );
                    mqJob.start();
                    ACTIVATED_JOBS.put(job.getJobNo(), mqJob);
                } else {
                    throw new RuntimeException("MQJob的事件委托入口不存在");
                }
                break;
            }
            case "OracleTableListenerJob": {
                if ("DELEGATE:OracleTableListenerJob".equals(delegateNode.getQualifiedName())) {
                    OracleTableListenerJob oracleTableListenerJob = new OracleTableListenerJob(
                            graphView,
                            job.getDbDriverName(),
                            job.getDbUrl(),
                            job.getDbUsername(),
                            job.getDbPassword(),
                            job.getDbEntity(),
                            job.getDbListenInsert(),
                            job.getDbListenUpdate(),
                            job.getDbListenDelete()
                    );
                    oracleTableListenerJob.start();
                    ACTIVATED_JOBS.put(job.getJobNo(), oracleTableListenerJob);
                } else {
                    throw new RuntimeException("MQJob的事件委托入口不存在");
                }
                break;
            }
            default:
                throw new RuntimeException("未知程序类型:" + job.getProgramType());
        }
        log.info("任务 [{}] 已注册", jobNo);
    }

    /**
     * 注销任务
     */
    public void unregisterJob(String jobNo) throws Exception {
        EventDelegate delegate = ACTIVATED_JOBS.remove(jobNo);
        if (delegate == null) return;
        delegate.end();
        log.info("任务 [{}] 已注销", jobNo);
    }
}
