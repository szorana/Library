package com.example.backend.service.impl;

import com.example.backend.model.Author;
import com.example.backend.model.Country;
import com.example.backend.model.dto.AuthorDto;
import com.example.backend.model.exceptions.AuthorNotFoundException;
import com.example.backend.model.exceptions.CountryNotFoundException;
import com.example.backend.repository.AuthorRepository;
import com.example.backend.repository.CountryRepository;
import com.example.backend.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository,
                             CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> listAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long authorId) {
        return this.authorRepository.findById(authorId);
    }

    @Override
    public Optional<Author> save(Author author) {
        Country country = this.countryRepository.findById(author.getCountry().getId())
                .orElseThrow(CountryNotFoundException::new);

        this.authorRepository.deleteById(author.getId());
        Author a = new Author(author.getName(), author.getSurname(), country);
        this.authorRepository.save(a);

        return Optional.of(a);
    }

    @Override
    public void deleteById(Long authorId) {
        this.authorRepository.deleteById(authorId);
    }

    @Override
    public Optional<Author> edit(Long authorId, AuthorDto authorDto) {
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        Country country = this.countryRepository.findById(authorDto.getCountry())
                .orElseThrow(CountryNotFoundException::new);

        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        author.setCountry(country);

        return Optional.of(this.authorRepository.save(author));
    }
}
