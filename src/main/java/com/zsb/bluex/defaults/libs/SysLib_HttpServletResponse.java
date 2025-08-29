package com.zsb.bluex.defaults.libs;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;

@BluexFunctionLib(category = "Spring支持|HttpServletResponse")
public class SysLib_HttpServletResponse {

    @BluexFunction(category = "状态|设置状态码")
    public static void SetStatus(
            INPUT<HttpServletResponse> Response,
            INPUT<Integer> Status
    ) {
        Response.value.setStatus(Status.value);
    }

    @BluexFunction(category = "状态|获取状态码", executable = false)
    public static void GetStatus(
            INPUT<HttpServletResponse> Response,
            OUTPUT<Integer> Out
    ) {
        Out.value = Response.value.getStatus();
    }

    @BluexFunction(category = "输出|写文本")
    public static void WriteText(
            INPUT<HttpServletResponse> Response,
            INPUT<String> Text
    ) {
        try {
            Response.value.setContentType("text/plain;charset=UTF-8");
            PrintWriter writer = Response.value.getWriter();
            writer.write(Text.value);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("写出文本失败", e);
        }
    }

    @BluexFunction(category = "输出|写JSON")
    public static void WriteJson(
            INPUT<HttpServletResponse> Response,
            INPUT<String> Json
    ) {
        try {
            Response.value.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = Response.value.getWriter();
            writer.write(Json.value);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("写出JSON失败", e);
        }
    }

    @BluexFunction(category = "输出|写文件")
    public static void WriteFile(
            INPUT<HttpServletResponse> Response,
            INPUT<File> File
    ) {
        try (FileInputStream fis = new FileInputStream(File.value)) {
            // 自动推断 MIME 类型
            String mimeType = Files.probeContentType(File.value.toPath());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }

            Response.value.setContentType(mimeType);
            Response.value.setHeader("Content-Disposition", "attachment; filename=\"" + File.value.getName() + "\"");
            Response.value.setContentLengthLong(File.value.length());

            byte[] buffer = new byte[4096];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                Response.value.getOutputStream().write(buffer, 0, len);
            }
            Response.value.getOutputStream().flush();
        } catch (IOException e) {
            throw new RuntimeException("写出文件失败: " + File.value, e);
        }
    }

    @BluexFunction(category = "Header|设置Header")
    public static void SetHeader(
            INPUT<HttpServletResponse> Response,
            INPUT<String> Name,
            INPUT<String> Value
    ) {
        Response.value.setHeader(Name.value, Value.value);
    }

    @BluexFunction(category = "Header|添加Header")
    public static void AddHeader(
            INPUT<HttpServletResponse> Response,
            INPUT<String> Name,
            INPUT<String> Value
    ) {
        Response.value.addHeader(Name.value, Value.value);
    }

    @BluexFunction(category = "Header|包含Header?", executable = false)
    public static void ContainsHeader(
            INPUT<HttpServletResponse> Response,
            INPUT<String> Name,
            OUTPUT<Boolean> Out
    ) {
        Out.value = Response.value.containsHeader(Name.value);
    }

    @BluexFunction(category = "Header|获取所有Header名称", executable = false)
    public static void GetHeaderNames(
            INPUT<HttpServletResponse> Response,
            OUTPUT<List<String>> Out
    ) {
        Out.value = (List<String>) Response.value.getHeaderNames();
    }

    @BluexFunction(category = "Header|获取Header值", executable = false)
    public static void GetHeader(
            INPUT<HttpServletResponse> Response,
            INPUT<String> Name,
            OUTPUT<String> Out
    ) {
        Out.value = Response.value.getHeader(Name.value);
    }

    @BluexFunction(category = "Header|获取所有Header值", executable = false)
    public static void GetHeaders(
            INPUT<HttpServletResponse> Response,
            INPUT<String> Name,
            OUTPUT<List<String>> Out
    ) {
        Out.value = (List<String>) Response.value.getHeaders(Name.value);
    }

    @BluexFunction(category = "类型|设置ContentType")
    public static void SetContentType(
            INPUT<HttpServletResponse> Response,
            INPUT<String> ContentType
    ) {
        Response.value.setContentType(ContentType.value);
    }

    @BluexFunction(category = "类型|设置CharacterEncoding")
    public static void SetCharacterEncoding(
            INPUT<HttpServletResponse> Response,
            INPUT<String> Encoding
    ) {
        Response.value.setCharacterEncoding(Encoding.value);
    }

    @BluexFunction(category = "重定向|发送重定向")
    public static void SendRedirect(
            INPUT<HttpServletResponse> Response,
            INPUT<String> Location
    ) {
        try {
            Response.value.sendRedirect(Location.value);
        } catch (IOException e) {
            throw new RuntimeException("重定向失败", e);
        }
    }

    @BluexFunction(category = "Cookie|添加Cookie")
    public static void AddCookie(
            INPUT<HttpServletResponse> Response,
            INPUT<Cookie> cookie
    ) {
        Response.value.addCookie(cookie.value);
    }

    @BluexFunction(category = "控制|Flush缓冲区")
    public static void FlushBuffer(
            INPUT<HttpServletResponse> Response
    ) {
        try {
            Response.value.flushBuffer();
        } catch (IOException e) {
            throw new RuntimeException("Flush缓冲区失败", e);
        }
    }

    @BluexFunction(category = "控制|重置Response")
    public static void Reset(
            INPUT<HttpServletResponse> Response
    ) {
        Response.value.reset();
    }

    @BluexFunction(category = "控制|是否已提交")
    public static void IsCommitted(
            INPUT<HttpServletResponse> Response,
            OUTPUT<Boolean> Out
    ) {
        Out.value = Response.value.isCommitted();
    }
}
