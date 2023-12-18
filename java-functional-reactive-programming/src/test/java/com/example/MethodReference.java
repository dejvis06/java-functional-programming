package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * This class serves as an example of using method references in Java, demonstrating various scenarios:
 * 1. Using method references with a Consumer interface.
 * 2. Using method references with static methods and BiFunction interface.
 * 3. Using method references with instance methods and Function interface.
 * It also includes an inner class 'Example' with a static method, an instance method, and a simple attribute.
 * The class is designed to showcase the syntax and usage of method references compared to equivalent lambda expressions.
 */
@SpringBootTest
public class MethodReference {

    @Test
    void contextLoads() {

        // Example of using method reference with a Consumer interface
        Consumer<String> methodReferenceConsumer = System.err::println;
        // Equivalent lambda expression without method reference
        Consumer<String> noMethodReferenceConsumer = s -> System.err.println(s);

        // Example of using method reference with a static method and BiFunction interface
        BiFunction<Integer, Integer, Integer> staticMethodReferenceFunction = Example::staticMethod;
        // Equivalent lambda expression without method reference
        BiFunction<Integer, Integer, Integer> staticNoMethodReferenceFunction = (x, y) -> {
            return Example.staticMethod(x, y);
        };

        // Example of using method reference with an instance method and Function interface
        Function<String, Integer> instanceMethodReferenceFunctionStr = String::length;
        // Equivalent lambda expression without method reference
        Function<String, Integer> instanceNoMethodReferenceFunctionStr = x -> {
            return x.length();
        };

        // Example of using method reference with an instance method and Function interface
        Function<Example, Integer> instanceMethodReferenceFunctionExample = Example::instanceMethod;
        // Equivalent lambda expression without method reference
        Function<Example, Integer> instanceNoMethodReferenceFunctionExample = x -> {
            return x.instanceMethod();
        };

        // Applying the instance method reference on an Example instance
        instanceMethodReferenceFunctionExample.apply(new Example("test"));
    }

    // Example class containing static and instance methods
    class Example {
        String attribute;
        Example(String attribute) {
            this.attribute = attribute;
        }

        static Integer staticMethod(Integer x, Integer y) {
            return x + y;
        }

        Integer instanceMethod() {
            return 0;
        }
    }
}
