package com.pinhobrunodev.OrderService.external.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {
    private String errorMessage;
    private String errorCode;
}
