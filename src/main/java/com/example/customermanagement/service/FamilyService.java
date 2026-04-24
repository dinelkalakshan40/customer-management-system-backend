package com.example.customermanagement.service;

import com.example.customermanagement.dto.FamilyDTO;

import java.util.List;

public interface FamilyService {
    void addFamilyMember(FamilyDTO dto);

    List<Long> getFamilyMembers(Long customerId);
}
