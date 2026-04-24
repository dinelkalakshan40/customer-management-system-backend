package com.example.customermanagement.service;

import com.example.customermanagement.dto.CustomerDTO;

public interface CustomerService {
    CustomerDTO createCustomer(CustomerDTO dto);
  //  public void addFamily(Long customerId, AddFamilyRequestDTO dto);
}
