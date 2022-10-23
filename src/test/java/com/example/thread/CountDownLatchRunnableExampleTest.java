package com.example.thread;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

@MockitoSettings
class CountDownLatchRunnableExampleTest {

    @Nested
    class ConstructorPreconditions {
        @Test
        void outputMessageListMustNotBeNull(@Mock final CountDownLatch countDownLatch) {
            assertThatNullPointerException()
                    .isThrownBy(() -> new CountDownLatchRunnableExample(null, countDownLatch))
                    .withMessage("outputMessageList must not be null");
        }
        
        @Test
        void countDownLatchMustNotBeNull(@Mock final List<String> outputMessageList) {
            assertThatNullPointerException()
                    .isThrownBy(() -> new CountDownLatchRunnableExample(outputMessageList, null))
                    .withMessage("countDownLatch must not be null");
        }
    }

    @Test
    void countDownLatchDemoTest() throws InterruptedException {
        final List<String> outputMessageList = Collections.synchronizedList(new ArrayList<>());
        final CountDownLatch countDownLatch = new CountDownLatch(5);

        final List<Thread> workers = Stream
                .generate(() -> new Thread(new CountDownLatchRunnableExample(outputMessageList, countDownLatch)))
                .limit(5)
                .collect(Collectors.toList());

        workers.forEach(Thread::start);

        //Makes this thread wait for the latch countdown to finish for the worker thread
        countDownLatch.await();
        outputMessageList.add("Latch released");

        assertThat(outputMessageList).containsExactly(
                "Job executed",
                "Job executed",
                "Job executed",
                "Job executed",
                "Job executed",
                "Latch released"
                );
    }
}