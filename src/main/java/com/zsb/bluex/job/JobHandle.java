package com.zsb.bluex.job;

import com.zsb.bluex.model.entity.BluexJob;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.ScheduledFuture;

@Data
@NoArgsConstructor
public class JobHandle {
    private BluexJob job;
    private ScheduledFuture<?> future;
    private Thread thread;

    public JobHandle(BluexJob job) {
        this.job = job;
    }

    public JobHandle(BluexJob job, ScheduledFuture<?> future) {
        this.job = job;
        this.future = future;
    }

    public JobHandle(BluexJob job, Thread thread) {
        this.job = job;
        this.thread = thread;
    }
}
