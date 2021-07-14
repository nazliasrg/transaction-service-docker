package com.microservices.transaction.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Integer customerId;
    private String username;
    private String password;
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private Double saldo;
    private Integer statusAccount;
}
