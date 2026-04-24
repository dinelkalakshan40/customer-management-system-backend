package com.example.customermanagement.controller;


import com.example.customermanagement.dto.FamilyDTO;
import com.example.customermanagement.service.FamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/family")
@RequiredArgsConstructor
public class FamilyController {
    private final FamilyService service;

    @PostMapping
    public ResponseEntity<String> add(@RequestBody FamilyDTO dto) {
        service.addFamilyMember(dto);
        return ResponseEntity.ok("Family member added");
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<Long>> get(@PathVariable Long customerId) {
        return ResponseEntity.ok(service.getFamilyMembers(customerId));
    }
}
