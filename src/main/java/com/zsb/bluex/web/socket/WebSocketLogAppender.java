package com.zsb.bluex.web.socket;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.encoder.Encoder;
import com.alibaba.fastjson2.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CopyOnWriteArrayList;

@Setter
@Getter
public class WebSocketLogAppender extends AppenderBase<ILoggingEvent> {

    private Encoder<ILoggingEvent> encoder;

    private static final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    public static void addSession(WebSocketSession session) {
        sessions.add(session);
    }

    public static void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }

    @SneakyThrows
    @Override
    protected void append(ILoggingEvent event) {
        String message = new String(encoder.encode(event), StandardCharsets.UTF_8);
        LogRecord logRecord = new LogRecord(event.getLevel().toString(), message);
        for (WebSocketSession session : sessions) {
            session.sendMessage(new TextMessage(JSON.toJSONString(logRecord)));
        }
    }
}
