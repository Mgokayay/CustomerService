package com.jekirdek.crmcustomer.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommonException extends RuntimeException {

    private HttpStatus httpStatus;

    public CommonException(String message,HttpStatus httpStatus) {
         super(message);
         this.httpStatus = httpStatus;
    }
}
