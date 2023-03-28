package com.example.backend.service;

import com.example.backend.model.Book;
import com.example.backend.model.enums.BookCategory;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> listAll();

    Optional<Book> save(Book book);

    Optional<Book> save(String name, BookCategory category, Long authorId);

    Optional<Book> findById(Long bookId);

    Optional<Book> edit(Long id);

    Book markAsTaken(Book book);

    void deleteById(Long bookId);

}
