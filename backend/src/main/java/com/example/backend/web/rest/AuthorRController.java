package com.example.backend.web.rest;

import com.example.backend.model.Author;
import com.example.backend.model.dto.AuthorDto;
import com.example.backend.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/authors")
public class AuthorRController {

    private final AuthorService authorService;


    public AuthorRController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> allAuthors(){
        return this.authorService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id){
        return this.authorService.findById(id)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/addAuthor")
    public ResponseEntity<Author> addAuthor(@RequestBody Author author){
        return this.authorService.save(author)
                .map(author1 -> ResponseEntity.ok().body(author1))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    // create read update delete

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable  Long id){
        this.authorService.deleteById(id);

        if (this.authorService.findById(id).isEmpty()){
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id,
                                               @RequestBody AuthorDto authorDto){
        return this.authorService.edit(id, authorDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
