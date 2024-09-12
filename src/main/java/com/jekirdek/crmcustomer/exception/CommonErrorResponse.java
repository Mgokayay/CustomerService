package com.jekirdek.crmcustomer.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonErrorResponse {

    private Integer status;
    private String message;
    private LocalDateTime timestamp;
}
