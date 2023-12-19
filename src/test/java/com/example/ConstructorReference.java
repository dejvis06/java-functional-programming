package com.example;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Function;

@SpringBootTest
public class ConstructorReference {

    Logger log = LoggerFactory.getLogger(ConstructorReference.class);

    /**
     * Test method to demonstrate constructor reference for creating a Thread using a Function.
     */
    @Test
    void contextLoads() {
        // Creating a Function that generates a Thread using its constructor reference.
        Function<Runnable, Thread> threadGenerator = Thread::new;

        // Creating a functional interface instance with multiple parameters
        // and using constructor reference to create a Thread.
        ThreadGenerator<ThreadGroup, Runnable, String, Thread> threadGeneratorMultipleParams = Thread::new;
    }

    /**
     * Functional Interface representing a Thread generator with multiple parameters.
     * It declares a single method for creating a Thread with specified parameters.
     */
    @FunctionalInterface
    interface ThreadGenerator<ThreadGroup, Runnable, String, Thread> {
        Thread apply(ThreadGroup threadGroup, Runnable runnable, String name);
    }
}
