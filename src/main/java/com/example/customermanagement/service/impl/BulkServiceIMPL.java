package com.example.customermanagement.service.impl;


import com.example.customermanagement.entity.*;
import com.example.customermanagement.repository.CityRepository;
import com.example.customermanagement.repository.CountryRepository;
import com.example.customermanagement.repository.CustomerRepository;
import com.example.customermanagement.service.BulkService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static javax.swing.UIManager.getString;

@Service
@Transactional
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
                System.out.println("Batch size: " + batch.size());


                Map<Long, City> cityCache = cityRepo.findAll()
                        .stream()
                        .collect(Collectors.toMap(City::getId, c -> c));

                Map<Long, Country> countryCache = countryRepo.findAll()
                        .stream()
                        .collect(Collectors.toMap(Country::getId, c -> c));

                for (Row row : sheet) {

                    if (row.getRowNum() == 0) continue;

                    Customer customer = new Customer();

                    // NAME
                    customer.setName(getString(row.getCell(0)));

                    // DOB 
                    customer.setDob(parseDate(row.getCell(1)));

                    // NIC
                    String nic = getString(row.getCell(2));
                    customer.setNic(nic);


                    if (nic != null && customerRepo.existsByNic(nic)) {
                        continue;
                    }


                    List<Mobile> mobiles = new ArrayList<>();

                    String mobile1 = getString(row.getCell(3));
                    String mobile2 = getString(row.getCell(4));
                    if (mobile1 != null && !mobile1.isEmpty()) {
                        Mobile m1 = new Mobile();
                        m1.setMobile(mobile1);
                        m1.setCustomer(customer);
                        mobiles.add(m1);
                    }

                    if (mobile2 != null && !mobile2.isEmpty()) {
                        Mobile m2 = new Mobile();
                        m2.setMobile(mobile2);
                        m2.setCustomer(customer);
                        mobiles.add(m2);
                    }

                    customer.setMobiles(mobiles);

                    // ADDRESS
                    Address address = new Address();
                    address.setLine1(getString(row.getCell(5)));
                    address.setLine2(getString(row.getCell(6)));


                    Long cityId = parseLongSafe(row.getCell(7));
                    Long countryId = parseLongSafe(row.getCell(8));

                    System.out.println("Row: " + row.getRowNum());
                    System.out.println("Excel City ID: [" + cityId + "]");
                    System.out.println("Excel Country ID: [" + countryId + "]");

                    City city = cityCache.get(cityId);
                    Country country = countryCache.get(countryId);

                    if (city == null || country == null) {
                        System.out.println("Invalid city/country → skipping row");
                        continue; // skip invalid master data
                    }

                    address.setCity(city);
                    address.setCountry(country);
                    address.setCustomer(customer);

                    // RELATIONS
                    customer.setAddresses(Collections.singletonList(address));

                    batch.add(customer);
                    System.out.println("Batch size: " + batch.size());

                    // BATCH INSERT
                    if (batch.size() >= BATCH_SIZE) {
                        System.out.println("Saving batch...");
                        customerRepo.saveAll(batch);
                        batch.clear();
                    }
                }

                if (!batch.isEmpty()) {
                    customerRepo.saveAll(batch);
                }

            } catch (Exception e) {
                throw new RuntimeException("Excel processing failed: " + e.getMessage(), e);
            }
    }
    // STRING READER
    private String getString(Cell cell) {
        if (cell == null) return null;

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();

            case NUMERIC:
                return String.valueOf((long) cell.getNumericCellValue());

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case FORMULA:
                return cell.getCellFormula();

            default:
                return null;
        }
    }

    //LONG PARSER
    private Long parseLongSafe(Cell cell) {
        if (cell == null) return null;

        try {
            switch (cell.getCellType()) {
                case NUMERIC:
                    return (long) cell.getNumericCellValue();

                case STRING:
                    return Long.parseLong(cell.getStringCellValue().trim());

                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    //DATE PARSER
    private LocalDate parseDate(Cell cell) {
        if (cell == null) return null;

        try {
            if (cell.getCellType() == CellType.NUMERIC &&
                    DateUtil.isCellDateFormatted(cell)) {

                return cell.getDateCellValue()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
            }

            if (cell.getCellType() == CellType.STRING) {
                return LocalDate.parse(cell.getStringCellValue().trim());
            }

        } catch (Exception e) {
            return null;
        }

        return null;
    }
}
