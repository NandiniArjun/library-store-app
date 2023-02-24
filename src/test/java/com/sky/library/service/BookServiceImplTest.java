package com.sky.library.service;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.Assert.assertTrue;

import com.sky.library.service.BookService;
import com.sky.library.repository.BookRepository;
import com.sky.library.model.Book;
import com.sky.library.exception.BookNotFoundException;

public class BookServiceImplTest {

    private BookService bookService;
    private BookRepository bookRepository;

    @Test
    void givenBookReferenceWhenBookExistsThenRecieveBookObject() {
        final Book book = bookService.retrieveBook("BOOK-GRUFF472");

        assertThat(book).isNotNull();
        assertThat(book.getReference()).isEqualTo("BOOK-GRUFF472");
        assertThat(book.getTitle()).isEqualTo("The Gruffalo book");
        assertThat(book.getReview()).isEqualTo("A mouse taking a walk in the woods");
    }

    @Test
    public void givenBookReferenceWhenBookDoesNotExistsThenRecieveException() {
        Exception exception = assertThrows(BookNotFoundException.class, () -> {
            bookService.retrieveBook("BOOK-999");
        });

        String expectedMessage = "BookNotFoundException";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void givenInvalidBookReferenceThenRecieveException() {
        Exception exception = assertThrows(BookNotFoundException.class, () -> {
            bookService.retrieveBook("aaa");
        });

        String expectedMessage = "Invalid book - Book reference must begin with BOOK-";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void givenBookReferenceWhenBookDoesExistsThenRecieveSummary() {
        final String summary = bookService.getBookSummary("BOOK-GRUFF472");

        assertThat(summary).isNotNull();
        assertThat(summary).isEqualTo("[BOOK-GRUFF472] The Gruffalo book - A mouse taking a walk in the woods");
    }

    @Test
    public void givenBookReferenceWhenBookDoesWithReviewMoreThan9WordsExistsThenRecieveSummary() {
        final String summary = bookService.getBookSummary("BOOK-WILL987");

        assertThat(summary).isNotNull();
        assertThat(summary).isEqualTo("[BOOK-WILL987] The Wind in the Willows - With the arrival of spring and fine weather outside...");
    }
}
