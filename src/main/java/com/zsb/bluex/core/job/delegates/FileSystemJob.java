package com.zsb.bluex.core.job.delegates;

import com.zsb.bluex.core.def.ControlDef;
import com.zsb.bluex.core.def.ParamDef;
import com.zsb.bluex.core.graph.GraphView;
import com.zsb.bluex.core.launch.MetaHolder;
import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.delegate.DelegateNode;
import com.zsb.bluex.defaults.enums.FileOpType;
import com.zsb.bluex.core.job.EventDelegate;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class FileSystemJob extends EventDelegate {

    public FileSystemJob() {
    }

    public FileSystemJob(GraphView graphView, String filePath) {
        super(graphView);
        this.filePath = filePath;
    }

    public String filePath;

    private final Map<Path, Long> lastHandled = new ConcurrentHashMap<>();
    private final long DEBOUNCE_MS = 500;

    private volatile boolean running = false;
    private WatchService watchService;
    private Thread watchThread;

    @Override
    public void start() throws Exception {
        watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(filePath);
        path.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY
        );

        running = true;
        watchThread = new Thread(() -> {
            try {
                while (running) {
                    WatchKey key;
                    try {
                        key = watchService.take(); // 阻塞等待事件
                    } catch (ClosedWatchServiceException e) {
                        break; // watchService 被关闭时退出
                    }
                    for (WatchEvent<?> event : key.pollEvents()) {
                        Path changed = path.resolve((Path) event.context());
                        // 忽略短时间重复事件
                        long now = System.currentTimeMillis();
                        if (now - lastHandled.getOrDefault(changed, 0L) < DEBOUNCE_MS) {
                            continue;
                        }
                        lastHandled.put(changed, now);

                        File file = changed.toFile();
                        /* 执行开始 */
                        ExecutionContext newCtx = graphView.buildExecCtx();
                        DelegateNode delegateNode = (DelegateNode) newCtx.findStartupNode();
                        delegateNode.setOutput("File", new OUTPUT<>(file));
                        // 设置文件操作类型
                        if ("ENTRY_CREATE".equals(event.kind().name())) {
                            delegateNode.setOutput("OpType", new OUTPUT<>(FileOpType.CREATE));
                        } else if ("ENTRY_MODIFY".equals(event.kind().name())) {
                            delegateNode.setOutput("OpType", new OUTPUT<>(FileOpType.MODIFY));
                        } else if ("ENTRY_DELETE".equals(event.kind().name())) {
                            delegateNode.setOutput("OpType", new OUTPUT<>(FileOpType.DELETE));
                        }
                        newCtx.run();
                    }
                    key.reset();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                log.error("FileSystemJob异常", e);
            }
        }, "FileSystemJob-Thread");
        watchThread.start();
    }

    @Override
    public void end() {
        running = false;
        try {
            if (watchService != null) {
                watchService.close(); // 会唤醒 take() 并抛出 ClosedWatchServiceException
            }
        } catch (Exception e) {
            log.error("关闭 WatchService 出错", e);
        }
        if (watchThread != null) {
            watchThread.interrupt(); // 防止阻塞在其他地方
        }
    }

    @Override
    public ControlDef provideDefinition() {
        ControlDef def = new ControlDef();
        def.setName("FileSystemJob");
        def.setDisplayName("文件系统监听");
        def.setCategory("事件委托|FileSystemJob");
        def.setQualifiedName("DELEGATE:FileSystemJob");
        def.setSignature("DELEGATE:FileSystemJob");
        def.setDelegate(true);

        def.getOutputExecDefs().add(new ParamDef("Exec"));

        def.getOutputParamDefs().add(
                new ParamDef(
                        "File",
                        MetaHolder.PRIMITIVE_DEFINITION.get("java.io.File")
                )
        );
        def.getOutputParamDefs().add(
                new ParamDef(
                        "OpType",
                        MetaHolder.ENUM_DEFINITION.get("com.zsb.bluex.defaults.enums.FileOpType")
                )
        );
        return def;
    }
}
