package com.example.customermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDTO {
    private Long id;
    private String line1;
    private String line2;
    private String cityId;
    private String countryId;
}
