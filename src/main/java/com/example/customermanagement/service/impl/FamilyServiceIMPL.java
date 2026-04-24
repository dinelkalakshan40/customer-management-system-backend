package com.example.customermanagement.service.impl;


import com.example.customermanagement.dto.FamilyDTO;
import com.example.customermanagement.repository.CustomerRepository;
import com.example.customermanagement.repository.FamilyRepository;
import com.example.customermanagement.service.FamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static javax.swing.text.StyleConstants.Family;

@Service
@RequiredArgsConstructor
public class FamilyServiceIMPL{
//    private final FamilyRepository repo;
//    private final CustomerRepository customerRepo;
//
//    @Override
//    public void addFamilyMember(FamilyDTO dto) {
//
//        // validate both customers exist
//        if (!customerRepo.existsById(dto.getCustomerId()) ||
//                !customerRepo.existsById(dto.getFamilyMemberId())) {
//            throw new RuntimeException("Customer not found");
//        }
//
//        Family f = new Family();
//        f.setCustomerId(dto.getCustomerId());
//        f.setFamilyMemberId(dto.getFamilyMemberId());
//
//        repo.save(f);
//    }
//
//    @Override
//    public List<Long> getFamilyMembers(Long customerId) {
//
//        return repo.findByCustomerId(customerId)
//                .stream()
//                .map(Family::getFamilyMemberId)
//                .toList();
//    }
}
