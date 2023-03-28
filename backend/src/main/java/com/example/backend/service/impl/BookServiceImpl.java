package com.example.backend.service.impl;

import com.example.backend.model.Author;
import com.example.backend.model.Book;
import com.example.backend.model.enums.BookCategory;
import com.example.backend.model.exceptions.AuthorNotFoundException;
import com.example.backend.model.exceptions.BookNotAvailableException;
import com.example.backend.model.exceptions.BookNotFoundException;
import com.example.backend.repository.AuthorRepository;
import com.example.backend.repository.BookRepository;
import com.example.backend.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Book> save(Book book) {
        Author author = this.authorRepository.findById(book.getAuthor().getId())
                .orElseThrow( () -> new AuthorNotFoundException(book.getAuthor().getId()));

        this.bookRepository.deleteBookByName(book.getName());
        Book newBook = new Book(book.getName(), book.getCategory(), author);
        this.bookRepository.save(newBook);

        return Optional.of(newBook);
    }

    @Override
    public Optional<Book> save(String name, BookCategory category, Long authorId) {
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow( () -> new AuthorNotFoundException(authorId));

        this.bookRepository.deleteBookByName(name);
        Book newBook = new Book(name, category, author);
        this.bookRepository.save(newBook);

        return Optional.empty();
    }

    @Override
    public Optional<Book> findById(Long bookId) {
        return this.bookRepository.findById(bookId);
    }

    @Override
    public Optional<Book> edit(Long id) {
        /* Book b = this.bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);

        book.setName();*/

        return Optional.empty();
    }

    @Override
    public Book markAsTaken(Book book) {
        Book anotherOne = this.bookRepository.findBookByName(book.getName())
                .orElseThrow(BookNotFoundException::new);

        if (anotherOne.getAvailableCopies() <= 0){
            throw new BookNotAvailableException(anotherOne.getName());
        }
        anotherOne.markAsTaken();
        return this.bookRepository.save(anotherOne);
    }

    @Override
    public void deleteById(Long bookId) {
        this.bookRepository.deleteById(bookId);
    }
}
