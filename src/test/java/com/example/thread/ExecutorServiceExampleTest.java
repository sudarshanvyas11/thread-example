package com.example.thread;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.mockito.BDDMockito.*;

@MockitoSettings
class ExecutorServiceExampleTest {

    @Mock private ExecutorService executorService;
    @Mock private Runnable runnable;

    @Nested
    class ConstructorPreconditions {
        @Test
        void executorServiceMustNotBeNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> new ExecutorServiceExample(null))
                    .withMessage("executorService must not be null");
        }
    }

    @Test
    void runnableMustNotBeNull() {
        assertThatNullPointerException()
                .isThrownBy(() -> new ExecutorServiceExample(executorService).execute(null))
                .withMessage("runnable must not be null");
    }

    @Test
    void execute() throws InterruptedException {
    /*
        Some examples of creating executor service

        Executors.newFixedThreadPool(10);
        Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
        Executors.newScheduledThreadPool(10);
        Executors.newSingleThreadScheduledExecutor(Executors.defaultThreadFactory());
    */

        new ExecutorServiceExample(executorService).execute(runnable);
        then(executorService).should().execute(runnable);
        then(executorService).should().awaitTermination(3000L, TimeUnit.SECONDS);
        then(executorService).should().shutdown();
    }

    @Test
    void shouldThrowExecutorExampleExceptionWhenAwaitTerminationFails() throws InterruptedException {
        willThrow(new InterruptedException("Thread Interrupted!")).given(executorService).awaitTermination(3000L, TimeUnit.SECONDS);

        assertThatExceptionOfType(ExecutorExampleException.class)
                .isThrownBy(() -> new ExecutorServiceExample(executorService).execute(runnable))
                .withMessage("Error executing runnable instance: 'Thread Interrupted!'");

        then(executorService).should().execute(runnable);
        then(executorService).shouldHaveNoMoreInteractions();
    }
}