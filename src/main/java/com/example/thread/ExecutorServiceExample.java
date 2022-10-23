package com.example.thread;

import org.apache.commons.lang3.Validate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceExample {

    private final ExecutorService executorService;

    public ExecutorServiceExample(ExecutorService executorService) {
        this.executorService = Validate.notNull(executorService, "executorService must not be null");
    }

    public void execute(Runnable runnable) {
        Validate.notNull(runnable, "runnable must not be null");
        executorService.execute(runnable);
        try {
            executorService.awaitTermination(3000L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new ExecutorExampleException(String.format("Error executing runnable instance: '%s'", e.getMessage()));
        }
        executorService.shutdown();
    }
}
