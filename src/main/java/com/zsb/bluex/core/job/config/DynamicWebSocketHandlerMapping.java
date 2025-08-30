package com.zsb.bluex.core.job.config;

import com.zsb.bluex.core.job.delegate.WebSocketJob;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.WebSocketHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 动态可注册/反注册的 WebSocket HandlerMapping
 */
public class DynamicWebSocketHandlerMapping extends AbstractHandlerMapping {

    public DynamicWebSocketHandlerMapping() {
        // 提前顺序，避免被其他 HandlerMapping 抢先处理
        setOrder(-1);
    }

    private final ConcurrentHashMap<String, WebSocketHandler> handlerMap = new ConcurrentHashMap<>();

    public void register(String path, WebSocketHandler handler) {
        handlerMap.put(path, handler);
    }

    public void unregister(String path) {
        WebSocketHandler handler = handlerMap.remove(path);
        if (handler instanceof WebSocketJob.WebSocketJobHandler) {
            ((WebSocketJob.WebSocketJobHandler) handler).closeAllSessions(); // 关闭所有已连接 session
        }
    }

    @Override
    protected Object getHandlerInternal(HttpServletRequest request) {
        String path = request.getRequestURI();
        String contextPath = request.getContextPath();
        if (contextPath != null && !contextPath.isEmpty()) {
            path = path.substring(contextPath.length());
        }
        WebSocketHandler handler = handlerMap.get(path);
        if (handler != null) {
            return new WebSocketHttpRequestHandler(handler);
        }
        return null;
    }
}
