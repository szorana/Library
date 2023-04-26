package com.example.backend.web.rest;

import com.example.backend.model.enums.BookCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:3000")
public class BookCategoriesRController {

    @GetMapping
    public ResponseEntity<BookCategory[]> getAllCategories(){
        return ResponseEntity.ok(BookCategory.values());
    }

}
