package com.zsb.bluex.job;

import com.alibaba.fastjson2.JSON;
import com.zsb.bluex.core.graph.GraphView;
import com.zsb.bluex.core.runtime.delegates.impl.ManuallyTriggered;
import com.zsb.bluex.model.entity.BluexJob;
import com.zsb.bluex.model.entity.BluexProgram;
import com.zsb.bluex.model.service.BluexProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.nio.file.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Slf4j
@Component
public class JobRegistry implements InitializingBean {

    // 存储被激活的任务
    private final Map<String, JobHandle> activatedJobs = new ConcurrentHashMap<>();

    @Resource
    private BluexProgramService bluexProgramService;

    private final TaskScheduler taskScheduler;

    public JobRegistry() {
        ThreadPoolTaskScheduler ts = new ThreadPoolTaskScheduler();
        ts.setPoolSize(5);
        ts.setThreadNamePrefix("bluex-job-");
        ts.initialize();
        this.taskScheduler = ts;
    }

    @Override
    public void afterPropertiesSet() {
//        jobService.list().forEach(this::registerJob);
    }

    public void registerJob(BluexJob job) {
        unregisterJob(job.getJobNo()); // 避免重复
        switch (job.getJobType()) {
            case "A": {
                ScheduledFuture<?> future = taskScheduler.schedule(
                        () -> executeJob(job),
                        new CronTrigger(job.getCronExpression())
                );
                JobHandle handle = new JobHandle(job, future);
                activatedJobs.put(job.getJobNo(), handle);
                break;
            }
            case "B": {
                try {
                    JobHandle handle = fileListenerJobHandle(job);
                    activatedJobs.put(job.getJobNo(), handle);
                } catch (Exception e) {
                    log.error("注册文件监听失败", e);
                }
                break;
            }
            case "C": // HTTP
                registerHttpJob(job, RequestMethod.valueOf(job.getHttpMethod()));
                JobHandle handle = new JobHandle(job);
                activatedJobs.put(job.getJobNo(), handle);
                break;
            default:
                log.error("未知任务类型: {}", job.getJobType());
        }
    }

    /**
     * 注销任务
     */
    public synchronized void unregisterJob(String jobNo) {
        JobHandle handle = activatedJobs.remove(jobNo);
        if (handle == null) return;

        if (handle.getFuture() != null) {
            handle.getFuture().cancel(true);
        }
        if (handle.getThread() != null) {
            handle.getThread().interrupt();
        }
        // HTTP 的注销
        unregisterHttpJob(jobNo);

        log.info("任务 [{}] 已注销", jobNo);
    }

    private JobHandle fileListenerJobHandle(BluexJob job) throws Exception {
        Path path = Paths.get(job.getFilePath());
        WatchService watchService = FileSystems.getDefault().newWatchService();
        path.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY
        );
        Thread thread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    WatchKey key = watchService.take();
                    for (WatchEvent<?> event : key.pollEvents()) {
                        log.info("文件系统事件: {} -> {}", event.kind(), event.context());
                        executeJob(job);
                    }
                    key.reset();
                }
            } catch (InterruptedException e) {
                log.info("文件监听线程 {} 被中断", job.getJobNo());
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                log.error("文件监听失败", e);
            } finally {
                try {
                    watchService.close();
                } catch (Exception ignored) {
                }
            }
        }, "file-listener-" + job.getJobNo());
        thread.start();
        return new JobHandle(job, thread);
    }

    private void registerHttpJob(BluexJob job, RequestMethod method) {
        HttpJobController.registerEndpoint(job, method);
    }

    private void unregisterHttpJob(String jobNo) {
        HttpJobController.unregisterEndpoint(jobNo);
    }

    private void executeJob(BluexJob job) {
        // TODO: 从数据库取脚本 JSON
        String programNo = "8f8c363f37ec475b838a4ba60f3001c6";
        BluexProgram program = bluexProgramService.getById(programNo);
        GraphView graphView = JSON.parseObject(program.getJsonContent(), GraphView.class);
        try {
            ManuallyTriggered manuallyTriggered = new ManuallyTriggered(graphView);
            manuallyTriggered.start(false);
        } catch (Exception exception) {
            log.error("executeJob出现异常:{}", exception.getMessage());
        }
    }
}
