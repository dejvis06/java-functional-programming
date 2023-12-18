package com.example;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LambdaAndFunctionalInterface {

    Logger log = LoggerFactory.getLogger(LambdaAndFunctionalInterface.class);

    /**
     * Demonstrates the use of lambda expression with the Thread and Runnable interface.
     * Lambda syntax: () -> log.info("Lambda executed")
     * () indicates no parameters, and the arrow (->) separates parameters from the expression body.
     * In this case, the lambda takes no parameters and logs "Lambda executed" when executed.
     */
    @Test
    void contextLoads() {
        Thread thread = new Thread(() -> log.info("Lambda executed"));
        thread.run();
    }
}
