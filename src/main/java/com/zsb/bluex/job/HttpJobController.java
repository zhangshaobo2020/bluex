package com.zsb.bluex.job;

import com.zsb.bluex.model.entity.BluexJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
public class HttpJobController {

    private static RequestMappingHandlerMapping handlerMapping;

    private static final Map<String, RequestMappingInfo> registeredMappings = new ConcurrentHashMap<>();

    public HttpJobController(RequestMappingHandlerMapping mapping) {
        handlerMapping = mapping;
    }

    @RequestMapping("/httpJob/{jobNo}")
    public String handle(@PathVariable("jobNo") String jobNo,
                         @RequestParam(required = false) String param) {
        log.info("HTTP 任务触发: {} param={}", jobNo, param);
        // TODO: 执行 ExecutionContext
        return "Job " + jobNo + " executed at " + System.currentTimeMillis();
    }

    /**
     * 注册 HTTP Endpoint
     */
    public static void registerEndpoint(BluexJob job, RequestMethod method) {
        try {
            String path = job.getHttpUrlMapping();
            Method handlerMethod = HttpJobController.class.getMethod("handleRequest", String.class);

            RequestMappingInfo mappingInfo = RequestMappingInfo
                    .paths(path)
                    .methods(method)
                    .build();

            handlerMapping.registerMapping(mappingInfo, new HttpJobController(handlerMapping), handlerMethod);

            registeredMappings.put(job.getJobNo(), mappingInfo);

            log.info("注册 HTTP Endpoint: [{}] {} -> jobNo={}", method, path, job.getJobNo());
        } catch (Exception e) {
            log.error("注册 HTTP Endpoint 失败", e);
        }
    }

    /**
     * 注销 HTTP Endpoint
     */
    public static void unregisterEndpoint(String jobNo) {
        RequestMappingInfo mappingInfo = registeredMappings.remove(jobNo);
        if (mappingInfo != null) {
            try {
                handlerMapping.unregisterMapping(mappingInfo);
                log.info("注销 HTTP Endpoint: jobNo={}", jobNo);
            } catch (Exception e) {
                log.error("注销 HTTP Endpoint 失败", e);
            }
        }
    }

    /**
     * 实际处理请求的方法
     */
    public String handleRequest(String body) {
        log.info("动态 HTTP Endpoint 被触发, body={}", body);
        // TODO: 触发对应的 BluexProgram 执行
        return "OK";
    }
}
