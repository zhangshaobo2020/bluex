package com.zsb.bluex.defaults.libs;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@BluexFunctionLib(category = "Java常见类型|File")
public class SysLib_File {

    @BluexFunction(category = "检查File是否存在", executable = false)
    public static void Exists(
            INPUT<String> Path,
            OUTPUT<Boolean> Out
    ) {
        Out.value = new File(Path.value).exists();
    }

    @BluexFunction(category = "创建新File")
    public static void CreateFile(
            INPUT<String> Path,
            OUTPUT<Boolean> Out
    ) throws Exception {
        File file = new File(Path.value);
        if (!file.exists()) {
            Out.value = file.createNewFile();
        } else {
            Out.value = false;
        }
    }

    @BluexFunction(category = "删除File")
    public static void DeleteFile(
            INPUT<String> Path,
            OUTPUT<Boolean> Out
    ) {
        File file = new File(Path.value);
        Out.value = file.delete();
    }

    @BluexFunction(category = "读取File文本内容")
    public static void ReadAllText(
            INPUT<String> Path,
            OUTPUT<String> Out
    ) throws Exception {
        byte[] bytes = Files.readAllBytes(new File(Path.value).toPath());
        Out.value = new String(bytes, StandardCharsets.UTF_8);
    }

    @BluexFunction(category = "写入文本到File")
    public static void WriteAllText(
            INPUT<String> Path,
            INPUT<String> Content,
            OUTPUT<Boolean> Out
    ) throws Exception {
        Files.write(new File(Path.value).toPath(),
                Content.value.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
        Out.value = true;
    }

    @BluexFunction(category = "追加文本到File")
    public static void AppendText(
            INPUT<String> Path,
            INPUT<String> Content,
            OUTPUT<Boolean> Out
    ) throws Exception {
        Files.write(new File(Path.value).toPath(),
                Content.value.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
        Out.value = true;
    }

    @BluexFunction(category = "获取File大小(字节)", executable = false)
    public static void GetFileSize(
            INPUT<String> Path,
            OUTPUT<Long> Out
    ) {
        File file = new File(Path.value);
        if (file.exists() && file.isFile()) {
            Out.value = file.length();
        } else {
            Out.value = -1L;
        }
    }

    @BluexFunction(category = "获取File名", executable = false)
    public static void GetFileName(
            INPUT<String> Path,
            OUTPUT<String> Out
    ) {
        File file = new File(Path.value);
        Out.value = file.getName();
    }

    @BluexFunction(category = "获取File所在目录", executable = false)
    public static void GetDirectoryName(
            INPUT<String> Path,
            OUTPUT<String> Out
    ) {
        File file = new File(Path.value);
        Out.value = file.getParent();
    }

    @BluexFunction(category = "检查是否是目录", executable = false)
    public static void IsDirectory(
            INPUT<String> Path,
            OUTPUT<Boolean> Out
    ) {
        File file = new File(Path.value);
        Out.value = file.isDirectory();
    }

    @BluexFunction(category = "检查是否是文件", executable = false)
    public static void IsFile(
            INPUT<String> Path,
            OUTPUT<Boolean> Out
    ) {
        File file = new File(Path.value);
        Out.value = file.isFile();
    }

    @BluexFunction(category = "列出指定目录下的文件", executable = false)
    public static void ListFiles(
            INPUT<String> Path,
            OUTPUT<List<File>> Files
    ) {
        File dir = new File(Path.value);
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                Files.value = Arrays.asList(files);
            } else {
                Files.value = Collections.emptyList();
            }
        } else {
            Files.value = Collections.emptyList();
        }
    }
}
