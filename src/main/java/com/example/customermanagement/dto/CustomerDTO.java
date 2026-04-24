package com.example.customermanagement.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDTO {
    private Long id;
    private String name;
    private LocalDate dob;
    private String nic;

    private List<String> mobiles;
    private List<AddressDTO> addresses;

}
