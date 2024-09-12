package com.jekirdek.crmcustomer.service;

import com.jekirdek.crmcustomer.dto.CustomerResponse;
import com.jekirdek.crmcustomer.entity.Customer;
import com.jekirdek.crmcustomer.exception.CommonException;
import com.jekirdek.crmcustomer.repository.CustomerRepository;
import com.jekirdek.crmcustomer.util.CustomerDtoConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.jekirdek.crmcustomer.util.CustomerDtoConversion.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse save(Customer customer) {
        customer.setRegistrationDate(LocalDate.now());
        return convertCustomer(customerRepository.save(customer));
    }

    @Override
    public List<CustomerResponse> findAll() {
        return convertCustomerList(customerRepository.findAll());
    }

    @Override
    public CustomerResponse findById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return convertCustomer(customer.get());
        }
        throw new CommonException("Customer not found with given id" + id, HttpStatus.NOT_FOUND);


    }

    @Override
    public void deleteById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
        } else {
            throw new CommonException("Customer not found with given id" + id, HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public CustomerResponse findByEmail(String email) {
//        Optional<Customer> customer = customerRepository.findByEmail(email);
//        if(customer.isPresent()){
//            return CustomerDtoConversion.convertCustomer(customer.get());
//        }
//        throw new CommonException("Email not found :" + email, HttpStatus.NOT_FOUND);
        return convertCustomer(customerRepository.findByEmail(email)
                .orElseThrow(
                        () -> new CommonException("Email is not exist :" + email, HttpStatus.NOT_FOUND)));
    }

    @Override
    public List<CustomerResponse> findByRegistrationDate(LocalDate registrationDate) {
        return convertCustomerList(customerRepository.findByRegistrationDate(registrationDate));
    }

    @Override
    public CustomerResponse findByName(String firstName) {
        return convertCustomer(customerRepository.findByName(firstName)
                .orElseThrow(() -> new CommonException("Name is not exist :" + firstName, HttpStatus.NOT_FOUND)));
    }

    @Override
    public List<CustomerResponse> findByRegion(String region) {
        return convertCustomerList(customerRepository.findByRegion(region));
    }

    @Override
    public CustomerResponse update(Long id,Customer customer) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            Customer customerToUpdate = existingCustomer.get();
            customerToUpdate.setFirstName(customer.getFirstName());
            customerToUpdate.setLastName(customer.getLastName());
            customerToUpdate.setEmail(customer.getEmail());
            customerToUpdate.setRegistrationDate(LocalDate.now());

            Customer updatedCustomer = customerRepository.save(customerToUpdate);
            return CustomerDtoConversion.convertCustomer(updatedCustomer);
        }else {
            throw new CommonException("Customer not found with given id" + customer.getId(), HttpStatus.NOT_FOUND);
        }
    }


}
