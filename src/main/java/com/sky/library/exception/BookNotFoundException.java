package com.sky.library.exception;

import org.springframework.http.HttpStatus;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(final String err) {
        super(err);
    }
}
