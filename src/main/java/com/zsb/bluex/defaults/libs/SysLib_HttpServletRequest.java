package com.zsb.bluex.defaults.libs;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@BluexFunctionLib(category = "Spring支持|HttpServletRequest")
public class SysLib_HttpServletRequest {

    @BluexFunction(category = "基础信息|获取请求方法", executable = false)
    public static void GetMethod(
            INPUT<HttpServletRequest> Request,
            OUTPUT<String> Out
    ) {
        Out.value = Request.value.getMethod();
    }

    @BluexFunction(category = "基础信息|获取请求URL", executable = false)
    public static void GetRequestURL(
            INPUT<HttpServletRequest> Request,
            OUTPUT<String> Out
    ) {
        Out.value = Request.value.getRequestURL().toString();
    }

    @BluexFunction(category = "基础信息|获取请求URI", executable = false)
    public static void GetRequestURI(
            INPUT<HttpServletRequest> Request,
            OUTPUT<String> Out
    ) {
        Out.value = Request.value.getRequestURI();
    }

    @BluexFunction(category = "基础信息|获取QueryString", executable = false)
    public static void GetQueryString(
            INPUT<HttpServletRequest> Request,
            OUTPUT<String> Out
    ) {
        Out.value = Request.value.getQueryString();
    }

    @BluexFunction(category = "参数|获取单个参数", executable = false)
    public static void GetParameter(
            INPUT<HttpServletRequest> Request,
            INPUT<String> Name,
            OUTPUT<String> Out
    ) {
        Out.value = Request.value.getParameter(Name.value);
    }

    @BluexFunction(category = "Header|获取单个Header", executable = false)
    public static void GetHeader(
            INPUT<HttpServletRequest> Request,
            INPUT<String> Name,
            OUTPUT<String> Out
    ) {
        Out.value = Request.value.getHeader(Name.value);
    }

    @BluexFunction(category = "Header|获取所有Headers", executable = false)
    public static void GetAllHeaders(
            INPUT<HttpServletRequest> Request,
            OUTPUT<Map<String, String>> Out
    ) {
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = Request.value.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            headers.put(name, Request.value.getHeader(name));
        }
        Out.value = headers;
    }

    @BluexFunction(category = "Body|获取请求体字符串", executable = false)
    public static void GetBodyAsString(
            INPUT<HttpServletRequest> Request,
            OUTPUT<String> Out
    ) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = Request.value.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("读取请求体失败", e);
        }
        Out.value = sb.toString().trim();
    }

    @BluexFunction(category = "客户端|获取远程IP", executable = false)
    public static void GetRemoteAddr(
            INPUT<HttpServletRequest> Request,
            OUTPUT<String> Out
    ) {
        Out.value = Request.value.getRemoteAddr();
    }

    @BluexFunction(category = "客户端|获取远程Host", executable = false)
    public static void GetRemoteHost(
            INPUT<HttpServletRequest> Request,
            OUTPUT<String> Out
    ) {
        Out.value = Request.value.getRemoteHost();
    }

    @BluexFunction(category = "客户端|获取远程Port", executable = false)
    public static void GetRemotePort(
            INPUT<HttpServletRequest> Request,
            OUTPUT<Integer> Out
    ) {
        Out.value = Request.value.getRemotePort();
    }

    @BluexFunction(category = "Session|获取HttpSession", executable = false)
    public static void GetSession(
            INPUT<HttpServletRequest> Request,
            OUTPUT<HttpSession> Out
    ) {
        Out.value = Request.value.getSession();
    }

    @BluexFunction(category = "Session|获取Session Attribute", executable = false)
    public static void GetSessionAttribute(
            INPUT<HttpServletRequest> Request,
            INPUT<String> Name,
            OUTPUT<Object> Out
    ) {
        HttpSession session = Request.value.getSession(false);
        Out.value = (session != null) ? session.getAttribute(Name.value) : null;
    }

    @BluexFunction(category = "Session|设置Session Attribute", executable = false)
    public static void SetSessionAttribute(
            INPUT<HttpServletRequest> Request,
            INPUT<String> Name,
            INPUT<Object> Value
    ) {
        HttpSession session = Request.value.getSession(true);
        session.setAttribute(Name.value, Value.value);
    }

    @BluexFunction(category = "属性|获取Request Attribute", executable = false)
    public static void GetRequestAttribute(
            INPUT<HttpServletRequest> Request,
            INPUT<String> Name,
            OUTPUT<Object> Out
    ) {
        Out.value = Request.value.getAttribute(Name.value);
    }

    @BluexFunction(category = "属性|设置Request Attribute", executable = false)
    public static void SetRequestAttribute(
            INPUT<HttpServletRequest> Request,
            INPUT<String> Name,
            INPUT<Object> Value
    ) {
        Request.value.setAttribute(Name.value, Value.value);
    }

    @BluexFunction(category = "判断|是否Ajax请求", executable = false)
    public static void IsAjaxRequest(
            INPUT<HttpServletRequest> Request,
            OUTPUT<Boolean> Out
    ) {
        String requestedWith = Request.value.getHeader("X-Requested-With");
        Out.value = "XMLHttpRequest".equalsIgnoreCase(requestedWith);
    }
}
