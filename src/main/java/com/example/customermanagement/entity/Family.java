package com.example.customermanagement.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "customer_family")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private Long familyMemberId;

//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private Customer customer;

//    @ManyToOne
//    @JoinColumn(name = "family_customer_id")
//    private Customer familyCustomer;

}
