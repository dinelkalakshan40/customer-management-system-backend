package com.example.customermanagement.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate dob;

    @Column(unique = true)
    private String nic;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Mobile> mobiles;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Address> addresses;

//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
//    private List<Family> familyMembers;

}
