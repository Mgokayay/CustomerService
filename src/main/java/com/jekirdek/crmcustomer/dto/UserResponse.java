package com.jekirdek.crmcustomer.dto;


import com.jekirdek.crmcustomer.entity.UserRole;

import java.time.LocalDateTime;


public record UserResponse(Long id, String username, UserRole userRole, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
