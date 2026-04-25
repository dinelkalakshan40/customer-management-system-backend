package com.example.customermanagement.service.impl;


import com.example.customermanagement.entity.Address;
import com.example.customermanagement.entity.Customer;
import com.example.customermanagement.entity.Mobile;
import com.example.customermanagement.repository.CityRepository;
import com.example.customermanagement.repository.CountryRepository;
import com.example.customermanagement.repository.CustomerRepository;
import com.example.customermanagement.service.BulkService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BulkServiceIMPL  implements BulkService {
    private final CustomerRepository customerRepo;
    private final CityRepository cityRepo;
    private final CountryRepository countryRepo;
    private static final int BATCH_SIZE = 1000;

    @Override
    public void processExcel(MultipartFile file) {

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {

            Sheet sheet = workbook.getSheetAt(0);
            List<Customer> batch = new ArrayList<>();

            for (Row row : sheet) {

                if (row.getRowNum() == 0) continue; // skip header

                Customer customer = new Customer();

                customer.setName(row.getCell(0).getStringCellValue());
                customer.setDob(row.getCell(1).getLocalDateTimeCellValue().toLocalDate());
                customer.setNic(row.getCell(2).getStringCellValue());

                // Mobile
                Mobile mobile = new Mobile();
                mobile.setMobile(row.getCell(3).getStringCellValue());
                mobile.setCustomer(customer);

                // Address
                Address address = new Address();
                address.setLine1(row.getCell(4).getStringCellValue());
                address.setLine2(row.getCell(5).getStringCellValue());

                Long cityId = (long) row.getCell(6).getNumericCellValue();
                Long countryId = (long) row.getCell(7).getNumericCellValue();


                address.setCity(cityRepo.findById(cityId).orElseThrow(() -> new RuntimeException("City not found")));
                address.setCountry(countryRepo.findById(countryId).orElseThrow(() -> new RuntimeException("City not found with id")));
                address.setCustomer(customer);

                customer.setMobiles(Arrays.asList(mobile));
                customer.setAddresses(Arrays.asList(address));

                batch.add(customer);

                // BATCH SAVE
                if (batch.size() == BATCH_SIZE) {
                    customerRepo.saveAll(batch);
                    batch.clear();
                }
            }

            // save remaining
            if (!batch.isEmpty()) {
                customerRepo.saveAll(batch);
            }

        } catch (Exception e) {
            throw new RuntimeException("Excel processing failed");
        }
    }
}
