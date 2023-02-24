package com.sky.library;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sky.library.service.BookService;
import com.sky.library.model.Book;
import com.sky.library.exception.BookNotFoundException;

@RestController
public class LibraryStoreController {

    @Autowired
    private BookService bookService;

    @GetMapping("book/{reference}")
    public ResponseEntity<String> retrieveBook(@PathVariable("reference") String reference) throws BookNotFoundException{
        Book book = bookService.retrieveBook(reference);

        if (book != null) {
            return new ResponseEntity<>(book.getTitle(), HttpStatus.OK);
        } else {
            throw new BookNotFoundException("BookNotFoundException");
        }
    }

    @GetMapping("summary/{reference}")
    public ResponseEntity<String> getBookSummary(@PathVariable("reference") String reference) throws BookNotFoundException{
        String summary = bookService.getBookSummary(reference);

        return new ResponseEntity<>(summary, HttpStatus.OK);
    }
}
