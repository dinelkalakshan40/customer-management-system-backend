package com.example.customermanagement.controller;


import com.example.customermanagement.dto.CustomerDTO;
import com.example.customermanagement.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin
@RequiredArgsConstructor
public class Controller {

    private final CustomerService service;

    @PostMapping
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO dto) {
        return ResponseEntity.ok(service.createCustomer(dto));
    }


}
