package com.example.customermanagement.service.impl;

import com.example.customermanagement.dto.CustomerDTO;
import com.example.customermanagement.entity.*;
import com.example.customermanagement.repository.CityRepository;
import com.example.customermanagement.repository.CountryRepository;
import com.example.customermanagement.repository.CustomerRepository;
import com.example.customermanagement.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
@RequiredArgsConstructor
public class CustomerServiceIMPL implements CustomerService {

    private final CustomerRepository customerRepo;
    private final CityRepository cityRepo;
    private final CountryRepository countryRepo;
    @Override
    public CustomerDTO createCustomer(CustomerDTO dto) {

        if (customerRepo.findByNic(dto.getNic()).isPresent()) {
            throw new RuntimeException("NIC already exists");
        }

        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setDob(dto.getDob());
        customer.setNic(dto.getNic());

        // Mobiles
        List<Mobile> mobiles = dto.getMobiles().stream().map(num -> {
            Mobile m = new Mobile();
            m.setMobile(num);
            m.setCustomer(customer);
            return m;
        }).collect(Collectors.toList());

        // Addresses
        List<Address> addresses = dto.getAddresses().stream().map(a -> {
            Address ad = new Address();
            ad.setLine1(a.getLine1());
            ad.setLine2(a.getLine2());

            ad.setCity(cityRepo.findById(Long.valueOf(a.getCityId()))
                    .orElseThrow(() -> new RuntimeException("City not found")));

            ad.setCountry(countryRepo.findById(Long.valueOf(a.getCountryId()))
                    .orElseThrow(() -> new RuntimeException("Country not found")));

            ad.setCustomer(customer);
            return ad;
        }).collect(Collectors.toList());

        customer.setMobiles(mobiles);
        customer.setAddresses(addresses);

        Customer saved = customerRepo.save(customer);

        dto.setId(saved.getId());
        return dto;
    }
    @Override
    public CustomerDTO getCustomer(Long id) {
        Customer c = customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        CustomerDTO dto = new CustomerDTO();
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setDob(c.getDob());
        dto.setNic(c.getNic());

        return dto;
    }
    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepo.findAll().stream().map(c -> {
            CustomerDTO dto = new CustomerDTO();
            dto.setId(c.getId());
            dto.setName(c.getName());
            dto.setNic(c.getNic());
            return dto;
        }).collect(Collectors.toList());
    }
    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO dto) {

        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        customer.setName(dto.getName());
        customer.setDob(dto.getDob());

        customerRepo.save(customer);
        return dto;
    }

}
