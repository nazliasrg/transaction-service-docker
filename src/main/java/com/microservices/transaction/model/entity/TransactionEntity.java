package com.microservices.transaction.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tbl_transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    @Column
    private Date transactionDate;

    @ManyToOne
    @JoinColumn(name="customerId", referencedColumnName = "customerId")
    private CustomerEntity customerEntity;

    @ManyToOne
    @JoinColumn(name="productId", referencedColumnName = "productId")
    private ProductEntity productEntity;

    @Column(length = 3)
    private Integer amount;

    @Column
    private Double cost;

    public TransactionEntity(Date transactionDate, CustomerEntity customerEntity, ProductEntity productEntity, Integer amount, Double cost) {
        this.transactionDate = transactionDate;
        this.customerEntity = customerEntity;
        this.productEntity = productEntity;
        this.amount = amount;
        this.cost = cost;
    }
}
