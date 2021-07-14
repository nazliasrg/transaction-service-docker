package com.microservices.transaction.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private Date transactionDate;
    private Integer customerId;
    private Integer productId;
    private Integer amount;
    private Double cost;
}
