package com.sky.library.service.impl;

import com.sky.library.service.BookService;
import com.sky.library.exception.BookNotFoundException;
import com.sky.library.model.Book;
import com.sky.library.constant.BookConstants;
import com.sky.library.repository.BookRepository;

import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import com.google.common.base.Joiner;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public String getBookSummary(String reference) throws BookNotFoundException {
        Book book = retrieveBook(reference);
        StringBuffer sb = new StringBuffer("[");
        sb.append(book.getReference());
        sb.append("] ");
        sb.append(book.getTitle());
        sb.append(" - ");

        if(StringUtils.isNotBlank(book.getReview())) {
            String[] reviewWords = book.getReview().split(BookConstants.BOOK_SUMMARY_REGEX);

            List<String> wordsList = Arrays.asList(reviewWords).stream()
                    .filter(w -> StringUtils.isNotBlank(w))
                    .collect(Collectors.toList());

            for(int count = 0; count < wordsList.size(); count++) {
                if(count == 9){
                    sb.append("...");
                    break;
                } else {
                    sb.append(wordsList.get(count));
                    sb.append(" ");
                    continue;
                }
            }
        }
        return sb.toString();
    }

    @Override
    public Book retrieveBook(String reference) throws BookNotFoundException {
        if(!checkBookByReference(reference)) {
            throw new BookNotFoundException("Invalid book - Book reference must begin with BOOK-");
        }
        return bookRepository.retrieveBook(reference);
    }

    private boolean checkBookByReference(final String reference) {
       return (StringUtils.isNotBlank(reference) &&
                reference.trim().startsWith(BookConstants.BOOK_REFERENCE_PREFIX)) ? true : false;
    }
}
