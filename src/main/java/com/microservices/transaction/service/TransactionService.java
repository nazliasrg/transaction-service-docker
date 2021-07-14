package com.microservices.transaction.service;

import com.microservices.transaction.model.dto.TransactionDto;
import org.springframework.http.ResponseEntity;

public interface TransactionService {
    ResponseEntity<?> addTransaction(TransactionDto transactionDto);
}
