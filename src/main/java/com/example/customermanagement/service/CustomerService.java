package com.example.customermanagement.service;

import com.example.customermanagement.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO createCustomer(CustomerDTO dto);
    CustomerDTO getCustomer(Long id);
    List<CustomerDTO> getAllCustomers();

    CustomerDTO updateCustomer(Long id, CustomerDTO dto);
}
