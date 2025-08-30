package com.zsb.bluex.core.job.config;

import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.WebSocketHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 动态可注册/反注册的 WebSocket HandlerMapping
 */
public class DynamicWebSocketHandlerMapping extends AbstractHandlerMapping {

    private final ConcurrentHashMap<String, WebSocketHandler> handlerMap = new ConcurrentHashMap<>();

    public void register(String path, WebSocketHandler handler) {
        handlerMap.put(path, handler);
    }

    public void unregister(String path) {
        handlerMap.remove(path);
    }

    @Override
    protected Object getHandlerInternal(HttpServletRequest request) {
        String path = request.getRequestURI();
        WebSocketHandler handler = handlerMap.get(path);
        if (handler != null) {
            return new WebSocketHttpRequestHandler(handler);
        }
        return null;
    }
}
