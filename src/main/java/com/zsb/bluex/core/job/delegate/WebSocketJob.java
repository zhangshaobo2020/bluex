package com.zsb.bluex.core.job.delegate;

import com.zsb.bluex.core.def.ControlDef;
import com.zsb.bluex.core.def.ParamDef;
import com.zsb.bluex.core.graph.GraphView;
import com.zsb.bluex.core.job.EventDelegate;
import com.zsb.bluex.core.job.config.DynamicWebSocketHandlerMapping;
import com.zsb.bluex.core.launch.MetaHolder;
import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.delegate.DelegateNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class WebSocketJob extends EventDelegate {

    private DynamicWebSocketHandlerMapping handlerMapping;
    private String wsUrlMapping;
    private boolean registered = false;

    public WebSocketJob() {
    }

    public WebSocketJob(GraphView graphView,
                        DynamicWebSocketHandlerMapping handlerMapping,
                        String wsUrlMapping) {
        super(graphView);
        this.handlerMapping = handlerMapping;
        this.wsUrlMapping = wsUrlMapping;
    }

    @Override
    public void start() throws Exception {
        if (handlerMapping == null) {
            throw new RuntimeException("DynamicWebSocketHandlerMapping 未注入");
        }
        handlerMapping.register(wsUrlMapping, new WebSocketJobHandler(this));
        registered = true;
        log.info("注册 WebSocket Endpoint: {} -> {}", wsUrlMapping, this);
    }

    @Override
    public void end() {
        if (registered) {
            handlerMapping.unregister(wsUrlMapping);
            registered = false;
            log.info("注销 WebSocket Endpoint: {} -> {}", wsUrlMapping, this);
        }
    }

    @Override
    public ControlDef provideDefinition() {
        ControlDef def = new ControlDef();
        def.setName("WebSocketJob");
        def.setDisplayName("WebSocket消息监听");
        def.setCategory("事件委托|WebSocketJob");
        def.setQualifiedName("DELEGATE:WebSocketJob");
        def.setSignature("DELEGATE:WebSocketJob");
        def.setDelegate(true);

        def.getOutputExecDefs().add(new ParamDef("Exec"));

        def.getOutputParamDefs().add(
                new ParamDef(
                        "Session",
                        MetaHolder.PRIMITIVE_DEFINITION.get("org.springframework.web.socket.WebSocketSession")
                )
        );
        def.getOutputParamDefs().add(
                new ParamDef(
                        "Message",
                        MetaHolder.PRIMITIVE_DEFINITION.get("java.lang.String")
                )
        );
        return def;
    }

    public static class WebSocketJobHandler extends AbstractWebSocketHandler {
        private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
        private final WebSocketJob job;

        public WebSocketJobHandler(WebSocketJob job) {
            this.job = job;
        }

        @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {
            sessions.add(session);
        }

        @Override
        public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
            try {
                String payload = message.getPayload().toString();
                ExecutionContext newCtx = job.graphView.buildExecCtx();
                DelegateNode delegateNode = (DelegateNode) newCtx.findStartupNode();
                delegateNode.setOutput("Session", new OUTPUT<>(session));
                delegateNode.setOutput("Message", new OUTPUT<>(payload));

                newCtx.run();
            } catch (Exception e) {
                log.error("WebSocketJob 执行异常", e);
                try {
                    session.close(CloseStatus.SERVER_ERROR);
                } catch (Exception ignored) {
                }
            }
        }

        @Override
        public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
            sessions.remove(session);
        }

        public void closeAllSessions() {
            for (WebSocketSession session : sessions) {
                try {
                    session.close(CloseStatus.NORMAL);
                } catch (Exception ignored) {
                }
            }
            sessions.clear();
        }
    }
}
