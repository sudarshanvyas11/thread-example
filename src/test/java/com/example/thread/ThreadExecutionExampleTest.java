package com.example.thread;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class ThreadExecutionExampleTest {

    @Nested
    class ConstructorPreconditions {
        @Test
        void runnableMustNotBeNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> new ThreadExecutionExample(null))
                    .withMessage("runnable must not be null");
        }
    }

    @Test
    void executeThread() {
        final LogCaptor logCaptor = LogCaptor.forClass(ThreadExecutionExample.class);

        new ThreadExecutionExample(new RunnableExample()).execute();

        assertThat(logCaptor.getInfoLogs()).containsExactly("Thread execution completed");
    }

    @Test
    void shouldThrowExecutorExampleExceptionWhenThreadJoinFails() {

    }
}