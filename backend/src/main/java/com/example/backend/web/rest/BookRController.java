package com.example.backend.web.rest;

import com.example.backend.model.Book;
import com.example.backend.model.exceptions.BookNotFoundException;
import com.example.backend.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRController {

    private final BookService bookService;

    public BookRController(BookService bookService) {
        this.bookService = bookService;
    }

    //All Books -> works
    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.listAll();
    }

    //Get Book -> works
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookBtId(@PathVariable Long id){
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    //Add New Book -> works
    @PostMapping("/addNewBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        return this.bookService.save(book)
                .map(book1 -> ResponseEntity.ok().body(book1))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    //Delete Book -> works
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        this.bookService.deleteById(id);
        if (this.bookService.findById(id).isEmpty())
            return ResponseEntity.ok().build();

        return ResponseEntity.badRequest().build();
    }

    //Edit Book -> works
    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book){
        return this.bookService.edit(id, book)
                .map(b -> ResponseEntity.ok().body(b))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    //Mark as Taken Book -> works
    @PutMapping("/{id}/taken")
    public ResponseEntity<Void> markAsTaken(@PathVariable Long id){
        Book book = this.bookService.findById(id)
                .orElseThrow(BookNotFoundException::new);

        this.bookService.markAsTaken(book);
        return ResponseEntity.ok().build();
    }



}
