package com.example.thread;

import org.apache.commons.lang3.Validate;

import java.util.concurrent.Executor;

public class ExecutorExample implements Executor {

    @Override
    public void execute(Runnable runnable) {
        Validate.notNull(runnable, "runnable must not be null");
        runnable.run();
    }
}
