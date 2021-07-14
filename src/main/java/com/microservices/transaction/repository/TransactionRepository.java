package com.microservices.transaction.repository;

import com.microservices.transaction.model.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    TransactionEntity findByTransactionId(Integer transactionId);
}
