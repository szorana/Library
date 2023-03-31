package com.example.backend.model;

import com.example.backend.model.enums.BookCategory;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private BookCategory category;

    @ManyToOne
    private Author author;

    private int availableCopies;

    public Book() {
    }

    public Book(String name, BookCategory category, Author author, int availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public void markAsTaken(){
        this.availableCopies--;
    }
}
