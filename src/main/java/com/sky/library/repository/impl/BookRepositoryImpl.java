package com.sky.library.repository.impl;

import com.sky.library.repository.BookRepository;
import com.sky.library.model.Book;
import com.sky.library.exception.BookNotFoundException;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository{

    final static List<Book> listBooks = new ArrayList<Book>();

    static {
        listBooks.add(new Book("BOOK-GRUFF472", "The Gruffalo book", "A mouse taking a walk in the woods"));
        listBooks.add(new Book("BOOK-POOH222", "Winnie the Pooh", "In this first volume, we meet all the friends and relatives"));
        listBooks.add(new Book("BOOK-WILL987", "The Wind in the Willows", "With the arrival of spring and fine weather outside we go for a walk"));
    }

    @Override
    public Book retrieveBook(String reference) throws BookNotFoundException {
        Optional<Book> matchingBook = listBooks.stream().
                filter(b -> b.getReference().equals(reference)).
                findFirst();
        if(matchingBook.isPresent())
            return matchingBook.get();
        else
           throw new BookNotFoundException("BookNotFoundException");
    }
}
