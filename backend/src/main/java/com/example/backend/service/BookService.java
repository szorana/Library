package com.example.backend.service;

import com.example.backend.model.Book;
import com.example.backend.model.dto.BookDto;
import com.example.backend.model.enums.BookCategory;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> listAll();

    Optional<Book> save(BookDto book);


   /* Optional<Book> save(String name, String category, Long authorId);*/

    Optional<Book> findById(Long bookId);

    Optional<Book> edit(Long bookId, BookDto book);

/*
    Optional<Book> edit(Long bookId, Book book);
*/

    Book markAsTaken(Book book);

    void deleteById(Long bookId);


}
