package com.example.thread;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ThreadExecutionExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadExecutionExample.class);

    private final Thread thread;

    public ThreadExecutionExample(RunnableExample runnable) {
        this.thread = new Thread(Validate.notNull(runnable, "runnable must not be null"));
    }

    public void execute() {
        try {
            thread.start();
            thread.join();
            LOGGER.info("Thread execution completed");
        } catch (InterruptedException e) {
            LOGGER.error("Thread execution failed with message: {}", e.getMessage());
            throw new ExecutorExampleException(String.format("Error executing thread: '%s'", e.getMessage()));
        }
    }
}
