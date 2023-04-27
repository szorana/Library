package com.example.backend.service.impl;

import com.example.backend.model.Author;
import com.example.backend.model.Book;
import com.example.backend.model.dto.BookDto;
import com.example.backend.model.enums.BookCategory;
import com.example.backend.model.exceptions.AuthorNotFoundException;
import com.example.backend.model.exceptions.BookNotAvailableException;
import com.example.backend.model.exceptions.BookNotFoundException;
import com.example.backend.repository.AuthorRepository;
import com.example.backend.repository.BookRepository;
import com.example.backend.service.BookService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public Optional<Book> save(BookDto book) {
        Author author = this.authorRepository.findById(book.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(book.getAuthor()));

        //this.bookRepository.deleteBookByName(book.getName());
        Book newBook = new Book(book.getName(), book.getCategory(), author, 100);
        this.bookRepository.save(newBook);

        return Optional.of(newBook);
    }

   /* @Override
    public Optional<Book> save(String name, String category, Long authorId) {
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow( () -> new AuthorNotFoundException(authorId));
        BookCategory category1 = BookCategory.valueOf(category);

        this.bookRepository.deleteBookByName(name);
        Book newBook = new Book(name, category1, author, 100);

        this.bookRepository.save(newBook);
        return Optional.of(newBook);
    }*/

    @Override
    public Optional<Book> findById(Long bookId) {
        return this.bookRepository.findById(bookId);
    }

   /* @Override
    public Optional<Book> edit(Long bookId, Book book) {
        Book anotherOBook = this.bookRepository.findById(bookId)
                .orElseThrow(BookNotFoundException::new);

        Author author = this.authorRepository.findById(book.getAuthor().getId())
                .orElseThrow(() -> new AuthorNotFoundException(book.getAuthor().getId()));

        anotherOBook.setName(book.getName());
        anotherOBook.setAuthor(author);
        anotherOBook.setCategory(book.getCategory());
        anotherOBook.setAvailableCopies(book.getAvailableCopies());

        this.bookRepository.save(anotherOBook);
        return Optional.of(anotherOBook);
    }*/

    @Override
    @Transactional
    public Optional<Book> edit(Long bookId, BookDto book) {
        Book anotherOBook = this.bookRepository.findById(bookId).get();

        Author author = this.authorRepository.findById(book.getAuthor()).get();

        anotherOBook.setName(book.getName());
        anotherOBook.setAuthor(author);
        anotherOBook.setCategory(book.getCategory());
        anotherOBook.setAvailableCopies(book.getAvailableCopies());

        this.bookRepository.save(anotherOBook);
        return Optional.of(anotherOBook);
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
