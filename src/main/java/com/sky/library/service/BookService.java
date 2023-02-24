package com.sky.library.service;

import com.sky.library.model.Book;
import com.sky.library.exception.BookNotFoundException;

import java.util.Optional;

public interface BookService {
    Book retrieveBook(String reference) throws BookNotFoundException;
    String getBookSummary(String bookReference) throws BookNotFoundException;
}
