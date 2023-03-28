package com.example.backend.model.exceptions;

public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException(String name) {
        super(String.format("%s NOT available. ", name));
    }
}
