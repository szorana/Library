package com.example.backend.model.exceptions;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException() {
        super("Book NOT found.");
    }
}
