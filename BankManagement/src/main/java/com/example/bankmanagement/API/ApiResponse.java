package com.example.bankmanagement.API;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private String status;
}
