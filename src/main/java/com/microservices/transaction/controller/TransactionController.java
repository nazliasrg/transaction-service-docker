package com.microservices.transaction.controller;

import com.microservices.transaction.model.dto.StatusMessageDto;
import com.microservices.transaction.model.dto.TransactionDto;
import com.microservices.transaction.model.entity.CustomerEntity;
import com.microservices.transaction.model.entity.ProductEntity;
import com.microservices.transaction.model.entity.TransactionEntity;
import com.microservices.transaction.repository.CustomerRepository;
import com.microservices.transaction.repository.ProductRepository;
import com.microservices.transaction.repository.TransactionRepository;
import com.microservices.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transaction")
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping("/add-transaction")
    public ResponseEntity<?> publishTransaction(@RequestBody TransactionDto transactionDto){
        StatusMessageDto result = new StatusMessageDto<>();
        try{
            return transactionService.addTransaction(transactionDto);
        }
        catch (Exception e){
            result.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        List<TransactionEntity> transactionDtos = transactionRepository.findAll();
        return ResponseEntity.ok(transactionDtos);
    }

    @GetMapping("/get-customer")
    public ResponseEntity<?> getCustomer(){
        List<CustomerEntity> customerEntityList = customerRepository.findAll();
        return ResponseEntity.ok(customerEntityList);
    }

    @GetMapping("/get-product")
    public ResponseEntity<?> getProduct(){
        List<ProductEntity> productEntityList = productRepository.findAll();
        return ResponseEntity.ok(productEntityList);
    }
}
