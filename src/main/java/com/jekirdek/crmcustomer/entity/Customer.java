package com.jekirdek.crmcustomer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "Customer")
@Table(name = "customer", schema = "crm_db")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @Size(min = 2, max = 50)
    private String firstName;

    @Column(name = "last_name")
    @Size(min = 2, max = 50)
    private String lastName;

    @Column(name = "email")
    @Email(message = "Email should be contain '@'")
    private String email;

    @Column(name = "region")
    @NotEmpty(message = "Region cannot be not empty")
    private String region;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

}
