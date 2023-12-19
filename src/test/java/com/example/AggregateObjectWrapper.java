package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
public class AggregateObjectWrapper {

    /**
     * This test method demonstrates the Iterator pattern implementation using a custom MyArrayList class.
     * It creates an instance of MyArrayList with an array of integers and then performs various operations
     * like iterating, filtering, and printing the elements using a method chain.
     */
    @Test
    void contextLoads() {
        // Creating a MyArrayList instance with an array of integers
        MyArrayList myArrayList = new MyArrayList(new Object[]{1, 2, 3, 4, 5});

        // Method chain: forEach and filter
        System.err.println("Method chain: forEach and filter");
        myArrayList.forEach(System.err::println)
                .filter(element -> (int) element > 2)
                .forEach(System.err::println);
    }

    /**
     * This inner class represents a simplified version of an ArrayList, demonstrating the Iterator pattern.
     * It contains an array of elements and provides methods for iteration, filtering, and printing using functional programming.
     */
    class MyArrayList {

        // List to store elements
        private final List<Object> elements;

        /**
         * Constructor for MyArrayList, initializes the list of elements.
         *
         * @param elements The array of elements to be stored in MyArrayList.
         */
        public MyArrayList(Object[] elements) {
            this.elements = new ArrayList<>(Arrays.asList(elements));
        }

        /**
         * This method iterates over the elements in the MyArrayList and applies the provided consumer
         * to each element. It demonstrates the Iterator pattern by providing a way to traverse the elements
         * without exposing the underlying implementation details of the list.
         *
         * @param consumer The functional interface representing the iterator action to be applied to each element.
         * @return The current MyArrayList instance.
         */
        public MyArrayList forEach(Consumer<Object> consumer) {
            // Iterating over the elements and applying the iterator
            for (Object element : elements) {
                consumer.accept(element);
            }
            // Returning the current MyArrayList instance for method chaining
            return this;
        }

        /**
         * This method filters the elements in the MyArrayList based on the provided predicate.
         *
         * @param predicate The functional interface representing the predicate for filtering elements.
         * @return A new MyArrayList containing only the elements that satisfy the predicate.
         */
        public MyArrayList filter(Predicate<Object> predicate) {
            List<Object> filteredElements = new ArrayList<>();
            for (Object element : elements) {
                if (predicate.test(element)) {
                    filteredElements.add(element);
                }
            }
            // Creating a new MyArrayList instance with the filtered elements
            return new MyArrayList(filteredElements.toArray());
        }
    }
}
