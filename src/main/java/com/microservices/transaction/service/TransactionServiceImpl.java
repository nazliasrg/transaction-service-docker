package com.microservices.transaction.service;

import com.microservices.transaction.model.dto.StatusMessageDto;
import com.microservices.transaction.model.dto.TransactionDto;
import com.microservices.transaction.model.entity.CustomerEntity;
import com.microservices.transaction.model.entity.ProductEntity;
import com.microservices.transaction.model.entity.TransactionEntity;
import com.microservices.transaction.repository.CustomerRepository;
import com.microservices.transaction.repository.ProductRepository;
import com.microservices.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    KafkaTemplate<String, TransactionEntity> kafkaTemplate;

    @Autowired
    KafkaTemplate<String, ProductEntity> productKafkaTemplate;

    @Autowired
    KafkaTemplate<String, CustomerEntity> customerKafkaTemplate;

    private static final String TOPIC = "TransactionTopic";

    private static final String PRODUCTTOPIC = "ProductTopic";

    private static final String CUSTOMERTOPIC = "CustomerTopic";

    private Double cost;

    @Override
    public ResponseEntity<?> addTransaction(TransactionDto dto) {
        StatusMessageDto<TransactionEntity> result = new StatusMessageDto<>();

        TransactionEntity transactionEntity = new TransactionEntity();

        CustomerEntity customerEntity = customerRepository.findByCustomerId(dto.getCustomerId());

        ProductEntity productEntity = productRepository.findByProductId(dto.getProductId());

        if(dto.getAmount() > productEntity.getStock()){
            result.setStatus(HttpStatus.BAD_REQUEST.value());
            result.setMessage("Not enough stock!");
            return ResponseEntity.badRequest().body(result);
        }
        else{
            cost = productEntity.getPrice() * dto.getAmount();
            if(cost > customerEntity.getSaldo()){
                result.setStatus(HttpStatus.BAD_REQUEST.value());
                result.setMessage("Not enough saldo!");
                return ResponseEntity.badRequest().body(result);
            }
            else{
                transactionEntity.setTransactionDate(new Date(System.currentTimeMillis()));
                transactionEntity.setCustomerEntity(customerEntity);
                transactionEntity.setProductEntity(productEntity);
                transactionEntity.setAmount(dto.getAmount());
                transactionEntity.setCost(cost);

                transactionRepository.save(transactionEntity);

                productEntity.setStock(productEntity.getStock() - transactionEntity.getAmount());

                productRepository.save(productEntity);

                customerEntity.setSaldo(customerEntity.getSaldo() - cost);

                customerRepository.save(customerEntity);

                kafkaTemplate.send(TOPIC, transactionEntity);
                productKafkaTemplate.send(PRODUCTTOPIC, productEntity);
                customerKafkaTemplate.send(CUSTOMERTOPIC, customerEntity);

                result.setStatus(HttpStatus.OK.value());
                result.setMessage(transactionEntity.getTransactionId() + " has been added successfully!");
                result.setData(transactionEntity);
                return ResponseEntity.ok(result);
            }
        }
    }
}
