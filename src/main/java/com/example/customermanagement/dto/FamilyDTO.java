package com.example.customermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FamilyDTO {
    private Long familyCustomerId;
    private String relation;
}
