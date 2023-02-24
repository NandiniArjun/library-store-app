package com.sky.library.repository;

import com.sky.library.model.Book;
import com.sky.library.exception.BookNotFoundException;

import java.util.Optional;

public interface BookRepository {
    Book retrieveBook(String reference) throws BookNotFoundException;
}
