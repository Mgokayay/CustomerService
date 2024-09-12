package com.jekirdek.crmcustomer.controller;

import com.jekirdek.crmcustomer.dto.CustomerResponse;
import com.jekirdek.crmcustomer.entity.Customer;
import com.jekirdek.crmcustomer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerResponse addCustomer(@Valid @RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @GetMapping
    public List<CustomerResponse> getAllCustomers() {
        return customerService.findAll();
    }

    @GetMapping("/id/{id}")
    public CustomerResponse getCustomer(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @GetMapping("/email/{email}")
    public CustomerResponse findCustomerByEmail(@PathVariable String email) {
        return customerService.findByEmail(email);
    }

    @GetMapping("/registrationDate/{registrationDate}")
    public List<CustomerResponse> findCustomerByRegistrationDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate registrationDate) {
        return customerService.findByRegistrationDate(registrationDate);
    }

    @GetMapping("/name/{firstName}")
    public CustomerResponse findCustomerByName(@PathVariable String firstName) {
        return customerService.findByName(firstName);
    }

    @GetMapping("/region/{region}")
    public List<CustomerResponse> findCustomerByRegion(@PathVariable String region) {
        return customerService.findByRegion(region);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
    }

    @PutMapping("/{id}")
    public CustomerResponse updateCustomer(@PathVariable Long id,@Valid @RequestBody Customer customer) {
        return customerService.update(id,customer);
    }


}
