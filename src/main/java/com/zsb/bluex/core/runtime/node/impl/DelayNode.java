package com.zsb.bluex.core.runtime.node.impl;

import com.zsb.bluex.core.runtime.ExecTask;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.ExecNode;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class DelayNode extends ExecNode {

    public String nextExec;

    /**
     * 延迟时间（毫秒）
     */
    public long delayMillis;

    private static void spin() {
    }

    public DelayNode(String id, String name) {
        super(id, name);
    }

    @Override
    public void execute(ExecutionContext ctx) throws Exception {
        log.debug("[DelayNode] 延迟开始 {}", LocalDateTime.now());
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < delayMillis) {
            spin();
        }
        log.debug("[DelayNode] 延迟结束 {}", LocalDateTime.now());
        ctx.schedule(new ExecTask(nextExec, null));
    }
}
