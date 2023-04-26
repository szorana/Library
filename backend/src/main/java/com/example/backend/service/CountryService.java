package com.example.backend.service;

import com.example.backend.model.Country;
import com.example.backend.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> listAll();
    Optional<Country> findById(Long id);
    Optional<Country> save(CountryDto countryDto);
    Optional<Country> save(String name, String continent);
    Optional<Country> edit(Long id, CountryDto countryDto);
    Optional<Country> edit(Long id, String name, String continent);
    void deleteById(Long id);

}
