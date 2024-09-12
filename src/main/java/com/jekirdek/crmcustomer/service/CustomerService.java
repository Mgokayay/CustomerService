package com.jekirdek.crmcustomer.service;

import com.jekirdek.crmcustomer.dto.CustomerResponse;
import com.jekirdek.crmcustomer.entity.Customer;

import java.time.LocalDate;
import java.util.List;

public interface CustomerService {

    CustomerResponse save(Customer customer);

    List<CustomerResponse> findAll();

    CustomerResponse findById(Long id);

    void deleteById(Long id);

    CustomerResponse findByEmail(String email);

    List<CustomerResponse> findByRegistrationDate(LocalDate registrationDate);

    CustomerResponse findByName(String name);

    List<CustomerResponse> findByRegion(String region);

    CustomerResponse update(Long id,Customer customer);
}
