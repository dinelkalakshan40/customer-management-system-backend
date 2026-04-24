package com.example.customermanagement.repository;

import com.example.customermanagement.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyRepository extends JpaRepository<Family,Long> {
    List<Family> findByCustomerId(Long customerId);
}
