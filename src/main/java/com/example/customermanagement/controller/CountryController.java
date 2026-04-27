package com.example.customermanagement.controller;

import com.example.customermanagement.entity.Country;
import com.example.customermanagement.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
@CrossOrigin
public class CountryController {

    private final CountryRepository repo;

    @GetMapping
    public List<Country> getAll() {
        return repo.findAll();
    }

}
