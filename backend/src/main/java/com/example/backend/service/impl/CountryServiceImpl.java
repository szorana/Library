package com.example.backend.service.impl;

import com.example.backend.model.Country;
import com.example.backend.model.dto.CountryDto;
import com.example.backend.repository.CountryRepository;
import com.example.backend.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public Optional<Country> save(CountryDto countryDto) {
        Country country = new Country(countryDto.getName(),
                countryDto.getContinent());
        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public Optional<Country> save(String name, String continent) {
        Country country = new Country(name, continent);

        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public Optional<Country> edit(Long id, CountryDto countryDto) {
        Country country = this.countryRepository.findById(id).get();
        country.setName(countryDto.getName());
        country.setContinent(countryDto.getContinent());

        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public Optional<Country> edit(Long id, String name, String continent) {
        Country country = this.countryRepository.findById(id).get();
        country.setName(name);
        country.setContinent(continent);

        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }
}
