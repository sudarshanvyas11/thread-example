package com.example.thread;

import org.apache.commons.lang3.Validate;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchRunnableExample implements Runnable{


    private final List<String> outputMessageList;
    private final CountDownLatch countDownLatch;

    public CountDownLatchRunnableExample(List<String> outputMessageList, CountDownLatch countDownLatch) {
        this.outputMessageList = Validate.notNull(outputMessageList, "outputMessageList must not be null");
        this.countDownLatch = Validate.notNull(countDownLatch, "countDownLatch must not be null");
    }

    @Override
    public void run() {
        //Complete the job in the thread and then count down latch till it reaches zero
        outputMessageList.add("Job executed");
        countDownLatch.countDown();
    }
}
