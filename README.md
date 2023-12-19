# Functional Programming

This repository contains Java code examples demonstrating functional programming. Each class focuses on a specific topic, showcasing syntax and usage. </br>
This README.md file provides a structured overview of the code examples, with sections for each class and explanations of the demonstrated concepts.
_(see the tests package for more)_

## Lambda and Functional Interface (LambdaAndFunctionalInterface)

This class demonstrates the use of lambda expressions with the `Thread` and `Runnable` interface. It includes a simple lambda expression syntax and usage.

```java
@Test
void contextLoads() {
    Thread thread = new Thread(() -> log.info("Lambda executed"));
    thread.run();
}
```

## Method Reference (MethodReference)

This class serves as an example of using method references in Java. It covers scenarios with a `Consumer` interface, static methods and `BiFunction` interface, and instance methods and `Function` interface. It also includes an inner class `Example` to showcase various method reference types.

#### Usage Example

```java
// Method reference with a Consumer interface
Consumer<String> methodReferenceConsumer = System.err::println;

// Method reference with a static method and BiFunction interface
BiFunction<Integer, Integer, Integer> staticMethodReferenceFunction = Example::staticMethod;

// Method reference with an instance method and Function interface
Function<Example, Integer> instanceMethodReferenceFunctionExample = Example::instanceMethod;

// Applying the instance method reference on an Example instance
instanceMethodReferenceFunctionExample.apply(new Example("test"));
```


## Constructor Reference (ConstructorReference)

This class demonstrates constructor reference usage for creating a Thread using a `Function`. It also introduces a functional interface `ThreadGenerator` representing a thread generator with multiple parameters.

#### Usage Example

```java
// Creating a Function that generates a Thread using its constructor reference
Function<Runnable, Thread> threadGenerator = Thread::new;

// Creating a functional interface instance with multiple parameters
ThreadGenerator<ThreadGroup, Runnable, String, Thread> threadGeneratorMultipleParams = Thread::new;
```

## Aggregate Object Wrapper (AggregateObjectWrapper)

This class implements the Iterator pattern using a custom `MyArrayList` class. It includes methods for iterating, filtering, and printing elements using functional programming concepts.

#### Usage Example

```java
// Creating a MyArrayList instance with an array of integers
MyArrayList myArrayList = new MyArrayList(new Object[]{1, 2, 3, 4, 5});

// Method chain: forEach and filter
System.err.println("Method chain: forEach and filter");
myArrayList.forEach(System.err::println)
        .filter(element -> (int) element > 2)
        .forEach(System.err::println);
```

## Stream Spliterator (StreamSpliterator)

This class demonstrates the usage of a custom Spliterator to process lines from a file and convert them into a Stream of Book objects using Java Streams API. </br>
It includes a BookSpliterator inner class, implementing the Spliterator interface, and a Book class to represent individual book details.

#### Usage

The `contextLoads` method in the test class reads lines from a file, creates a Spliterator for Book objects (`BookSpliterator`), and converts it into a Stream of Book objects. Finally, it prints the Book objects.

```java
    // Reading lines from a file
    Stream<String> lines = Files.lines(resource.getFile().toPath());

    // Creating a Spliterator for Book objects
    Spliterator<Book> spliterator = new BookSpliterator(lines.spliterator());

    // Converting Spliterator to Stream of Book objects
    Stream<Book> books = StreamSupport.stream(spliterator, false);

    // Printing the Book objects
    books.forEach(System.err::println);
```



