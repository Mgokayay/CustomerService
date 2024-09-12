package com.jekirdek.crmcustomer.repository;

import com.jekirdek.crmcustomer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE c.email=?1")
    Optional<Customer> findByEmail(String email);

    @Query("SELECT c FROM Customer c WHERE c.registrationDate=:registrationDate")
    List<Customer> findByRegistrationDate(LocalDate registrationDate);

    @Query(value = "SELECT * FROM customer WHERE customer.first_name=:firstName", nativeQuery = true)
    Optional<Customer> findByName(String firstName);

    @Query(value="SELECT * FROM customer WHERE customer.region=:region",nativeQuery = true)
    List<Customer> findByRegion(String region);

}
