package com.example.backend.service;

import com.example.backend.model.Author;
import com.example.backend.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> listAll();

    Optional<Author> findById(Long authorId);

    Optional<Author> save(Author author);

    void deleteById(Long authorId);

    Optional<Author> edit(Long authorId, AuthorDto authorDto);

    //todo: booksByAuthor

}
