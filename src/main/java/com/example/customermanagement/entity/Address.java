package com.example.customermanagement.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String line1;
    private String line2;

    @ManyToOne
    private City city;

    @ManyToOne
    private Country country;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
