package com.example.customermanagement.repository;

import com.example.customermanagement.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
    Country findByName(String name);
}
