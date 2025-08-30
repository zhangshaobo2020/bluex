package com.zsb.bluex.defaults.libs;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;

@BluexFunctionLib(category = "Spring支持|WebSocketSession")
public class SysLib_WebSocketSession {

    // ------------------- 基础操作 -------------------

    @BluexFunction(category = "控制|关闭连接")
    public static void Close(
            INPUT<WebSocketSession> Session,
            INPUT<Integer> StatusCode,
            INPUT<String> Reason
    ) {
        try {
            Session.value.close(new CloseStatus(StatusCode.value, Reason.value));
        } catch (IOException e) {
            throw new RuntimeException("关闭WebSocket失败", e);
        }
    }

    @BluexFunction(category = "控制|是否已关闭", executable = false)
    public static void IsOpen(
            INPUT<WebSocketSession> Session,
            OUTPUT<Boolean> Out
    ) {
        Out.value = Session.value.isOpen();
    }

    // ------------------- 发送消息 -------------------

    @BluexFunction(category = "发送|发送文本消息")
    public static void SendText(
            INPUT<WebSocketSession> Session,
            INPUT<String> Text
    ) {
        try {
            Session.value.sendMessage(new TextMessage(Text.value));
        } catch (IOException e) {
            throw new RuntimeException("发送文本消息失败", e);
        }
    }

    // ------------------- 属性操作 -------------------

    @BluexFunction(category = "属性|获取SessionId", executable = false)
    public static void GetId(
            INPUT<WebSocketSession> Session,
            OUTPUT<String> Out
    ) {
        Out.value = Session.value.getId();
    }

    @BluexFunction(category = "属性|获取URI", executable = false)
    public static void GetUri(
            INPUT<WebSocketSession> Session,
            OUTPUT<String> Out
    ) {
        Out.value = String.valueOf(Session.value.getUri());
    }

    @BluexFunction(category = "属性|获取远程地址", executable = false)
    public static void GetRemoteAddress(
            INPUT<WebSocketSession> Session,
            OUTPUT<String> Out
    ) {
        Out.value = Session.value.getRemoteAddress() != null ?
                Session.value.getRemoteAddress().toString() : null;
    }

    @BluexFunction(category = "属性|获取本地地址", executable = false)
    public static void GetLocalAddress(
            INPUT<WebSocketSession> Session,
            OUTPUT<String> Out
    ) {
        Out.value = Session.value.getLocalAddress() != null ?
                Session.value.getLocalAddress().toString() : null;
    }

    @BluexFunction(category = "属性|获取属性Map", executable = false)
    public static void GetAttributes(
            INPUT<WebSocketSession> Session,
            OUTPUT<Map<String, Object>> Out
    ) {
        Out.value = Session.value.getAttributes();
    }

    @BluexFunction(category = "属性|获取协议", executable = false)
    public static void GetSubProtocol(
            INPUT<WebSocketSession> Session,
            OUTPUT<String> Out
    ) {
        Out.value = Session.value.getAcceptedProtocol();
    }
}
