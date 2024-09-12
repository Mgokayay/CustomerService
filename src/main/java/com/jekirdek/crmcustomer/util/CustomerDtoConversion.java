package com.jekirdek.crmcustomer.util;

import com.jekirdek.crmcustomer.dto.CustomerResponse;
import com.jekirdek.crmcustomer.entity.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerDtoConversion {

    public static List<CustomerResponse> convertCustomerList(List<Customer> customerList) {
        List<CustomerResponse> customerResponseList = new ArrayList<>();
        customerResponseList = customerList.stream().map(customer -> convertCustomer(customer)).
                collect(Collectors.toList());
        return customerResponseList;
    }

    public static CustomerResponse convertCustomer(Customer customer){
        return new CustomerResponse(customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getRegion(),
                customer.getRegistrationDate());
    }
}
