package com.jekirdek.crmcustomer.dto;

import java.time.LocalDate;


public record CustomerResponse(Long id, String firstName, String lastName,
                               String email, String region, LocalDate registrationDate) {
}
