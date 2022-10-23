package com.example.thread;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.mockito.BDDMockito.then;

@MockitoSettings
class ExecutorExampleTest {

    @Nested
    class Preconditions {
        @Test
        void runnableMustNotBeNull() {
          assertThatNullPointerException()
                  .isThrownBy(() -> new ExecutorExample().execute(null))
                  .withMessage("runnable must not be null");
        }
    }

    @Test
    void shouldExecuteRunnable(@Mock final Runnable runnable) {
        new ExecutorExample().execute(runnable);
        then(runnable).should().run();
    }
}