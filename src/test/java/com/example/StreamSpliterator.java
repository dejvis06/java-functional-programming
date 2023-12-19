package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@SpringBootTest
public class StreamSpliterator {

    @Value("classpath:Books.txt")
    Resource resource;

    @Test
    void contextLoads() throws IOException {
        // Reading lines from a file
        Stream<String> lines = Files.lines(resource.getFile().toPath());

        // Creating a Spliterator for Book objects
        Spliterator<Book> spliterator = new BookSpliterator(lines.spliterator());

        // Converting Spliterator to Stream of Book objects
        Stream<Book> books = StreamSupport.stream(spliterator, false);

        // Printing the Book objects
        books.forEach(System.err::println);
    }

    class BookSpliterator implements Spliterator<Book> {

        String name, author, genre, rating;
        final Spliterator<String> baseSpliterator;

        BookSpliterator(Spliterator<String> baseSpliterator) {
            this.baseSpliterator = baseSpliterator;
        }

        /**
         * Attempts to advance and consume the next element in the data source.
         * The method is responsible for obtaining values for each attribute of the Book
         * and creating a Book instance using those values. If all attributes are available,
         * the provided action Consumer is used to accept the Book instance.
         *
         * @param action The Consumer that will accept the next Book instance.
         * @return true if an element was successfully consumed, false otherwise.
         */
        @Override
        public boolean tryAdvance(Consumer<? super Book> action) {
            // Runtime impl of the baseSpliterator is -> FileChannelLinesSpliterator (check the impl method)
            // Uses tryAdvance 4 times, because each 4 lines in the file represents an attribute as set by order below:
            if (baseSpliterator.tryAdvance(line -> this.name = line) &&
                    baseSpliterator.tryAdvance(line -> this.author = line) &&
                    baseSpliterator.tryAdvance(line -> this.genre = line) &&
                    baseSpliterator.tryAdvance(line -> this.rating = line)) {
                // If all attributes are available, create a Book and accept it
                action.accept(new Book(name, author, genre, rating));
                return true;
            }
            return false;
        }

        @Override
        public Spliterator<Book> trySplit() {
            return null;
        }

        @Override
        public long estimateSize() {
            return baseSpliterator.estimateSize() / 4;
        }

        @Override
        public int characteristics() {
            return baseSpliterator.characteristics();
        }
    }

    class Book {
        String name, author, genre, rating;

        public Book(String name, String author, String genre, String rating) {
            this.name = name;
            this.author = author;
            this.genre = genre;
            this.rating = rating;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "name='" + name + '\'' +
                    ", author='" + author + '\'' +
                    ", genre='" + genre + '\'' +
                    ", rating='" + rating + '\'' +
                    '}';
        }
    }
}
