package com.zsb.bluex.core.job.delegate;

import com.zsb.bluex.core.def.ControlDef;
import com.zsb.bluex.core.def.ParamDef;
import com.zsb.bluex.core.graph.GraphView;
import com.zsb.bluex.core.launch.MetaHolder;
import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.delegate.DelegateNode;
import com.zsb.bluex.core.job.EventDelegate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
public class HttpJob extends EventDelegate {

    public HttpJob() {
    }

    public HttpJob(GraphView graphView, RequestMappingHandlerMapping requestMappingHandlerMapping, String httpMethod, String httpUrlMapping) {
        super(graphView);
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
        this.httpMethod = httpMethod;
        this.httpUrlMapping = httpUrlMapping;
    }

    private String httpMethod;
    private String httpUrlMapping;

    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    private RequestMappingInfo requestMappingInfo;

    @Override
    public void start() throws Exception {
        if (requestMappingHandlerMapping == null) {
            throw new RuntimeException("RequestMappingHandlerMapping 未注入");
        }

        try {
            Method handlerMethod = HttpJobHandler.class.getMethod(
                    "handleRequest",
                    HttpServletRequest.class,
                    HttpServletResponse.class
            );

            this.requestMappingInfo = RequestMappingInfo
                    .paths(httpUrlMapping)
                    .methods(RequestMethod.valueOf(httpMethod))
                    .build();

            // 每个 HttpJob 对应一个新的 handler
            requestMappingHandlerMapping.registerMapping(
                    requestMappingInfo,
                    new HttpJobHandler(this),
                    handlerMethod
            );
            log.info("注册 HTTP Endpoint: [{}] {} -> {}", httpMethod, httpUrlMapping, this);
        } catch (Exception e) {
            log.error("注册 HTTP Endpoint 失败", e);
        }
    }

    @Override
    public void end() {
        try {
            if (requestMappingInfo != null) {
                requestMappingHandlerMapping.unregisterMapping(requestMappingInfo);
                log.info("注销 HTTP Endpoint: {} -> {}", httpUrlMapping, this);
            }
        } catch (Exception e) {
            log.error("注销 HTTP Endpoint 失败", e);
        }
    }

    @Override
    public ControlDef provideDefinition() {
        ControlDef def = new ControlDef();
        def.setName("HttpJob");
        def.setDisplayName("HTTP请求监听");
        def.setCategory("事件委托|HttpJob");
        def.setQualifiedName("DELEGATE:HttpJob");
        def.setSignature("DELEGATE:HttpJob");
        def.setDelegate(true);

        // 输出：Exec 节点
        def.getOutputExecDefs().add(new ParamDef("Exec"));

        // 输出：Request 和 Response
        def.getOutputParamDefs().add(
                new ParamDef(
                        "Request",
                        MetaHolder.PRIMITIVE_DEFINITION.get("javax.servlet.http.HttpServletRequest")
                )
        );
        def.getOutputParamDefs().add(
                new ParamDef(
                        "Response",
                        MetaHolder.PRIMITIVE_DEFINITION.get("javax.servlet.http.HttpServletResponse")
                )
        );
        return def;
    }

    /**
     * HTTP Handler 入口
     */
    public static class HttpJobHandler {
        private final HttpJob job;

        public HttpJobHandler(HttpJob job) {
            this.job = job;
        }

        public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
            try {
                ExecutionContext newCtx = job.graphView.buildExecCtx();
                DelegateNode delegateNode = (DelegateNode) newCtx.findStartupNode();
                delegateNode.setOutput("Request", new OUTPUT<>(request));
                delegateNode.setOutput("Response", new OUTPUT<>(response));

                newCtx.run();
            } catch (Exception e) {
                log.error("HttpJob 执行异常", e);
                try {
                    response.sendError(500, "HttpJob 执行异常");
                } catch (Exception ignored) {
                }
            }
        }
    }
}
